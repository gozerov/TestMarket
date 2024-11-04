package data.repositories

import data.cache.ShoppingListCache
import data.cache.models.toProductWithAmountList
import domain.models.ProductWithAmount
import domain.repositories.ShoppingListRepository

internal class ShoppingListRepositoryImpl(
    private val shoppingListCache: ShoppingListCache
) : ShoppingListRepository {

    override suspend fun getProducts(): List<ProductWithAmount> =
        shoppingListCache.getShoppingListProduct().toProductWithAmountList()

    override suspend fun removeFromShoppingList(productId: Int) {
        shoppingListCache.removeFromShoppingList(productId)
    }

    override suspend fun addToCart(productId: Int, isAdding: Boolean) {
        shoppingListCache.updateCart(productId, isAdding)
    }

}