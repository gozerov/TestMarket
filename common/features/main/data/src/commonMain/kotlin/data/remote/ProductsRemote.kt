package data.remote

import data.remote.models.RemoteProduct

interface ProductsRemote {

    suspend fun getProducts(): List<RemoteProduct>

}