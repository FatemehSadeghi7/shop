package com.example.shop.domain.usecase.cart

import com.example.shop.domain.repository.CartRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(
        productId: Int,
        size: String = "",
        color: String = "",
    ) {
        repository.addToCart(productId, size, color)
    }
}
