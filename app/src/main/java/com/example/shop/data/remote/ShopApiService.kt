package com.example.shop.data.remote

import com.example.shop.data.remote.dto.ApiResponse
import com.example.shop.data.remote.dto.BannerDto
import com.example.shop.data.remote.dto.CategoryDto
import com.example.shop.data.remote.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopApiService {

    @GET("products")
    suspend fun getProducts(
        @Query("sort") sort: String? = null,
        @Query("category_id") categoryId: Int? = null,
        @Query("min_price") minPrice: Double? = null,
        @Query("max_price") maxPrice: Double? = null,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20,
    ): ApiResponse<List<ProductDto>>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ApiResponse<ProductDto>

    @GET("products/search")
    suspend fun searchProducts(
        @Query("q") query: String
    ): ApiResponse<List<ProductDto>>

    @GET("categories")
    suspend fun getCategories(): ApiResponse<List<CategoryDto>>

    @GET("categories/{id}")
    suspend fun getCategoryById(
        @Path("id") id: Int
    ): ApiResponse<CategoryDto>

    @GET("banners")
    suspend fun getBanners(): ApiResponse<List<BannerDto>>
}
