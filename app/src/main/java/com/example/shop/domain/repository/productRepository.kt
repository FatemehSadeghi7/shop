package com.example.shop.domain.repository


import com.example.shop.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<List<Product>>
    fun getProductById(id: Int): Flow<Product?>
    fun getCategories(): Flow<List<String>>
}