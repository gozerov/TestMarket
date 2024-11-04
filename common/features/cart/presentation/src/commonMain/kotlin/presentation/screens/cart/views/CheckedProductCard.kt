package presentation.screens.cart.views

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
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import domain.models.CheckedProduct
import theme.TestMarketTheme
import views.DefaultButton
import views.DefaultDivider

@Composable
internal fun CheckedProductCard(
    product: CheckedProduct,
    onChangeAmountClicked: () -> Unit,
    onChecked: (value: Boolean) -> Unit,
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
            Checkbox(
                modifier = Modifier.padding(start = 20.dp, end = 8.dp).size(20.dp),
                colors = CheckboxDefaults.colors(
                    checkedColor = TestMarketTheme.colors.primary,
                    uncheckedColor = TestMarketTheme.colors.secondary
                ),
                checked = product.isChecked,
                onCheckedChange = onChecked
            )
            AsyncImage(
                modifier = Modifier.padding(start = 8.dp).size(80.dp),
                model = product.image,
                contentDescription = product.name,
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = product.name,
                    color = TestMarketTheme.colors.text,
                    maxLines = 2,
                    fontSize = 14.sp
                )


                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
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
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "${product.price}₽",
                        fontWeight = FontWeight.Bold,
                        color = TestMarketTheme.colors.text,
                        fontSize = 14.sp
                    )
                }

                if (!isLastItem)
                    DefaultDivider(modifier = Modifier.padding(start = 16.dp, top = 8.dp))
            }
        }
    }
}