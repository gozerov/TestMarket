package navigation

sealed class Screen(
    val route: String
) {

    data object BottomTabs : Screen("bottomTabs")

    sealed class CartFlow(featureRoute: String) : Screen(featureRoute) {

        data object Cart : Screen.CartFlow("cart")

        companion object {

            const val BASE_ROUTE = "cartFlow"

        }

    }

    sealed class ShoppingFlow(featureRoute: String) : Screen(featureRoute) {

        data object ShoppingList : Screen.ShoppingFlow("login")

        companion object {

            const val BASE_ROUTE = "shoppingFlow"

        }

    }

    sealed class MainFlow(featureRoute: String) : Screen(featureRoute) {

        data object Main : Screen.MainFlow("main")

        companion object {

            const val BASE_ROUTE = "mainFlow"

        }

    }

}