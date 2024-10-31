package domain.repositories

import domain.models.ProductDTO

interface ProductsRepository {

    suspend fun getProducts(): List<ProductDTO>

    suspend fun updateCart(productId: Int, isAdding: Boolean): Boolean

    suspend fun updateShoppingList(productId: Int, isAdding: Boolean): Boolean

}