package com.example.shop.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.shop.ui.screens.cart.CartScreen
import com.example.shop.ui.screens.discover.DiscoverScreen
import com.example.shop.ui.screens.home.HomeScreen
import com.example.shop.ui.screens.main.MainScreen
import com.example.shop.ui.screens.product_detail.ProductDetailScreen
import com.example.shop.ui.screens.search.SearchScreen
import com.example.shop.ui.screens.shop.ShopScreen
import com.example.shop.ui.screens.splash.SplashScreen
import com.example.shop.util.Constants

@Composable
fun ShopNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { fadeIn(animationSpec = tween(Constants.ANIMATION_DURATION)) },
        exitTransition = { fadeOut(animationSpec = tween(Constants.ANIMATION_DURATION)) },
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.Main.route) {
            MainScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Main.route) { inclusive = true }
                    }
                },
                onSearchClick = { navController.navigate(Screen.Search.route) },
                onCartClick = { navController.navigate(Screen.Cart.route)},
                onDiscoverClick = { navController.navigate(Screen.Discover.route)},
            )
        }

        composable(route = Screen.Home.route) {
            HomeScreen(
                onNavigateToShop = { navController.navigate(Screen.Shop.route) },
                onNavigateToProduct = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                },
                onNavigateToSearch = { navController.navigate(Screen.Search.route) },
                onNavigateToCart = { navController.navigate(Screen.Cart.route) },
                onNavigateToDiscover = { navController.navigate(Screen.Discover.route) },
            )
        }

        composable(route = Screen.Shop.route) {
            ShopScreen(
                onNavigateToProduct = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                },
                onNavigateToSearch = { navController.navigate(Screen.Search.route) },
                onNavigateToCart = { navController.navigate(Screen.Cart.route) },
                onNavigateBack = { navController.popBackStack() },
                onNavigateToDiscover = { navController.navigate(Screen.Discover.route) },
            )
        }

        composable(route = Screen.Discover.route) {
            DiscoverScreen(
                onNavigateToShop = { categoryId ->
                    navController.navigate(Screen.Shop.route)
                },
                onNavigateToSearch = { navController.navigate(Screen.Search.route) },
                onNavigateToCart = { navController.navigate(Screen.Cart.route) },
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(
                navArgument("productId") { type = NavType.IntType }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(Constants.ANIMATION_DURATION),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(Constants.ANIMATION_DURATION),
                )
            },
        ) {
            ProductDetailScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToCart = { navController.navigate(Screen.Cart.route) },
            )
        }

        composable(
            route = Screen.Cart.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(Constants.ANIMATION_DURATION),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(Constants.ANIMATION_DURATION),
                )
            },
        ) {
            CartScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToProduct = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                },
            )
        }

        composable(route = Screen.Search.route) {
            SearchScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToProduct = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId)) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}