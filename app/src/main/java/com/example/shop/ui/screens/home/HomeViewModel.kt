package com.example.shop.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.domain.usecase.banner.GetSaleBannersUseCase
import com.example.shop.domain.usecase.cart.GetCartUseCase
import com.example.shop.domain.usecase.category.GetCategoriesUseCase
import com.example.shop.domain.usecase.product.GetProductsUseCase
import com.example.shop.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getSaleBannersUseCase: GetSaleBannersUseCase,
    private val getCartUseCase: GetCartUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeData()
        observeCart()
    }

    private fun loadHomeData() {
        loadProducts()
        loadCategories()
        loadBanners()
    }

    private fun loadProducts() {
        getProductsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            featuredProducts = result.data?.take(6) ?: emptyList(),
                            error = null,
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message,
                            featuredProducts = result.data?.take(6) ?: emptyList(),
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadCategories() {
        getCategoriesUseCase().onEach { result ->
            if (result is Resource.Success) {
                _uiState.update {
                    it.copy(categories = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadBanners() {
        getSaleBannersUseCase().onEach { result ->
            if (result is Resource.Success) {
                _uiState.update {
                    it.copy(banners = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeCart() {
        getCartUseCase.getItemCount().onEach { count ->
            _uiState.update { it.copy(cartItemCount = count) }
        }.launchIn(viewModelScope)
    }

    fun retry() {
        loadHomeData()
    }
}
