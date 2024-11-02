package presentation.screens.cart.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import ru.gozerov.test_market.common.features.cart.presentation.resources.Res
import ru.gozerov.test_market.common.features.cart.presentation.resources.cart_is_empty
import theme.TestMarketTheme

@Composable
fun EmptyCartView(modifier: Modifier = Modifier) {
    Box(modifier = modifier.then(Modifier.fillMaxSize()), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(Res.string.cart_is_empty),
            fontSize = 18.sp,
            color = TestMarketTheme.colors.text
        )
    }
}