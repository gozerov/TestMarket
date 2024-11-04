package presentation.screens.list.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.models.ProductWithAmount
import org.jetbrains.compose.resources.stringResource
import ru.gozerov.test_market.common.features.`shopping-list`.presentation.resources.Res
import ru.gozerov.test_market.common.features.`shopping-list`.presentation.resources.remove
import theme.TestMarketTheme

@Composable
internal fun RemoveFromListSheetContent(
    product: ProductWithAmount,
    onRemoveClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp, top = 4.dp),
            text = product.name,
            color = TestMarketTheme.colors.text,
            maxLines = 2,
            fontSize = 14.sp
        )
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onRemoveClicked
                ),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Icon(
                modifier = Modifier.padding(start = 16.dp).size(24.dp),
                imageVector = Icons.Default.Clear,
                tint = TestMarketTheme.colors.error,
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 24.dp),
                text = stringResource(Res.string.remove),
                fontSize = 14.sp,
                color = TestMarketTheme.colors.error
            )
        }
    }
}