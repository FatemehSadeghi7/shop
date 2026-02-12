package com.example.shop.di

import com.example.shop.data.repository.BannerRepositoryImpl
import com.example.shop.data.repository.CartRepositoryImpl
import com.example.shop.data.repository.CategoryRepositoryImpl
import com.example.shop.data.repository.ProductRepositoryImpl
import com.example.shop.domain.repository.BannerRepository
import com.example.shop.domain.repository.CartRepository
import com.example.shop.domain.repository.CategoryRepository
import com.example.shop.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        impl: CartRepositoryImpl
    ): CartRepository

    @Binds
    @Singleton
    abstract fun bindBannerRepository(
        impl: BannerRepositoryImpl
    ): BannerRepository
}
