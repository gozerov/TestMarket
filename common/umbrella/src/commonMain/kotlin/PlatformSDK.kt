import cache.DriverFactory
import data.di.cartDataModule
import data.di.mainDataModule
import data.di.shoppingListDataModule
import di.Injector
import di.coreModule
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.direct
import views.ElevationResolver

object PlatformSDK {

    fun init(
        platformConfiguration: PlatformConfiguration,
        driverFactory: DriverFactory,
        elevationResolver: ElevationResolver
    ) {
        val umbrellaModule = DI.Module(
            name = "umbrella",
            init = {
                bindSingleton<PlatformConfiguration> { platformConfiguration }
                bindSingleton<DriverFactory> { driverFactory }
                bindSingleton<ElevationResolver> { elevationResolver }
            }
        )

        Injector.createDependencies(
            DI {
                importAll(
                    umbrellaModule,
                    coreModule,
                    mainDataModule,
                    shoppingListDataModule,
                    cartDataModule
                )
            }.direct
        )
    }

}