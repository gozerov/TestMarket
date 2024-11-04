package data.remote

import data.remote.models.RemoteProduct
import io.ktor.client.call.body
import io.ktor.client.request.get
import ktor.KtorService

internal class ProductsRemoteImpl(private val ktorService: KtorService): ProductsRemote {

    override suspend fun getProducts(): List<RemoteProduct> {
        val response = ktorService.client.get(urlString = "https://fakestoreapi.com/products")
        return response.body<List<RemoteProduct>>().take(20)
    }

}