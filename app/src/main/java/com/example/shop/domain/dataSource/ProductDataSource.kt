package com.example.shop.domain.dataSource


import com.example.shop.R
import com.example.shop.data.model.Product

object ProductDataSource {

    fun getAllProducts(): List<Product> = listOf(
        Product(
            id = 1,
            name = "Surge Short",
            price = 68.0,
            currency = "usd",
            imageRes = R.drawable.image1,
            category = "shorts",
            description = "Lightweight training shorts with four-way stretch."
        ),
        Product(
            id = 2,
            name = "Sweat Jogger French",
            price = 68.0,
            currency = "usd",
            imageRes = R.drawable.image2,
            category = "pants",
            description = "French terry joggers for all-day comfort."
        ),
        Product(
            id = 3,
            name = "Biker Short",
            price = 52.0,
            currency = "usd",
            imageRes = R.drawable.image3,
            category = "shorts",
            description = "High-rise biker shorts with hidden pocket."
        ),
        Product(
            id = 4,
            name = "Camo Legging",
            price = 78.0,
            currency = "usd",
            imageRes = R.drawable.image6,
            category = "leggings",
            description = "Full-length camo print leggings with compression fit."
        ),
        Product(
            id = 5,
            name = "Training Tank",
            price = 45.0,
            currency = "usd",
            imageRes = R.drawable.image7,
            category = "tops",
            description = "Breathable mesh tank for intense workouts."
        ),
        Product(
            id = 6,
            name = "Running Jacket",
            price = 120.0,
            currency = "usd",
            imageRes = R.drawable.image8,
            category = "jackets",
            description = "Water-resistant running jacket with reflective details."
        )
    )
}