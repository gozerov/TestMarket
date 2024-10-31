package di

import cache.createDriver
import ktor.KtorService
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import ru.gozerov.database.AppDatabase

val coreModule = DI.Module("coreModule") {
    bindSingleton<AppDatabase> {
        AppDatabase(createDriver(instance()))
    }
    bindSingleton<KtorService> { KtorService() }
}