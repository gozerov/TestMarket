package data.di

import data.cache.ShoppingListCache
import data.cache.ShoppingListCacheImpl
import data.repositories.ShoppingListRepositoryImpl
import domain.repositories.ShoppingListRepository
import domain.usecases.AddToCartUseCase
import domain.usecases.GetProductsWithAmountUseCase
import domain.usecases.RemoveFromShoppingListUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val shoppingListDataModule = DI.Module("shoppingListDataModule") {
    bindSingleton<ShoppingListRepository> { ShoppingListRepositoryImpl(instance()) }
    bindSingleton<ShoppingListCache> { ShoppingListCacheImpl(instance()) }

    bindProvider<GetProductsWithAmountUseCase> { GetProductsWithAmountUseCase(instance()) }
    bindProvider<AddToCartUseCase> { AddToCartUseCase(instance()) }
    bindProvider<RemoveFromShoppingListUseCase> { RemoveFromShoppingListUseCase(instance()) }
}