package presentation.screens.cart.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.models.CheckedProduct
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource
import ru.gozerov.test_market.common.features.cart.presentation.resources.Res
import ru.gozerov.test_market.common.features.cart.presentation.resources.cart
import theme.TestMarketTheme
import views.DefaultDivider

@Composable
fun FilledCartView(
    products: ImmutableList<CheckedProduct>,
    onChecked: (productId: Int, isChecked: Boolean) -> Unit,
    onChangeAmountClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.then(Modifier.fillMaxSize())
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
            text = stringResource(Res.string.cart),
            color = TestMarketTheme.colors.text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        DefaultDivider(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products.size) { ind ->
                val product = products[ind]
                val isChecked = remember { mutableStateOf(product.isChecked) }
                CheckedProductCard(
                    product = product,
                    onChecked = { value ->
                        isChecked.value = value
                        onChecked(product.id, value)
                    },
                    isChecked = isChecked,
                    onChangeAmountClicked = onChangeAmountClicked,
                    isLastItem = ind == products.size - 1
                )
            }

            item {
                if (products.isNotEmpty()) {
                    DefaultDivider()
                }
            }
        }
    }
}