package data.di

import data.cache.ProductsCache
import data.cache.ProductsCacheImpl
import data.remote.ProductsRemote
import data.remote.ProductsRemoteImpl
import data.repositories.ProductRepositoryImpl
import domain.repositories.ProductsRepository
import domain.usecases.GetProductsUseCase
import domain.usecases.UpdateCartUseCase
import domain.usecases.UpdateShoppingListUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val mainDataModule = DI.Module("marketDataModule") {
    bindSingleton<ProductsRemote> { ProductsRemoteImpl(instance()) }
    bindSingleton<ProductsRepository> { ProductRepositoryImpl(instance(), instance()) }

    bindSingleton<ProductsCache> { ProductsCacheImpl(instance()) }

    bindProvider<GetProductsUseCase> { GetProductsUseCase(instance()) }
    bindProvider<UpdateCartUseCase> { UpdateCartUseCase(instance()) }
    bindProvider<UpdateShoppingListUseCase> { UpdateShoppingListUseCase(instance()) }
}