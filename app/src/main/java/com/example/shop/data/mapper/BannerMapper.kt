package com.example.shop.data.mapper

import com.example.shop.data.remote.dto.BannerDto
import com.example.shop.domain.model.Banner

fun BannerDto.toDomain(): Banner {
    return Banner(
        id = id,
        title = title,
        subtitle = subtitle,
        discountPercent = discountPercent,
        imageUrl = imageUrl,
        backgroundColor = backgroundColor ?: 0xFFFF6B47,
    )
}
