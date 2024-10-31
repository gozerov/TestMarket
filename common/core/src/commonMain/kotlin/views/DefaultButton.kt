package views

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.TestMarketTheme

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = TestMarketTheme.colors.secondary),
        shape = RoundedCornerShape(4.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        modifier = modifier,
        onClick = onClick,
        content = content
    )
}