
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shop.app.navigation.Routes
import com.example.shop.presentation.screen.ShopScreen
import com.example.shop.presentation.screen.HomeScreen
import com.example.shop.presentation.screen.SplashScreen

@Composable
fun RouteNavGraph() {
    val nav= rememberNavController()
    NavHost(navController = nav, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) {

            SplashScreen (
                onDone = { nav.navigate(Routes.MAIN){
                    popUpTo(Routes.SPLASH)
                }
                         },
            )
        }
        composable(Routes.MAIN) {

            MainScreen (
                onArrowClick = {
                    nav.navigate(Routes.HOME)
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen (
                onSearchClick = {
                    nav.navigate(Routes.SHOP)
                },
            )
        }

        composable(Routes.SHOP) {
            ShopScreen(
                onBackClick = { nav.popBackStack() },
                onSearchClick = { },
                onCartClick = { },
                onMenuClick = { },
                onProductClick = { productId ->

                }
            )
        }

    }
}