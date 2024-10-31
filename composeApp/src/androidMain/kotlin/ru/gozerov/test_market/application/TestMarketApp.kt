package ru.gozerov.test_market.application

import PlatformConfiguration
import PlatformSDK
import android.app.Application

class TestMarketApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initPlatformSDK()
    }

}

fun TestMarketApp.initPlatformSDK() =
    PlatformSDK.init(
        platformConfiguration = PlatformConfiguration(context = applicationContext)
    )