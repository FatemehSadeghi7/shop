package com.example.shop.domain.usecase.cart

import com.example.shop.domain.repository.CartRepository
import javax.inject.Inject

class RemoveFromCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(cartItemId: Int) {
        repository.removeFromCart(cartItemId)
    }

    suspend fun updateQuantity(cartItemId: Int, quantity: Int) {
        repository.updateQuantity(cartItemId, quantity)
    }

    suspend fun clearCart() {
        repository.clearCart()
    }
}
