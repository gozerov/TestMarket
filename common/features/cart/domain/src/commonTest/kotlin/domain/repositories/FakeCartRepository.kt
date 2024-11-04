package domain.repositories

import domain.models.CheckedProduct

class FakeCartRepository : CartRepository {

    private val cart = mutableListOf<CheckedProduct>()

    override suspend fun getCart(): List<CheckedProduct> {
        return cart.toList()
    }

    override suspend fun removeProductsFromCart(ids: List<Int>): List<CheckedProduct> {
        cart.removeAll { it.id in ids }
        return cart.toList()
    }

    override suspend fun addProductsToShoppingList(ids: List<Int>): List<CheckedProduct> {
        val addedProducts = ids.map { id ->
            CheckedProduct(
                id = id,
                name = "Product $id",
                description = "Description for product $id",
                image = "ImageURL for product $id",
                price = 10.0 * id,
                amount = 1,
                isChecked = true
            )
        }
        cart.addAll(addedProducts)
        return cart.toList()
    }

    override suspend fun checkAll(isChecked: Boolean): List<CheckedProduct> {
        val updatedCart = cart.map { it.copy(isChecked = isChecked) }
        cart.clear()
        cart.addAll(updatedCart)
        return cart.toList()
    }

    override suspend fun updateProductStatus(productId: Int, isChecked: Boolean) {
        val index = cart.indexOfFirst { it.id == productId }
        if (index >= 0) {
            cart[index] = cart[index].copy(isChecked = isChecked)
        }
    }
}
