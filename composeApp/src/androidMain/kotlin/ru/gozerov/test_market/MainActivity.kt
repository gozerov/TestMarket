package ru.gozerov.test_market

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import navigation.TestMarketApp
import theme.TestMarketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestMarketTheme {
                TestMarketApp()
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}