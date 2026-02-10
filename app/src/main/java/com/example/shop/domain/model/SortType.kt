package com.example.shop.domain.model


enum class SortType {
    DEFAULT,
    PRICE_LOW_TO_HIGH,
    PRICE_HIGH_TO_LOW,
    NAME_A_Z,
    NAME_Z_A
}

data class FilterOptions(
    val categories: Set<String> = emptySet(),
    val minPrice: Double? = null,
    val maxPrice: Double? = null
)