package com.example.shop.domain.usecase


import com.example.shop.data.model.Product
import com.example.shop.domain.model.FilterOptions
import com.example.shop.domain.model.SortType
import com.example.shop.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    operator fun invoke(
        sortType: SortType = SortType.DEFAULT,
        filter: FilterOptions = FilterOptions()
    ): Flow<List<Product>> {
        return repository.getProducts().map { products ->
            products
                .applyFilter(filter)
                .applySort(sortType)
        }
    }

    private fun List<Product>.applyFilter(filter: FilterOptions): List<Product> {
        var result = this
        if (filter.categories.isNotEmpty()) {
            result = result.filter { it.category in filter.categories }
        }
        filter.minPrice?.let { min ->
            result = result.filter { it.price >= min }
        }
        filter.maxPrice?.let { max ->
            result = result.filter { it.price <= max }
        }
        return result
    }

    private fun List<Product>.applySort(sortType: SortType): List<Product> {
        return when (sortType) {
            SortType.DEFAULT -> this
            SortType.PRICE_LOW_TO_HIGH -> sortedBy { it.price }
            SortType.PRICE_HIGH_TO_LOW -> sortedByDescending { it.price }
            SortType.NAME_A_Z -> sortedBy { it.name }
            SortType.NAME_Z_A -> sortedByDescending { it.name }
        }
    }
}