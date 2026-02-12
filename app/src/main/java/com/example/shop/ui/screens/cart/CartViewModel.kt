package com.example.shop.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.domain.usecase.cart.GetCartUseCase
import com.example.shop.domain.usecase.cart.RemoveFromCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    init {
        observeCart()
    }

    private fun observeCart() {
        combine(
            getCartUseCase.getItems(),
            getCartUseCase.getTotalPrice(),
        ) { items, total ->
            _uiState.update {
                it.copy(isLoading = false, items = items, totalPrice = total)
            }
        }.launchIn(viewModelScope)
    }

    fun removeItem(cartItemId: Int) {
        viewModelScope.launch { removeFromCartUseCase(cartItemId) }
    }

    fun updateQuantity(cartItemId: Int, quantity: Int) {
        viewModelScope.launch { removeFromCartUseCase.updateQuantity(cartItemId, quantity) }
    }

    fun clearCart() {
        viewModelScope.launch { removeFromCartUseCase.clearCart() }
    }
}
