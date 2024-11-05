package data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class RemoteProduct(
    val id: Int,
    val title: String,
    val description: String?,
    val price: Double,
    val image: String?
)