package com.example.shop.domain.model

data class CartItem(
    val id: Int,
    val product: Product,
    val quantity: Int = 1,
    val selectedSize: String = "",
    val selectedColor: String = "",
) {
    val totalPrice: Double
        get() = product.discountedPrice * quantity
}
