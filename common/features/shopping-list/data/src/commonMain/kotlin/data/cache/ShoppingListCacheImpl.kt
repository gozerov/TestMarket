package data.cache

import ru.gozerov.database.AppDatabase
import ru.gozerov.database.ProductWithFlags

class ShoppingListCacheImpl(
    private val appDatabase: AppDatabase
) : ShoppingListCache {

    override suspend fun getShoppingListProduct(): List<ProductWithFlags> =
        appDatabase.productsQueries.ProductWithFlags().executeAsList()
            .filter { product -> product.isInFavorites == 1L }


    override suspend fun updateCart(productId: Int, isAdding: Boolean) {
        if (isAdding)
            appDatabase.cartQueries.insertIntoCart(productId.toLong(), 1L)
        else
            appDatabase.cartQueries.removeFromCart(productId.toLong())
    }

    override suspend fun removeFromShoppingList(productId: Int) {
        appDatabase.shopping_listQueries.removeFromShoppingList(productId.toLong())
    }

}