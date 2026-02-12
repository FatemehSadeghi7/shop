package com.example.shop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shop.data.local.dao.CartDao
import com.example.shop.data.local.dao.CategoryDao
import com.example.shop.data.local.dao.ProductDao
import com.example.shop.data.local.entity.CartItemEntity
import com.example.shop.data.local.entity.CategoryEntity
import com.example.shop.data.local.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class,
        CategoryEntity::class,
        CartItemEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class ShopDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
    abstract fun cartDao(): CartDao
}
