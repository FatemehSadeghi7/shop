package com.example.shop.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String,
    val imageUrl: String,
    val productCount: Int = 0,
    val backgroundColor: Long = 0xFFF5F0EB,
)
