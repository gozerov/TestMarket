package presentation.screens.cart.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.models.CheckedProduct
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import presentation.screens.cart.utils.getTotalProductsWord
import ru.gozerov.test_market.common.core.ic_favorite_outlined
import ru.gozerov.test_market.common.features.cart.presentation.resources.Res
import ru.gozerov.test_market.common.features.cart.presentation.resources.cart
import ru.gozerov.test_market.common.features.cart.presentation.resources.check_all
import ru.gozerov.test_market.common.features.cart.presentation.resources.place_an_order
import ru.gozerov.test_market.common.features.cart.presentation.resources.total
import theme.TestMarketTheme
import views.DefaultButton
import views.DefaultDivider

@Composable
internal fun FilledCartView(
    products: ImmutableList<CheckedProduct>,
    areAllChecked: State<Boolean>,
    onAllChecked: (value: Boolean) -> Unit,
    onChecked: (productId: Int, isChecked: Boolean) -> Unit,
    onShoppingListClicked: () -> Unit,
    onClearClicked: () -> Unit,
    onChangeAmountClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isOrderActive = products.any { product -> product.isChecked }

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

        DefaultDivider(modifier = Modifier.padding(top = 16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth()
        ) {
            Checkbox(
                modifier = Modifier.padding(horizontal = 12.dp).size(20.dp),
                colors = CheckboxDefaults.colors(
                    checkedColor = TestMarketTheme.colors.primary,
                    uncheckedColor = TestMarketTheme.colors.secondary
                ),
                checked = areAllChecked.value,
                onCheckedChange = onAllChecked
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(Res.string.check_all),
                color = TestMarketTheme.colors.text,
                fontSize = 14.sp
            )
            IconButton(onClick = onShoppingListClicked, enabled = isOrderActive) {
                Icon(
                    tint = TestMarketTheme.colors.text,
                    modifier = Modifier.size(24.dp).alpha(if (isOrderActive) 1f else 0.5f),
                    painter = painterResource(ru.gozerov.test_market.common.core.Res.drawable.ic_favorite_outlined),
                    contentDescription = null
                )
            }
            IconButton(onClick = onClearClicked, enabled = isOrderActive) {
                Icon(
                    tint = TestMarketTheme.colors.text,
                    modifier = Modifier.size(24.dp).alpha(if (isOrderActive) 1f else 0.6f),
                    imageVector = Icons.Default.Clear,
                    contentDescription = null
                )
            }
        }

        DefaultDivider()

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            println(products.toString())
            item {
                Spacer(Modifier.height(4.dp))
            }
            items(products.size) { ind ->

                val product = products[ind]
                println(product.toString())
                CheckedProductCard(
                    product = product,
                    onChecked = { value ->
                        onChecked(product.id, value)
                    },
                    onChangeAmountClicked = onChangeAmountClicked,
                    isLastItem = ind == products.size - 1
                )
            }
            if (products.isNotEmpty()) {
                item {
                    DefaultDivider()
                }
                if (isOrderActive) {
                    val checkedProducts = products.filter { product -> product.isChecked }
                    item {
                        Text(
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp).alpha(0.6f),
                            text = stringResource(Res.string.total),
                            color = TestMarketTheme.colors.text,
                            fontSize = 12.sp
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
                        ) {
                            Text(
                                text = "${checkedProducts.size} ${
                                    getTotalProductsWord(checkedProducts.size)
                                }",
                                color = TestMarketTheme.colors.text,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "${checkedProducts.sumOf { product -> product.price }} ₽",
                                color = TestMarketTheme.colors.text,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                item {
                    DefaultButton(
                        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
                            .height(48.dp),
                        onClick = {},
                        isActive = isOrderActive,
                        backgroundTint = TestMarketTheme.colors.primary
                    ) {
                        Text(
                            modifier = Modifier.alpha(if (isOrderActive) 1f else 0.6f),
                            text = stringResource(Res.string.place_an_order),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = TextUnit(0f, TextUnitType.Sp),
                            color = TestMarketTheme.colors.text
                        )
                    }
                }

            }
        }
    }
}