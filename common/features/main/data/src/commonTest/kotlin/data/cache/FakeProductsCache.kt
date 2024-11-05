package data.cache

import data.remote.models.RemoteProduct
import domain.models.ProductDTO

class FakeProductsCache : ProductsCache {

    private val products = mutableListOf<ProductDTO>()

    override suspend fun saveProducts(products: List<RemoteProduct>) {
        this.products.clear()
        this.products.addAll(
            products.map {
                ProductDTO(
                    id = it.id,
                    name = it.title,
                    description = it.description,
                    image = it.image,
                    price = it.price,
                    isInCart = false,
                    isInFavorites = false
                )
            }
        )
    }

    override suspend fun getProducts(): List<ProductDTO> = products.toList()

    override suspend fun updateCart(productId: Int, isAdding: Boolean) {
        val productIndex = products.indexOfFirst { it.id == productId }
        if (productIndex != -1) {
            val updatedProduct = products[productIndex].copy(isInCart = isAdding)
            products[productIndex] = updatedProduct
        }
    }

    override suspend fun updateShoppingList(productId: Int, isAdding: Boolean) {
        val productIndex = products.indexOfFirst { it.id == productId }
        if (productIndex != -1) {
            val updatedProduct = products[productIndex].copy(isInFavorites = isAdding)
            products[productIndex] = updatedProduct
        }
    }

}
