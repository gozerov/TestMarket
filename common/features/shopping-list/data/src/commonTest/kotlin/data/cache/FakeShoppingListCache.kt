package data.cache

import ru.gozerov.database.ProductWithFlags

class FakeShoppingListCache : ShoppingListCache {

    private var products = mutableListOf<ProductWithFlags>()

    override suspend fun getShoppingListProduct(): List<ProductWithFlags> = products.toList()

    override suspend fun updateCart(productId: Int, isAdding: Boolean) {
        val productIndex = products.indexOfFirst { it.id == productId.toLong() }
        if (productIndex != -1) {
            val updatedProduct = products[productIndex].copy(isInCart = if (isAdding) 1 else 0)
            products[productIndex] = updatedProduct
        }
    }

    override suspend fun removeFromShoppingList(productId: Int) {
        products.removeAll { it.id == productId.toLong() }
    }

    fun addProducts(newProducts: List<ProductWithFlags>) {
        products.addAll(newProducts)
    }

}
