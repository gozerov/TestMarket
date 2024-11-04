package data.di

import data.cache.CartCache
import data.cache.CartCacheImpl
import data.repositories.CartRepositoryImpl
import domain.repositories.CartRepository
import domain.usecases.AddProductsToShoppingListUseCase
import domain.usecases.CheckAllUseCase
import domain.usecases.GetCartUseCase
import domain.usecases.RemoveProductsFromCartUseCase
import domain.usecases.UpdateProductStatusUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val cartDataModule = DI.Module("cartDataModule") {
    bindSingleton<CartRepository> { CartRepositoryImpl(instance()) }
    bindSingleton<CartCache> { CartCacheImpl(instance()) }

    bindProvider<GetCartUseCase> { GetCartUseCase(instance()) }
    bindProvider<UpdateProductStatusUseCase> { UpdateProductStatusUseCase(instance()) }
    bindProvider<CheckAllUseCase> { CheckAllUseCase(instance()) }
    bindProvider<AddProductsToShoppingListUseCase> { AddProductsToShoppingListUseCase(instance()) }
    bindProvider<RemoveProductsFromCartUseCase> { RemoveProductsFromCartUseCase(instance()) }
}