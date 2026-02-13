package com.example.shop.data.mapper

import com.example.shop.data.local.entity.CategoryEntity
import com.example.shop.data.remote.dto.CategoryDto
import com.example.shop.domain.model.Category

fun CategoryDto.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        type = type,
        imageUrl = imageUrl,
        productCount = productCount ?: 0,
        backgroundColor = backgroundColor ?: 0xFFF5F0EB,
    )
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        type = type,
        imageUrl = imageUrl,
        productCount = productCount,
        backgroundColor = backgroundColor,
    )
}

fun CategoryDto.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        type = type,
        imageUrl = imageUrl,
        productCount = productCount ?: 0,
        backgroundColor = backgroundColor ?: 0xFFF5F0EB,
    )
}
