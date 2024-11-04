package views

import androidx.compose.ui.unit.Dp

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class ElevationResolver {

    fun getBottomBarElevation(): Dp

}