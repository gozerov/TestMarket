package views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import theme.TestMarketTheme

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    backgroundTint: Color = TestMarketTheme.colors.secondary,
    border: BorderStroke? = null,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundTint),
        shape = RoundedCornerShape(4.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        border = border,
        modifier = modifier,
        onClick = onClick,
        content = content
    )
}