package theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class TestMarketColors(
    val text: Color,
    val primary: Color,
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val secondary: Color,
    val accent: Color
)


object KMPMarketTheme {
    val colors: TestMarketColors
        @Composable
        get() = LocalTestMarketColors.current
}

val LocalTestMarketColors = staticCompositionLocalOf<TestMarketColors> {
    error("No colors provided")
}