package com.example.shop.domain.usecase.cart

import com.example.shop.domain.model.CartItem
import com.example.shop.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    fun getItems(): Flow<List<CartItem>> {
        return repository.getCartItems()
    }

    fun getItemCount(): Flow<Int> {
        return repository.getCartItemCount()
    }

    fun getTotalPrice(): Flow<Double> {
        return repository.getCartTotalPrice()
    }
}
