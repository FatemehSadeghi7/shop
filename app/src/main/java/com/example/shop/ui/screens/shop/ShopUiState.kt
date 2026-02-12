package com.example.shop.ui.screens.shop

import com.example.shop.domain.model.FilterOptions
import com.example.shop.domain.model.Product
import com.example.shop.domain.model.SortType

data class ShopUiState(
    val isLoading: Boolean = true,
    val products: List<Product> = emptyList(),
    val sortType: SortType = SortType.NEWEST,
    val filterOptions: FilterOptions = FilterOptions(),
    val cartItemCount: Int = 0,
    val error: String? = null,
    val showSortSheet: Boolean = false,
    val showFilterSheet: Boolean = false,
)
