package com.example.shop.data.repository

import com.example.shop.data.local.dao.CartDao
import com.example.shop.data.local.dao.ProductDao
import com.example.shop.data.local.entity.CartItemEntity
import com.example.shop.data.mapper.toDomain
import com.example.shop.domain.model.CartItem
import com.example.shop.domain.model.Product
import com.example.shop.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao,
    private val productDao: ProductDao,
) : CartRepository {

    override fun getCartItems(): Flow<List<CartItem>> {
        return cartDao.getAllCartItems().map { entities ->
            entities.mapNotNull { cartEntity ->
                val productEntity = productDao.getProductById(cartEntity.productId)
                productEntity?.let {
                    CartItem(
                        id = cartEntity.id,
                        product = it.toDomain(),
                        quantity = cartEntity.quantity,
                        selectedSize = cartEntity.selectedSize,
                        selectedColor = cartEntity.selectedColor,
                    )
                }
            }
        }
    }

    override fun getCartItemCount(): Flow<Int> {
        return cartDao.getCartItemCount()
    }

    override fun getCartTotalPrice(): Flow<Double> {
        return getCartItems().map { items ->
            items.sumOf { it.totalPrice }
        }
    }

    override suspend fun addToCart(productId: Int, size: String, color: String) {
        val existing = cartDao.findCartItem(productId, size, color)
        if (existing != null) {
            cartDao.updateQuantity(existing.id, existing.quantity + 1)
        } else {
            cartDao.insertCartItem(
                CartItemEntity(
                    productId = productId,
                    selectedSize = size,
                    selectedColor = color,
                )
            )
        }
    }

    override suspend fun removeFromCart(cartItemId: Int) {
        cartDao.deleteCartItem(cartItemId)
    }

    override suspend fun updateQuantity(cartItemId: Int, quantity: Int) {
        if (quantity <= 0) {
            cartDao.deleteCartItem(cartItemId)
        } else {
            cartDao.updateQuantity(cartItemId, quantity)
        }
    }

    override suspend fun clearCart() {
        cartDao.clearCart()
    }
}
