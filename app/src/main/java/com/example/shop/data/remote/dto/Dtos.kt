package com.example.shop.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Double,
    @SerializedName("discount_percent") val discountPercent: Int?,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("images") val images: List<String>?,
    @SerializedName("category_id") val categoryId: Int,
    @SerializedName("category_name") val categoryName: String?,
    @SerializedName("sizes") val sizes: List<String>?,
    @SerializedName("colors") val colors: List<String>?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("review_count") val reviewCount: Int?,
    @SerializedName("in_stock") val inStock: Boolean?,
)

data class CategoryDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("product_count") val productCount: Int?,
    @SerializedName("background_color") val backgroundColor: Long?,
)

data class BannerDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("discount_percent") val discountPercent: Int,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("background_color") val backgroundColor: Long?,
)

data class ApiResponse<T>(
    @SerializedName("data") val data: T,
    @SerializedName("message") val message: String?,
    @SerializedName("success") val success: Boolean,
)
