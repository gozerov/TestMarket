package presentation.screens.main.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import presentation.screens.main.models.Product
import ru.gozerov.test_market.common.core.ic_cart_outlined
import ru.gozerov.test_market.common.core.ic_favorite_outlined
import ru.gozerov.test_market.common.features.main.presentation.resources.Res
import ru.gozerov.test_market.common.features.main.presentation.resources.ic_favorite_filled
import theme.TestMarketTheme
import views.DefaultButton
import views.DefaultDivider

@Composable
fun ProductCard(
    product: Product,
    onCartClicked: () -> Unit,
    onShoppingListClicked: () -> Unit,
    isLastItem: Boolean,
    modifier: Modifier = Modifier
) {

    Column {
        Row(
            modifier = modifier.then(
                Modifier
                    .fillMaxWidth()
                    .background(TestMarketTheme.colors.primaryBackground)
            )
        ) {
            AsyncImage(
                modifier = Modifier.padding(start = 16.dp).size(80.dp),
                model = product.image,
                contentDescription = product.name,
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = product.name,
                    color = TestMarketTheme.colors.text,
                    maxLines = 1,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "${product.price}₽",
                    fontWeight = FontWeight.Bold,
                    color = TestMarketTheme.colors.text,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    DefaultButton(
                        modifier = Modifier.width(80.dp),
                        backgroundTint =
                        if (product.isInCart) TestMarketTheme.colors.primaryBackground
                        else TestMarketTheme.colors.primary,
                        border =
                        if (product.isInCart) BorderStroke(1.dp, TestMarketTheme.colors.primary)
                        else null,
                        onClick = onCartClicked
                    ) {
                        Icon(
                            tint =
                            if (product.isInCart) TestMarketTheme.colors.primary
                            else TestMarketTheme.colors.primaryBackground,
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(ru.gozerov.test_market.common.core.Res.drawable.ic_cart_outlined),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = onShoppingListClicked) {
                        Icon(
                            tint =
                            if (product.isInFavorites) TestMarketTheme.colors.primary
                            else TestMarketTheme.colors.text,
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(
                                if (product.isInFavorites) Res.drawable.ic_favorite_filled
                                else ru.gozerov.test_market.common.core.Res.drawable.ic_favorite_outlined
                            ),
                            contentDescription = null
                        )
                    }
                }
                if (!isLastItem)
                    DefaultDivider(modifier = Modifier.padding(start = 16.dp, top = 8.dp))
            }
        }
    }
}