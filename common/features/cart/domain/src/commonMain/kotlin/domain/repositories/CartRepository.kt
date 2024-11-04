package domain.repositories

import domain.models.CheckedProduct

interface CartRepository {

    suspend fun getCart(): List<CheckedProduct>

    suspend fun removeProductsFromCart(ids: List<Int>): List<CheckedProduct>

    suspend fun addProductsToShoppingList(ids: List<Int>): List<CheckedProduct>

    suspend fun checkAll(isChecked: Boolean): List<CheckedProduct>

    suspend fun updateProductStatus(productId: Int, isChecked: Boolean)

}