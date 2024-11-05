package data.remote

import data.remote.models.RemoteProduct

class FakeProductsRemote : ProductsRemote {

    private val remoteProducts = mutableListOf<RemoteProduct>()

    fun setProducts(products: List<RemoteProduct>) {
        remoteProducts.clear()
        remoteProducts.addAll(products)
    }

    override suspend fun getProducts(): List<RemoteProduct> = remoteProducts.toList()

}