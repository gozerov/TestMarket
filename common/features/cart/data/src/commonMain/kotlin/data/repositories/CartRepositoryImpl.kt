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
        cartCache.removeProductsFromCart(ids)
        return getCart()
    }

    override suspend fun checkAll(isChecked: Boolean): List<CheckedProduct> {
        cartCache.checkAll(isChecked)
        val products = getCart()
        println(products)
        return products
    }

    override suspend fun updateProductStatus(productId: Int, isChecked: Boolean) =
        cartCache.updateProductStatus(productId, isChecked)

}