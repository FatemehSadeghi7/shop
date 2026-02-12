package com.example.shop.domain.usecase.banner

import com.example.shop.domain.model.Banner
import com.example.shop.domain.repository.BannerRepository
import com.example.shop.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSaleBannersUseCase @Inject constructor(
    private val repository: BannerRepository
) {
    operator fun invoke(): Flow<Resource<List<Banner>>> {
        return repository.getBanners()
    }
}
