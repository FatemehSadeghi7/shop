package com.example.shop.ui.screens.discover

import com.example.shop.domain.model.Category

data class DiscoverUiState(
    val isLoading: Boolean = true,
    val categories: List<Category> = emptyList(),
    val cartItemCount: Int = 0,
    val error: String? = null,
)
