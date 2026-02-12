package com.example.shop.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Main : Screen("main")
    data object Home : Screen("home")
    data object Shop : Screen("shop")
    data object Discover : Screen("discover")
    data object Cart : Screen("cart")
    data object Search : Screen("search")
    data object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
}