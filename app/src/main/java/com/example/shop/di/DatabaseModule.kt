package com.example.shop.di

import android.content.Context
import androidx.room.Room
import com.example.shop.data.local.ShopDatabase
import com.example.shop.data.local.dao.CartDao
import com.example.shop.data.local.dao.CategoryDao
import com.example.shop.data.local.dao.ProductDao
import com.example.shop.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideShopDatabase(
        @ApplicationContext context: Context
    ): ShopDatabase {
        return Room.databaseBuilder(
            context,
            ShopDatabase::class.java,
            Constants.DATABASE_NAME,
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideProductDao(database: ShopDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: ShopDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    @Singleton
    fun provideCartDao(database: ShopDatabase): CartDao {
        return database.cartDao()
    }
}
