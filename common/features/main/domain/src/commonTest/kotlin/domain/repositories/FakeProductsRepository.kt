package domain.repositories

import domain.models.ProductDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeProductsRepository : ProductsRepository {

    private val products = mutableListOf<ProductDTO>()
    private val productsFlow = MutableStateFlow<List<ProductDTO>>(products)

    override suspend fun getProducts(): Flow<List<ProductDTO>> = productsFlow.asStateFlow()

    override suspend fun updateCart(productId: Int, isAdding: Boolean) {
        val productIndex = products.indexOfFirst { it.id == productId }
        if (productIndex != -1) {
            val updatedProduct = products[productIndex].copy(isInCart = isAdding)
            products[productIndex] = updatedProduct
            productsFlow.value = products.toList()
        }
    }

    override suspend fun updateShoppingList(productId: Int, isAdding: Boolean) {
        val productIndex = products.indexOfFirst { it.id == productId }
        if (productIndex != -1) {
            val updatedProduct = products[productIndex].copy(isInFavorites = isAdding)
            products[productIndex] = updatedProduct
            productsFlow.value = products.toList()
        }
    }

    fun addProducts(newProducts: List<ProductDTO>) {
        products.addAll(newProducts)
        productsFlow.value = products.toList()
    }

}
