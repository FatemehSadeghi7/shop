package com.example.shop.ui.screens.home

import com.example.shop.domain.model.Banner
import com.example.shop.domain.model.Category
import com.example.shop.domain.model.Product

data class HomeUiState(
    val isLoading: Boolean = true,
    val featuredProducts: List<Product> = emptyList(),
    val categories: List<Category> = emptyList(),
    val banners: List<Banner> = emptyList(),
    val cartItemCount: Int = 0,
    val error: String? = null,
)
