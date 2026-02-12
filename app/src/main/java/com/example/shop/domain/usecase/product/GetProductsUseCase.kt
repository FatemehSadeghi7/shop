package com.example.shop.domain.usecase.product

import com.example.shop.domain.model.FilterOptions
import com.example.shop.domain.model.Product
import com.example.shop.domain.model.SortType
import com.example.shop.domain.repository.ProductRepository
import com.example.shop.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(
        sortType: SortType = SortType.NEWEST,
        filterOptions: FilterOptions = FilterOptions(),
    ): Flow<Resource<List<Product>>> {
        return repository.getProducts(sortType, filterOptions)
    }
}
