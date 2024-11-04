package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import navigation.bottom_bar.BottomNavigation

@Composable
fun TestMarketApp() {
    val navController: NavHostController = rememberNavController()

    CompositionLocalProvider(
        LocalNavHost provides navController
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.BottomTabs.route
        ) {
            composable(route = Screen.BottomTabs.route) {
                BottomNavigation()
            }
        }
    }
}