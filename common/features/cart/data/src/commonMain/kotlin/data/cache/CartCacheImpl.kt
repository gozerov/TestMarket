package data.cache

import ru.gozerov.database.AppDatabase
import ru.gozerov.database.SelectableProduct

class CartCacheImpl(
    private val appDatabase: AppDatabase
) : CartCache {

    override suspend fun getCart(): List<SelectableProduct> =
        appDatabase.productsQueries.SelectableProduct().executeAsList()

    override suspend fun removeProductsFromCart(ids: List<Int>) {
        val idsAsString = ids.map { id -> id.toLong() }
        appDatabase.cartQueries.deleteFromCartByIds(idsAsString)
    }

    override suspend fun updateProductStatus(id: Int, isChecked: Boolean) {
        appDatabase.cartQueries.insertIntoCart(id.toLong(), if (isChecked) 1L else 0L)
    }

    override suspend fun addProductsToShoppingList(ids: List<Int>) {
        appDatabase.transaction {
            ids.forEach { productId ->
                appDatabase.shopping_listQueries.insertIntoShoppingListIfNotExists(productId.toLong())
            }
        }
    }

    override suspend fun checkAll(isChecked: Boolean) {
        appDatabase.cartQueries.updateAllSelectionStatus(if (isChecked) 1L else 0L)
    }

}