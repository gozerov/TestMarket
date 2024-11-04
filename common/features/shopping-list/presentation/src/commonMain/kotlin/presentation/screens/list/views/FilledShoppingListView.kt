package presentation.screens.list.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import domain.models.ProductWithAmount
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource
import ru.gozerov.test_market.common.features.`shopping-list`.presentation.resources.Res
import ru.gozerov.test_market.common.features.`shopping-list`.presentation.resources.shopping_list
import theme.TestMarketTheme
import views.DefaultDivider

@Composable
fun FilledShoppingListView(
    products: ImmutableList<ProductWithAmount>,
    onCartClicked: (product: ProductWithAmount, isAddedToCart: Boolean) -> Unit,
    onChangeAmountClicked: () -> Unit,
    onMenuClicked: (product: ProductWithAmount) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.then(Modifier.fillMaxSize())
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
            text = stringResource(Res.string.shopping_list),
            color = TestMarketTheme.colors.text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        DefaultDivider(modifier = Modifier.padding(start = 16.dp, top = 16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Spacer(modifier = Modifier.padding(top = 16.dp))
            }
            items(products.size) { ind ->

                val product = products[ind]
                val isAddedToCart = remember { mutableStateOf(product.isInCart) }
                WishlistCard(
                    product = product,
                    onCartClicked = {
                        isAddedToCart.value = !isAddedToCart.value
                        onCartClicked(product, isAddedToCart.value)
                    },
                    onChangeAmountClicked = onChangeAmountClicked,
                    onMenuClicked = {
                        onMenuClicked(product)
                    },
                    isLastItem = ind == products.size - 1
                )

            }

            item {
                if (products.isNotEmpty()) {
                    DefaultDivider(modifier = Modifier.padding(start = 16.dp, top = 8.dp))
                }
            }
        }
    }
}