package com.example.shop.domain.model

enum class SortType(val displayName: String) {
    NEWEST("Newest"),
    PRICE_LOW_TO_HIGH("Price: Low to High"),
    PRICE_HIGH_TO_LOW("Price: High to Low"),
    MOST_POPULAR("Most Popular"),
    BEST_RATING("Best Rating"),
}

data class FilterOptions(
    val categoryIds: List<Int> = emptyList(),
    val minPrice: Double? = null,
    val maxPrice: Double? = null,
    val sizes: List<String> = emptyList(),
    val colors: List<String> = emptyList(),
    val onlyInStock: Boolean = false,
    val onlyOnSale: Boolean = false,
) {
    val isActive: Boolean
        get() = categoryIds.isNotEmpty() ||
                minPrice != null ||
                maxPrice != null ||
                sizes.isNotEmpty() ||
                colors.isNotEmpty() ||
                onlyInStock ||
                onlyOnSale
}
