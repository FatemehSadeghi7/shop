package com.example.shop.data.repository



import com.example.shop.data.model.Product
import com.example.shop.domain.dataSource.ProductDataSource
import com.example.shop.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepositoryImpl : ProductRepository {

    override fun getProducts(): Flow<List<Product>> = flow {
        emit(ProductDataSource.getAllProducts())
    }

    override fun getProductById(id: Int): Flow<Product?> = flow {
        emit(ProductDataSource.getAllProducts().find { it.id == id })
    }

    override fun getCategories(): Flow<List<String>> = flow {
        val categories = ProductDataSource.getAllProducts()
            .map { it.category }
            .distinct()
        emit(categories)
    }
}