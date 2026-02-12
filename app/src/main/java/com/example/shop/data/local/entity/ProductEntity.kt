package com.example.shop.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val discountPercent: Int = 0,
    val imageUrl: String,
    val images: String = "",
    val categoryId: Int,
    val categoryName: String = "",
    val sizes: String = "",
    val colors: String = "",
    val isFavorite: Boolean = false,
    val rating: Double = 0.0,
    val reviewCount: Int = 0,
    val inStock: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
)
