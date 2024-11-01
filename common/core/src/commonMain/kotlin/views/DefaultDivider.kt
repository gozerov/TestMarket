package views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.TestMarketTheme

@Composable
fun DefaultDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier.then(Modifier.padding(start = 16.dp, top = 8.dp)).fillMaxWidth(),
        color = TestMarketTheme.colors.secondary
    )
}