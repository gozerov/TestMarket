package navigation.bottom_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import navigation.Screen
import navigation.getTabIconPainter
import navigation.getTabName
import presentation.screens.cart.CartScreen
import presentation.screens.list.ShoppingListScreen
import presentation.screens.main.MainScreen
import theme.TestMarketTheme

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
                composable(Screen.MainFlow.Main.route) { MainScreen() }
            }

            navigation(
                startDestination = Screen.ShoppingFlow.ShoppingList.route,
                route = Screen.ShoppingFlow.BASE_ROUTE
            ) {
                composable(Screen.ShoppingFlow.ShoppingList.route) { ShoppingListScreen() }
            }

            navigation(
                startDestination = Screen.CartFlow.Cart.route,
                route = Screen.CartFlow.BASE_ROUTE
            ) {
                composable(Screen.CartFlow.Cart.route) { CartScreen() }
            }
        }

        BottomNavigation(
            modifier = Modifier.align(Alignment.BottomStart),
            backgroundColor = TestMarketTheme.colors.primaryBackground
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            items.forEach { screen ->
                val isSelected = currentDestination?.hierarchy?.any { it.route == screen } == true
                BottomNavigationItem(
                    icon = {
                        Icon(
                            modifier = Modifier.padding(vertical = 4.dp).size(20.dp).alpha(if (isSelected) 1f else 0.5f),
                            painter = getTabIconPainter(screen),
                            contentDescription = screen,
                            tint = TestMarketTheme.colors.text
                        )
                    },
                    label = {
                        Text(
                            modifier = Modifier
                                .alpha(if (isSelected) 1f else 0.5f),
                            letterSpacing = TextUnit(0f, TextUnitType.Sp),
                            text = getTabName(screen),
                            color = TestMarketTheme.colors.text,
                        )
                    },
                    selected = isSelected,
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