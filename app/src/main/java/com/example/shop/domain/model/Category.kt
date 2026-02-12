package com.example.shop.domain.model

data class Category(
    val id: Int,
    val name: String,
    val type: String,
    val imageUrl: String,
    val productCount: Int = 0,
    val backgroundColor: Long = 0xFFF5F0EB,
)
