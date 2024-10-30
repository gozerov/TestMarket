package di

import ktor.KtorService
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val coreModule = DI.Module("coreModule") {
    bindSingleton<KtorService> { KtorService() }
}