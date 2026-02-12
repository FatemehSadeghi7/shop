package com.example.shop.data.mapper

import com.example.shop.data.local.entity.ProductEntity
import com.example.shop.data.remote.dto.ProductDto
import com.example.shop.domain.model.Product



fun ProductDto.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        description = description,
        price = price,
        discountPercent = discountPercent ?: 0,
        imageUrl = imageUrl,
        images = images?.joinToString(",") ?: "",
        categoryId = categoryId,
        categoryName = categoryName ?: "",
        sizes = sizes?.joinToString(",") ?: "",
        colors = colors?.joinToString(",") ?: "",
        rating = rating ?: 0.0,
        reviewCount = reviewCount ?: 0,
        inStock = inStock ?: true,
    )
}

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        name = name,
        description = description,
        price = price,
        discountPercent = discountPercent,
        imageUrl = imageUrl,
        images = if (images.isNotEmpty()) images.split(",") else emptyList(),
        categoryId = categoryId,
        categoryName = categoryName,
        sizes = if (sizes.isNotEmpty()) sizes.split(",") else emptyList(),
        colors = if (colors.isNotEmpty()) colors.split(",") else emptyList(),
        isFavorite = isFavorite,
        rating = rating,
        reviewCount = reviewCount,
        inStock = inStock,
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        description = description,
        price = price,
        discountPercent = discountPercent,
        imageUrl = imageUrl,
        images = images.joinToString(","),
        categoryId = categoryId,
        categoryName = categoryName,
        sizes = sizes.joinToString(","),
        colors = colors.joinToString(","),
        isFavorite = isFavorite,
        rating = rating,
        reviewCount = reviewCount,
        inStock = inStock,
    )
}

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        name = name,
        description = description,
        price = price,
        discountPercent = discountPercent ?: 0,
        imageUrl = imageUrl,
        images = images ?: emptyList(),
        categoryId = categoryId,
        categoryName = categoryName ?: "",
        sizes = sizes ?: emptyList(),
        colors = colors ?: emptyList(),
        rating = rating ?: 0.0,
        reviewCount = reviewCount ?: 0,
        inStock = inStock ?: true,
    )
}
