package com.example.shop.domain.usecase.product

import com.example.shop.domain.model.Product
import com.example.shop.domain.repository.ProductRepository
import com.example.shop.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(id: Int): Flow<Resource<Product>> {
        return repository.getProductById(id)
    }
}
