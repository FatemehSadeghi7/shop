package com.example.shop.ui.screens.product_detail

import com.example.shop.domain.model.Product

data class ProductDetailUiState(
    val isLoading: Boolean = true,
    val product: Product? = null,
    val selectedSize: String = "",
    val selectedColor: String = "",
    val selectedImageIndex: Int = 0,
    val addedToCart: Boolean = false,
    val error: String? = null,
)
