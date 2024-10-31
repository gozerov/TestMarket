package data.cache.models

import domain.models.ProductDTO
import ru.gozerov.database.Product

fun Product.toProductDTO() = ProductDTO(
    id = id.toInt(),
    name = name,
    description = description,
    image = image,
    price = price,
    isInCart = isInCart,
    isInFavorites = isInFavorites
)