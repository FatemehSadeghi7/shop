package com.example.shop.domain.repository

import com.example.shop.domain.model.FilterOptions
import com.example.shop.domain.model.Product
import com.example.shop.domain.model.SortType
import com.example.shop.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(
        sortType: SortType = SortType.NEWEST,
        filterOptions: FilterOptions = FilterOptions(),
    ): Flow<Resource<List<Product>>>

    fun getProductById(id: Int): Flow<Resource<Product>>

    fun getProductsByCategory(categoryId: Int): Flow<Resource<List<Product>>>

    fun searchProducts(query: String): Flow<Resource<List<Product>>>

    fun getFavoriteProducts(): Flow<Resource<List<Product>>>

    suspend fun toggleFavorite(productId: Int)
}
