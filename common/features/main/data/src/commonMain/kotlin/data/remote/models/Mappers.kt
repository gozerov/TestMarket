package data.remote.models

import domain.models.ProductDTO

fun RemoteProduct.toProductDTO() = ProductDTO(
    id = id,
    name = title,
    description = description,
    image = image,
    price = price,
    isInCart = false,
    isInFavorites = false
)