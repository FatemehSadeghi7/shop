package com.example.shop.domain.repository

import com.example.shop.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCartItems(): Flow<List<CartItem>>

    fun getCartItemCount(): Flow<Int>

    fun getCartTotalPrice(): Flow<Double>

    suspend fun addToCart(productId: Int, size: String, color: String)

    suspend fun removeFromCart(cartItemId: Int)

    suspend fun updateQuantity(cartItemId: Int, quantity: Int)

    suspend fun clearCart()
}
