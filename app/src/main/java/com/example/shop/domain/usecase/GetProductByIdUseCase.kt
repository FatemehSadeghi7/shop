package com.example.shop.domain.usecase


import com.example.shop.data.model.Product
import com.example.shop.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductByIdUseCase(
    private val repository: ProductRepository
) {
    operator fun invoke(id: Int): Flow<Product?> {
        return repository.getProductById(id)
    }
}