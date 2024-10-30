package navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalNavHost = staticCompositionLocalOf<NavHostController> { error("no NavHost provided") }
