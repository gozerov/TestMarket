package data.repositories

import data.remote.ProductsRemote
import data.remote.models.toProductDTO
import domain.models.ProductDTO
import domain.repositories.ProductsRepository

class ProductRepositoryImpl(
    private val productsRemote: ProductsRemote
) : ProductsRepository {

    override suspend fun getProducts(): List<ProductDTO> =
        productsRemote.getProducts().map { product -> product.toProductDTO() }

    override suspend fun updateCart(productId: Int, isAdding: Boolean): Boolean {
        return false
    }

    override suspend fun updateShoppingList(productId: Int, isAdding: Boolean): Boolean {
        return false
    }

}