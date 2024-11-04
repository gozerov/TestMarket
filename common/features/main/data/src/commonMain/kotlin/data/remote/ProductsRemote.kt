package data.remote

import data.remote.models.RemoteProduct

internal interface ProductsRemote {

    suspend fun getProducts(): List<RemoteProduct>

}