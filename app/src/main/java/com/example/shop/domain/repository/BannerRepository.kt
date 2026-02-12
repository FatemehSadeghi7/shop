package com.example.shop.domain.repository

import com.example.shop.domain.model.Banner
import com.example.shop.util.Resource
import kotlinx.coroutines.flow.Flow

interface BannerRepository {

    fun getBanners(): Flow<Resource<List<Banner>>>
}
