package data.cache

import data.remote.models.RemoteProduct
import domain.models.ProductDTO

internal interface ProductsCache {

    suspend fun saveProducts(products: List<RemoteProduct>)

    suspend fun getProducts(): List<ProductDTO>

    suspend fun updateCart(productId: Int, isAdding: Boolean)

    suspend fun updateShoppingList(productId: Int, isAdding: Boolean)

}