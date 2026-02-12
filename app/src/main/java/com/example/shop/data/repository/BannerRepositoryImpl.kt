package com.example.shop.data.repository

import com.example.shop.data.mapper.toDomain
import com.example.shop.data.remote.ShopApiService
import com.example.shop.domain.model.Banner
import com.example.shop.domain.repository.BannerRepository
import com.example.shop.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BannerRepositoryImpl @Inject constructor(
    private val apiService: ShopApiService,
) : BannerRepository {

    override fun getBanners(): Flow<Resource<List<Banner>>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getBanners()
            if (response.success) {
                emit(Resource.Success(response.data.map { it.toDomain() }))
            } else {
                emit(Resource.Success(getHardcodedBanners()))
            }
        } catch (e: Exception) {
            emit(Resource.Success(getHardcodedBanners()))
        }
    }

    private fun getHardcodedBanners(): List<Banner> = listOf(
        Banner(
            id = 1,
            title = "BIG SEASON SALE",
            subtitle = "SAVE UP TO",
            discountPercent = 75,
            imageUrl = "sale_banner",
            backgroundColor = 0xFFFF6B47,
        ),
        Banner(
            id = 2,
            title = "NEW ARRIVALS",
            subtitle = "CHECK OUT",
            discountPercent = 0,
            imageUrl = "new_arrivals",
            backgroundColor = 0xFF1A1A1A,
        ),
    )
}
