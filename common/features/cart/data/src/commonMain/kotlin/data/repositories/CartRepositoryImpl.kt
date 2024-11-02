package data.repositories

import data.cache.CartCache
import data.cache.models.toCheckedProductList
import domain.models.CheckedProduct
import domain.repositories.CartRepository

class CartRepositoryImpl(
    private val cartCache: CartCache
) : CartRepository {

    override suspend fun getCart(): List<CheckedProduct> =
        cartCache.getCart().toCheckedProductList()

    override suspend fun removeProductsFromCart(ids: List<Int>): List<CheckedProduct> {
        cartCache.removeProductsFromCart(ids)
        return getCart()
    }

    override suspend fun addProductsToShoppingList(ids: List<Int>): List<CheckedProduct> {
        cartCache.addProductsToShoppingList(ids)
        return getCart()
    }

    override suspend fun updateProductStatus(productId: Int, isChecked: Boolean) =
        cartCache.updateProductStatus(productId, isChecked)

}