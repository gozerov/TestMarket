package domain.repositories

import domain.models.ProductWithAmount

interface ShoppingListRepository {

    suspend fun getProducts() : List<ProductWithAmount>

    suspend fun removeFromShoppingList(productId: Int)

    suspend fun addToCart(productId: Int, isAdding: Boolean)

}