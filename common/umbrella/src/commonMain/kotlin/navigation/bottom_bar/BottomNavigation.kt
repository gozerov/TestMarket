package navigation.bottom_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import navigation.Screen
import theme.KMPMarketTheme

@Composable
fun BottomNavigation() {
    val navController = rememberNavController()
    val items = listOf(
        Screen.MainFlow.BASE_ROUTE,
        Screen.ShoppingFlow.BASE_ROUTE,
        Screen.CartFlow.BASE_ROUTE
    )

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController,
            modifier = Modifier.padding(bottom = 56.dp).fillMaxSize(),
            startDestination = Screen.MainFlow.BASE_ROUTE
        ) {
            navigation(
                startDestination = Screen.MainFlow.Main.route,
                route = Screen.MainFlow.BASE_ROUTE
            ) {
                composable(Screen.MainFlow.Main.route) { Text("main", color = Color.Black) }
            }

            navigation(
                startDestination = Screen.ShoppingFlow.ShoppingList.route,
                route = Screen.ShoppingFlow.BASE_ROUTE
            ) {
                composable(Screen.ShoppingFlow.ShoppingList.route) {
                    Text(
                        "shopping",
                        color = Color.Black
                    )
                }
            }

            navigation(
                startDestination = Screen.CartFlow.Cart.route,
                route = Screen.CartFlow.BASE_ROUTE
            ) {
                composable(Screen.CartFlow.Cart.route) { Text("cart", color = Color.Black) }
            }
        }

        BottomNavigation(
            modifier = Modifier.align(Alignment.BottomStart),
            backgroundColor = KMPMarketTheme.colors.primaryBackground
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            items.forEach { screen ->
                BottomNavigationItem(
                    icon = { },
                    label = { Text(screen, color = KMPMarketTheme.colors.text) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen } == true,
                    onClick = {
                        navController.navigate(screen) {
                            popUpTo(navController.graph.findStartDestination().route.toString()) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }

    }
}