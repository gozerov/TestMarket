package ru.gozerov.test_market.application

import PlatformConfiguration
import PlatformSDK
import android.app.Application

class KMPMarketApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initPlatformSDK()
    }

}

fun KMPMarketApp.initPlatformSDK() =
    PlatformSDK.init(
        platformConfiguration = PlatformConfiguration(context = applicationContext)
    )