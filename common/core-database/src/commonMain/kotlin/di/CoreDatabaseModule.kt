package di

import cache.createDriver
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import ru.gozerov.database.AppDatabase

val coreDatabaseModule = DI.Module("coreDatabaseModule") {
    bindSingleton<AppDatabase> {
        AppDatabase(createDriver(instance()))
    }
}