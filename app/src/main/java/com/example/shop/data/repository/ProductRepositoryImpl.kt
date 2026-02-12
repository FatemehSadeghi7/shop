package com.example.shop.data.repository

import com.example.shop.data.local.dao.ProductDao
import com.example.shop.data.mapper.toDomain
import com.example.shop.data.mapper.toEntity
import com.example.shop.data.remote.ShopApiService
import com.example.shop.domain.model.FilterOptions
import com.example.shop.domain.model.Product
import com.example.shop.domain.model.SortType
import com.example.shop.domain.repository.ProductRepository
import com.example.shop.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val apiService: ShopApiService,
    private val productDao: ProductDao,
) : ProductRepository {


    override fun getProducts(
        sortType: SortType,
        filterOptions: FilterOptions,
    ): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())

        val cachedProducts = try {
            val count = productDao.getProductCount()
            if (count == 0) {
                val products = getHardcodedProducts()
                productDao.insertProducts(products.map { it.toEntity() })
                products
            } else {
                getHardcodedProducts()
            }
        } catch (e: Exception) {
            getHardcodedProducts()
        }

        emit(Resource.Loading(data = cachedProducts))

        try {
            val response = apiService.getProducts(sort = sortType.name.lowercase())
            if (response.success) {
                val products = response.data.map { it.toDomain() }
                productDao.insertProducts(products.map { it.toEntity() })
                emit(Resource.Success(products))
            } else {
                emit(Resource.Error(response.message ?: "Unknown error", cachedProducts))
            }
        } catch (e: Exception) {
            val products = applyFiltersAndSort(cachedProducts, sortType, filterOptions)
            emit(Resource.Success(products))
        }
    }

    override fun getProductById(id: Int): Flow<Resource<Product>> = flow {
        emit(Resource.Loading())
        try {
            val dbProduct = productDao.getProductById(id)
            if (dbProduct != null) {
                emit(Resource.Success(dbProduct.toDomain()))
            } else {
                val product = getHardcodedProducts().find { it.id == id }
                if (product != null) {
                    productDao.insertProduct(product.toEntity())
                    emit(Resource.Success(product))
                } else {
                    emit(Resource.Error("Product not found"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }

    override fun getProductsByCategory(categoryId: Int): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        val products = getHardcodedProducts().filter { it.categoryId == categoryId }
        emit(Resource.Success(products))
    }

    override fun searchProducts(query: String): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        val products = getHardcodedProducts().filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.description.contains(query, ignoreCase = true) ||
                    it.categoryName.contains(query, ignoreCase = true)
        }
        emit(Resource.Success(products))
    }

    override fun getFavoriteProducts(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        val products = getHardcodedProducts().filter { it.isFavorite }
        emit(Resource.Success(products))
    }

    override suspend fun toggleFavorite(productId: Int) {
        productDao.toggleFavorite(productId)
    }

    private fun applyFiltersAndSort(
        products: List<Product>,
        sortType: SortType,
        filterOptions: FilterOptions,
    ): List<Product> {
        var result = products

        if (filterOptions.categoryIds.isNotEmpty()) {
            result = result.filter { it.categoryId in filterOptions.categoryIds }
        }
        filterOptions.minPrice?.let { min ->
            result = result.filter { it.discountedPrice >= min }
        }
        filterOptions.maxPrice?.let { max ->
            result = result.filter { it.discountedPrice <= max }
        }
        if (filterOptions.onlyInStock) {
            result = result.filter { it.inStock }
        }
        if (filterOptions.onlyOnSale) {
            result = result.filter { it.hasDiscount }
        }

        // Sort
        result = when (sortType) {
            SortType.NEWEST -> result
            SortType.PRICE_LOW_TO_HIGH -> result.sortedBy { it.discountedPrice }
            SortType.PRICE_HIGH_TO_LOW -> result.sortedByDescending { it.discountedPrice }
            SortType.MOST_POPULAR -> result.sortedByDescending { it.reviewCount }
            SortType.BEST_RATING -> result.sortedByDescending { it.rating }
        }

        return result
    }

    /**
     * داده‌های هاردکد (بر اساس تصاویر Vertlune)
     */
    private fun getHardcodedProducts(): List<Product> = listOf(
        Product(
            id = 1, name = "Surge Short", description = "Lightweight training shorts with 4-way stretch fabric.",
            price = 68.0, imageUrl = "surge_short", categoryId = 1, categoryName = "Shorts",
            sizes = listOf("S", "M", "L", "XL"), colors = listOf("#1B4F72", "#2C3E50", "#1A1A1A"),
            rating = 4.5, reviewCount = 128, inStock = true,
        ),
        Product(
            id = 2, name = "Sweat Jogger French", description = "Premium French terry jogger pants for all-day comfort.",
            price = 68.0, imageUrl = "sweat_jogger", categoryId = 2, categoryName = "Pants",
            sizes = listOf("S", "M", "L", "XL"), colors = listOf("#BEB3A7", "#1A1A1A", "#3D3D3D"),
            rating = 4.7, reviewCount = 95, inStock = true,
        ),
        Product(
            id = 3, name = "Align Biker Short", description = "High-waist biker shorts with buttery-soft Nulu fabric.",
            price = 58.0, imageUrl = "align_biker", categoryId = 1, categoryName = "Shorts",
            sizes = listOf("XS", "S", "M", "L"), colors = listOf("#C0392B", "#1A1A1A", "#5D6D7E"),
            rating = 4.8, reviewCount = 256, inStock = true, discountPercent = 15,
        ),
        Product(
            id = 4, name = "Camo Legging", description = "High-rise camo print legging with hidden pocket.",
            price = 88.0, imageUrl = "camo_legging", categoryId = 3, categoryName = "Leggings",
            sizes = listOf("XS", "S", "M", "L", "XL"), colors = listOf("#4A6741", "#1A1A1A"),
            rating = 4.3, reviewCount = 67, inStock = true,
        ),
        Product(
            id = 5, name = "Metal Vent Tech Sleeve", description = "Anti-stink short-sleeve shirt with seamless construction.",
            price = 78.0, imageUrl = "metal_vent_sleeve", categoryId = 4, categoryName = "Shirt",
            sizes = listOf("S", "M", "L", "XL", "XXL"), colors = listOf("#6B8E23", "#1A1A1A", "#FFFFFF"),
            rating = 4.6, reviewCount = 183, inStock = true,
        ),
        Product(
            id = 6, name = "At Ease Hoodie", description = "Relaxed-fit hoodie in soft, textured fabric.",
            price = 128.0, imageUrl = "at_ease_hoodie", categoryId = 5, categoryName = "Sweater",
            sizes = listOf("S", "M", "L", "XL"), colors = listOf("#34495E", "#7F8C8D", "#1A1A1A"),
            rating = 4.9, reviewCount = 312, inStock = true, discountPercent = 20,
        ),
        Product(
            id = 7, name = "Align Tank", description = "Light-support tank top with built-in bra.",
            price = 58.0, imageUrl = "align_tank", categoryId = 4, categoryName = "Shirt",
            sizes = listOf("XS", "S", "M", "L"), colors = listOf("#E74C3C", "#1A1A1A", "#FDFEFE"),
            rating = 4.7, reviewCount = 445, inStock = true,
        ),
        Product(
            id = 8, name = "Pace Breaker Short", description = "Versatile lined shorts for running and training.",
            price = 72.0, imageUrl = "pace_breaker", categoryId = 1, categoryName = "Shorts",
            sizes = listOf("S", "M", "L", "XL"), colors = listOf("#2E86C1", "#1A1A1A", "#ABB2B9"),
            rating = 4.4, reviewCount = 89, inStock = false,
        ),
        Product(
            id = 9, name = "Scuba Oversized Hoodie", description = "Oversized half-zip hoodie in cotton fleece.",
            price = 118.0, imageUrl = "scuba_hoodie", categoryId = 5, categoryName = "Sweater",
            sizes = listOf("XS/S", "M/L", "XL/XXL"), colors = listOf("#D5DBDB", "#1A1A1A", "#A93226"),
            rating = 4.8, reviewCount = 567, inStock = true, discountPercent = 10,
        ),
        Product(
            id = 10, name = "ABC Slim Pant", description = "Classic slim-fit pant with anti-ball crushing technology.",
            price = 128.0, imageUrl = "abc_pant", categoryId = 2, categoryName = "Pants",
            sizes = listOf("28", "30", "32", "34", "36"), colors = listOf("#1A1A1A", "#4A4A4A", "#2C3E50"),
            rating = 4.6, reviewCount = 234, inStock = true,
        ),
    )
}