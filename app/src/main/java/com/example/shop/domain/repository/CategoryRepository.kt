package com.example.shop.domain.repository

import com.example.shop.domain.model.Category
import com.example.shop.util.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getCategories(): Flow<Resource<List<Category>>>

    fun getCategoryById(id: Int): Flow<Resource<Category>>
}
