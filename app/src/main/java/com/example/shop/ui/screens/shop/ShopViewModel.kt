package com.example.shop.ui.screens.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.domain.model.FilterOptions
import com.example.shop.domain.model.SortType
import com.example.shop.domain.usecase.cart.GetCartUseCase
import com.example.shop.domain.usecase.product.GetProductsUseCase
import com.example.shop.domain.usecase.product.ToggleFavoriteUseCase
import com.example.shop.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val getCartUseCase: GetCartUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ShopUiState())
    val uiState: StateFlow<ShopUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
        observeCart()
    }

    fun loadProducts() {
        val state = _uiState.value
        getProductsUseCase(state.sortType, state.filterOptions).onEach { result ->
            when (result) {
                is Resource.Loading -> _uiState.update { it.copy(isLoading = true) }
                is Resource.Success -> _uiState.update {
                    it.copy(isLoading = false, products = result.data ?: emptyList(), error = null)
                }
                is Resource.Error -> _uiState.update {
                    it.copy(isLoading = false, error = result.message, products = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeCart() {
        getCartUseCase.getItemCount().onEach { count ->
            _uiState.update { it.copy(cartItemCount = count) }
        }.launchIn(viewModelScope)
    }

    fun onSortChanged(sortType: SortType) {
        _uiState.update { it.copy(sortType = sortType, showSortSheet = false) }
        loadProducts()
    }

    fun onFilterChanged(filterOptions: FilterOptions) {
        _uiState.update { it.copy(filterOptions = filterOptions, showFilterSheet = false) }
        loadProducts()
    }

    fun toggleFavorite(productId: Int) {
        viewModelScope.launch {
            toggleFavoriteUseCase(productId)
            loadProducts()
        }
    }

    fun toggleSortSheet() {
        _uiState.update { it.copy(showSortSheet = !it.showSortSheet) }
    }

    fun toggleFilterSheet() {
        _uiState.update { it.copy(showFilterSheet = !it.showFilterSheet) }
    }
}
