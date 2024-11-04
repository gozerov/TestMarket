package views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import theme.TestMarketTheme

@Composable
fun DefaultDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier.then(Modifier.fillMaxWidth()),
        color = TestMarketTheme.colors.secondary
    )
}