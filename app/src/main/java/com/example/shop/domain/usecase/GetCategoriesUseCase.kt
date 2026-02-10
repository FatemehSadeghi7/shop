package com.example.shop.domain.usecase


import com.example.shop.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<List<String>> {
        return repository.getCategories()
    }
}