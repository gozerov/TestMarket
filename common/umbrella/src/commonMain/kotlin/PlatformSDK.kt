import cache.DriverFactory
import data.di.mainDataModule
import data.di.shoppingListDataModule
import di.Injector
import di.coreModule
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.direct

object PlatformSDK {

    fun init(
        platformConfiguration: PlatformConfiguration,
        driverFactory: DriverFactory
    ) {
        val umbrellaModule = DI.Module(
            name = "umbrella",
            init = {
                bindSingleton<PlatformConfiguration> { platformConfiguration }
                bindSingleton<DriverFactory> { driverFactory }
            }
        )

        Injector.createDependencies(
            DI {
                importAll(
                    umbrellaModule,
                    coreModule,
                    mainDataModule,
                    shoppingListDataModule
                )
            }.direct
        )
    }

}