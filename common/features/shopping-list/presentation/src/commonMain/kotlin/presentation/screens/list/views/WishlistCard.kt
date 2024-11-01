package presentation.screens.list.views

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import domain.models.ProductWithAmount
import org.jetbrains.compose.resources.painterResource
import ru.gozerov.test_market.common.core.Res
import ru.gozerov.test_market.common.core.ic_cart_filled
import ru.gozerov.test_market.common.core.ic_cart_outlined
import theme.TestMarketTheme
import views.DefaultButton
import views.DefaultDivider

@Composable
fun WishlistCard(
    product: ProductWithAmount,
    onCartClicked: () -> Unit,
    onChangeAmountClicked: () -> Unit,
    onMenuClicked: () -> Unit,
    isAddedToCart: State<Boolean>,
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp).weight(1f),
                        text = product.name,
                        color = TestMarketTheme.colors.text,
                        maxLines = 2,
                        fontSize = 14.sp
                    )
                    IconButton(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        onClick = onMenuClicked
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = TestMarketTheme.colors.secondary
                        )
                    }
                }
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
                        backgroundTint = TestMarketTheme.colors.primaryBackground,
                        border = BorderStroke(1.dp, TestMarketTheme.colors.primary),
                        onClick = onChangeAmountClicked
                    ) {
                        Text("1 шт")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = onCartClicked) {
                        Icon(
                            tint =
                            if (isAddedToCart.value) TestMarketTheme.colors.primary
                            else TestMarketTheme.colors.text,
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(
                                if (isAddedToCart.value) Res.drawable.ic_cart_filled
                                else Res.drawable.ic_cart_outlined
                            ),
                            contentDescription = null
                        )
                    }
                }
                if (!isLastItem)
                    DefaultDivider()
            }
        }
    }
}