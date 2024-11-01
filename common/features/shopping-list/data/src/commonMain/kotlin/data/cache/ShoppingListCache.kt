package data.cache

import ru.gozerov.database.ProductWithFlags

interface ShoppingListCache {

    suspend fun getShoppingListProduct(): List<ProductWithFlags>

    suspend fun updateCart(productId: Int, isAdding: Boolean)

    suspend fun removeFromShoppingList(productId: Int)

}