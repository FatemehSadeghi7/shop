package com.example.shop.domain.model

data class Banner(
    val id: Int,
    val title: String,
    val subtitle: String,
    val discountPercent: Int,
    val imageUrl: String,
    val backgroundColor: Long = 0xFFFF6B47,
)
