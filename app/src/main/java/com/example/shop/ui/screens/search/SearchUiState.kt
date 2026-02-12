package com.example.shop.ui.screens.search

import com.example.shop.domain.model.Product

data class SearchUiState(
    val query: String = "",
    val isSearching: Boolean = false,
    val results: List<Product> = emptyList(),
    val recentSearches: List<String> = emptyList(),
    val hasSearched: Boolean = false,
)
