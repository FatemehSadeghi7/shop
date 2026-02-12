package com.example.shop.data.repository

import com.example.shop.data.local.dao.CategoryDao
import com.example.shop.data.mapper.toDomain
import com.example.shop.data.remote.ShopApiService
import com.example.shop.domain.model.Category
import com.example.shop.domain.repository.CategoryRepository
import com.example.shop.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val apiService: ShopApiService,
    private val categoryDao: CategoryDao,
) : CategoryRepository {

    override fun getCategories(): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getCategories()
            if (response.success) {
                emit(Resource.Success(response.data.map { it.toDomain() }))
            } else {
                emit(Resource.Success(getHardcodedCategories()))
            }
        } catch (e: Exception) {
            emit(Resource.Success(getHardcodedCategories()))
        }
    }

    override fun getCategoryById(id: Int): Flow<Resource<Category>> = flow {
        emit(Resource.Loading())
        val category = getHardcodedCategories().find { it.id == id }
        if (category != null) {
            emit(Resource.Success(category))
        } else {
            emit(Resource.Error("Category not found"))
        }
    }

    private fun getHardcodedCategories(): List<Category> = listOf(
        Category(id = 1, name = "Shorts", type = "Shorts", imageUrl = "shorts", productCount = 24, backgroundColor = 0xFFF0F4E8),
        Category(id = 2, name = "Pants", type = "Pants", imageUrl = "pants", productCount = 18, backgroundColor = 0xFFECECEC),
        Category(id = 3, name = "Leggings", type = "Leggings", imageUrl = "leggings", productCount = 32, backgroundColor = 0xFFFDE8E4),
        Category(id = 4, name = "Sleeve", type = "Shirt", imageUrl = "sleeve", productCount = 45, backgroundColor = 0xFFF0F4E8),
        Category(id = 5, name = "Long Sleeve", type = "Sweater", imageUrl = "long_sleeve", productCount = 28, backgroundColor = 0xFFECECEC),
        Category(id = 6, name = "Align Tank", type = "Shirt", imageUrl = "align_tank", productCount = 15, backgroundColor = 0xFFFDE8E4),
    )
}
