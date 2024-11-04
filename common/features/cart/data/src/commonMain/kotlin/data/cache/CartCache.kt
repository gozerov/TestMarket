package data.cache

import ru.gozerov.database.SelectableProduct

interface CartCache {

    suspend fun getCart(): List<SelectableProduct>

    suspend fun removeProductsFromCart(ids: List<Int>)

    suspend fun updateProductStatus(id: Int, isChecked: Boolean)

    suspend fun addProductsToShoppingList(ids: List<Int>)

    suspend fun checkAll(isChecked: Boolean)

}