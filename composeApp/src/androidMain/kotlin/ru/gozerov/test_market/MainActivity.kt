package ru.gozerov.test_market

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import navigation.TestMarketApp
import theme.TestMarketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            TestMarketTheme {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.light(
                        TestMarketTheme.colors.primaryBackground.toArgb(),
                        TestMarketTheme.colors.primaryBackground.toArgb()
                    ),
                    navigationBarStyle = SystemBarStyle.light(
                        TestMarketTheme.colors.primaryBackground.toArgb(),
                        TestMarketTheme.colors.primaryBackground.toArgb()
                    )
                )
                val statusBarInsets = WindowInsets.statusBars.asPaddingValues()
                val navigationBarInsets = WindowInsets.navigationBars.asPaddingValues()

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = statusBarInsets.calculateTopPadding(),
                            bottom = navigationBarInsets.calculateBottomPadding()
                        )
                ) {
                    TestMarketApp()
                }
            }
        }
    }
}