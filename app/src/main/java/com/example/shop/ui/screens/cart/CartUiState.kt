package com.example.shop.ui.screens.cart

import com.example.shop.domain.model.CartItem

data class CartUiState(
    val isLoading: Boolean = true,
    val items: List<CartItem> = emptyList(),
    val totalPrice: Double = 0.0,
    val error: String? = null,
)
