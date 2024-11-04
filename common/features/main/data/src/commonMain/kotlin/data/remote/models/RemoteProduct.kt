package data.remote.models

import kotlinx.serialization.Serializable

@Serializable
internal data class RemoteProduct(
    val id: Int,
    val title: String,
    val description: String?,
    val price: Double,
    val image: String?
)