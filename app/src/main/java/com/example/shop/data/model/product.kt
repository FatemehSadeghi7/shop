package com.example.shop.data.model


data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val currency: String,
    val imageRes: Int,
    val category: String,
    val description: String
) {
    val formattedPrice: String
        get() = "$${price.toInt()} $currency"
}