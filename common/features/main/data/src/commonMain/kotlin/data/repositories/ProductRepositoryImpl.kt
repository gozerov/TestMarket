package data.repositories

import data.cache.ProductsCache
import data.remote.ProductsRemote
import data.remote.models.toProductDTO
import domain.models.ProductDTO
import domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepositoryImpl(
    private val productsRemote: ProductsRemote,
    private val productsCache: ProductsCache
) : ProductsRepository {

    override suspend fun getProducts(): Flow<List<ProductDTO>> = flow {
        val cacheProducts = productsCache.getProducts()
        emit(cacheProducts)

        val remoteProducts = productsRemote.getProducts()
        productsCache.saveProducts(remoteProducts)

        emit(remoteProducts.map { product -> product.toProductDTO() })
    }

    override suspend fun updateCart(productId: Int, isAdding: Boolean): Boolean {
        return false
    }

    override suspend fun updateShoppingList(productId: Int, isAdding: Boolean): Boolean {
        return false
    }

}