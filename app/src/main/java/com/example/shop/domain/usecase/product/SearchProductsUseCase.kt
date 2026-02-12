package com.example.shop.domain.usecase.product

import com.example.shop.domain.model.Product
import com.example.shop.domain.repository.ProductRepository
import com.example.shop.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<Product>>> {
        if (query.isBlank()) {
            return flowOf(Resource.Success(emptyList()))
        }
        return repository.searchProducts(query.trim())
    }
}
