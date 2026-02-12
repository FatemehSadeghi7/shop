package com.example.shop.ui.screens.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.domain.usecase.cart.AddToCartUseCase
import com.example.shop.domain.usecase.product.GetProductByIdUseCase
import com.example.shop.domain.usecase.product.ToggleFavoriteUseCase
import com.example.shop.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {

    private val productId: Int = savedStateHandle.get<Int>("productId") ?: -1

    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    init {
        loadProduct()
    }

    private fun loadProduct() {
        getProductByIdUseCase(productId).onEach { result ->
            when (result) {
                is Resource.Loading -> _uiState.update { it.copy(isLoading = true) }
                is Resource.Success -> _uiState.update {
                    it.copy(
                        isLoading = false,
                        product = result.data,
                        selectedSize = result.data?.sizes?.firstOrNull() ?: "",
                        selectedColor = result.data?.colors?.firstOrNull() ?: "",
                        error = null,
                    )
                }
                is Resource.Error -> _uiState.update {
                    it.copy(isLoading = false, error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun selectSize(size: String) {
        _uiState.update { it.copy(selectedSize = size) }
    }

    fun selectColor(color: String) {
        _uiState.update { it.copy(selectedColor = color) }
    }

    fun selectImage(index: Int) {
        _uiState.update { it.copy(selectedImageIndex = index) }
    }

    fun addToCart() {
        val state = _uiState.value
        val product = state.product ?: return
        viewModelScope.launch {
            addToCartUseCase(product.id, state.selectedSize, state.selectedColor)
            _uiState.update { it.copy(addedToCart = true) }
            delay(2000)
            _uiState.update { it.copy(addedToCart = false) }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            toggleFavoriteUseCase(productId)
            loadProduct()
        }
    }
}
