package ru.gozerov.test_market.application

import PlatformConfiguration
import PlatformSDK
import android.app.Application
import cache.DriverFactory
import views.ElevationResolver

class TestMarketApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initPlatformSDK()
    }

}

fun TestMarketApp.initPlatformSDK() =
    PlatformSDK.init(
        driverFactory = DriverFactory(context = applicationContext),
        platformConfiguration = PlatformConfiguration(context = applicationContext),
        elevationResolver = ElevationResolver()
    )