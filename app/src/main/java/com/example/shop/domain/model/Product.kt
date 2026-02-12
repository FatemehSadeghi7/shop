package com.example.shop.domain.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val discountPercent: Int = 0,
    val imageUrl: String,
    val images: List<String> = emptyList(),
    val categoryId: Int,
    val categoryName: String = "",
    val sizes: List<String> = emptyList(),
    val colors: List<String> = emptyList(),
    val isFavorite: Boolean = false,
    val rating: Double = 0.0,
    val reviewCount: Int = 0,
    val inStock: Boolean = true,
) {
    val discountedPrice: Double
        get() = if (discountPercent > 0) price * (1 - discountPercent / 100.0) else price

    val hasDiscount: Boolean
        get() = discountPercent > 0
}
