package com.example.shop.presentation

import com.example.shop.data.model.Product
import com.example.shop.domain.model.FilterOptions
import com.example.shop.domain.model.SortType


data class ShopUiState(
    val products: List<Product> = emptyList(),
    val categories: List<String> = emptyList(),
    val sortType: SortType = SortType.DEFAULT,
    val filterOptions: FilterOptions = FilterOptions(),
    val isLoading: Boolean = true,
    val error: String? = null
)

sealed interface ShopUiEvent {
    data class OnSortChanged(val sortType: SortType) : ShopUiEvent
    data class OnFilterChanged(val filterOptions: FilterOptions) : ShopUiEvent
    data class OnProductClick(val productId: Int) : ShopUiEvent
    data object OnClearFilter : ShopUiEvent
}