package com.example.shop.di

import com.example.shop.data.repository.ProductRepositoryImpl
import com.example.shop.domain.repository.ProductRepository
import com.example.shop.domain.usecase.GetCategoriesUseCase
import com.example.shop.domain.usecase.GetProductByIdUseCase
import com.example.shop.domain.usecase.GetProductsUseCase

object AppModule {

    // Repository
    private val productRepository: ProductRepository by lazy {
        ProductRepositoryImpl()
    }

    // UseCases
    val getProductsUseCase: GetProductsUseCase by lazy {
        GetProductsUseCase(productRepository)
    }

    val getProductByIdUseCase: GetProductByIdUseCase by lazy {
        GetProductByIdUseCase(productRepository)
    }

    val getCategoriesUseCase: GetCategoriesUseCase by lazy {
        GetCategoriesUseCase(productRepository)
    }
}