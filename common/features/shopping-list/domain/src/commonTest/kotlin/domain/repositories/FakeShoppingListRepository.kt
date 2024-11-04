package domain.repositories

import domain.models.ProductWithAmount

class FakeShoppingListRepository : ShoppingListRepository {

    private val products = mutableListOf<ProductWithAmount>()

    override suspend fun getProducts(): List<ProductWithAmount> = products.toList()

    override suspend fun removeFromShoppingList(productId: Int) {
        products.removeAll { it.id == productId }
    }

    override suspend fun addToCart(productId: Int, isAdding: Boolean) {
        val productIndex = products.indexOfFirst { it.id == productId }
        if (productIndex != -1) {
            val updatedProduct = products[productIndex].copy(isInCart = isAdding)
            products[productIndex] = updatedProduct
        }
    }

    fun addProducts(newProducts: List<ProductWithAmount>) {
        products.addAll(newProducts)
    }

}
