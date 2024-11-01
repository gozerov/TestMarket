package data.repositories

import data.cache.ProductsCache
import data.remote.ProductsRemote
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

        emit(productsCache.getProducts())
    }

    override suspend fun updateCart(productId: Int, isAdding: Boolean) =
        productsCache.updateCart(productId, isAdding)

    override suspend fun updateShoppingList(productId: Int, isAdding: Boolean) =
        productsCache.updateShoppingList(productId, isAdding)

}