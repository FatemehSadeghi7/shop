package com.example.shop.domain.usecase.category

import com.example.shop.domain.model.Category
import com.example.shop.domain.repository.CategoryRepository
import com.example.shop.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    operator fun invoke(): Flow<Resource<List<Category>>> {
        return repository.getCategories()
    }
}
