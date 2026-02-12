package com.example.shop.domain.usecase.product

import com.example.shop.domain.repository.ProductRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: Int) {
        repository.toggleFavorite(productId)
    }
}
