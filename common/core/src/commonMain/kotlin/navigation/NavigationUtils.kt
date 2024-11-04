package navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.gozerov.test_market.common.core.Res
import ru.gozerov.test_market.common.core.cart
import ru.gozerov.test_market.common.core.ic_cart_outlined
import ru.gozerov.test_market.common.core.ic_search
import ru.gozerov.test_market.common.core.ic_favorite_outlined
import ru.gozerov.test_market.common.core.main
import ru.gozerov.test_market.common.core.shopping_list

@Composable
fun getTabName(screen: String): String {
    return when (screen) {
        Screen.MainFlow.BASE_ROUTE -> stringResource(Res.string.main)
        Screen.ShoppingFlow.BASE_ROUTE -> stringResource(Res.string.shopping_list)
        Screen.CartFlow.BASE_ROUTE -> stringResource(Res.string.cart)
        else -> throw IllegalStateException("Unknown base route for bottom navigation")
    }
}


@Composable
fun getTabIconPainter(screen: String): Painter {
    return when (screen) {
        Screen.MainFlow.BASE_ROUTE -> painterResource(Res.drawable.ic_search)
        Screen.ShoppingFlow.BASE_ROUTE -> painterResource(Res.drawable.ic_favorite_outlined)
        Screen.CartFlow.BASE_ROUTE -> painterResource(Res.drawable.ic_cart_outlined)
        else -> throw IllegalStateException("Unknown base route for bottom navigation")
    }
}