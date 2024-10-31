package presentation.screens.main.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import presentation.screens.main.models.Product
import ru.gozerov.test_market.common.features.main.presentation.resources.Res
import ru.gozerov.test_market.common.features.main.presentation.resources.ic_favorite_filled
import ru.gozerov.test_market.common.features.main.presentation.resources.ic_favorite_outlined
import ru.gozerov.test_market.common.features.main.presentation.resources.in_cart
import theme.TestMarketTheme
import views.DefaultButton

@Composable
fun ProductCard(
    product: Product,
    onCartClicked: () -> Unit,
    onShoppingListClicked: () -> Unit,
    isAddedToCard: State<Boolean>,
    isAddedToShoppingList: State<Boolean>,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .background(TestMarketTheme.colors.primaryBackground)
        )
    ) {
        AsyncImage(
            modifier = Modifier.size(128.dp),
            model = product.image,
            contentDescription = product.name,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = product.name,
                color = TestMarketTheme.colors.text,
                maxLines = 1,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "${product.price}₽",
                fontWeight = FontWeight.Bold,
                color = TestMarketTheme.colors.text,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                DefaultButton(modifier = Modifier.width(120.dp), onClick = onCartClicked) {
                    Text(
                        text = stringResource(Res.string.in_cart),
                        color = TestMarketTheme.colors.text,
                        fontSize = 14.sp,
                        letterSpacing = TextUnit(0f, TextUnitType.Sp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onShoppingListClicked) {
                    Icon(
                        tint =
                        if (isAddedToShoppingList.value) TestMarketTheme.colors.primary
                        else TestMarketTheme.colors.text,
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(
                            if (isAddedToShoppingList.value) Res.drawable.ic_favorite_filled
                            else Res.drawable.ic_favorite_outlined
                        ),
                        contentDescription = null
                    )
                }
            }
        }
    }
}