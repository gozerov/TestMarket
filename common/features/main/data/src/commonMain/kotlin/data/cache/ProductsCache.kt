package data.cache

import data.remote.models.RemoteProduct
import domain.models.ProductDTO

interface ProductsCache {

    suspend fun saveProducts(products: List<RemoteProduct>)

    suspend fun getProducts(): List<ProductDTO>

}