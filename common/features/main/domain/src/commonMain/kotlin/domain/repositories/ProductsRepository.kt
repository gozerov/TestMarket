package domain.repositories

import domain.models.ProductDTO
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getProducts(): Flow<List<ProductDTO>>

    suspend fun updateCart(productId: Int, isAdding: Boolean): Boolean

    suspend fun updateShoppingList(productId: Int, isAdding: Boolean): Boolean

}