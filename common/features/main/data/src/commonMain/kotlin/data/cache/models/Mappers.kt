package data.cache.models

import domain.models.ProductDTO
import ru.gozerov.database.GetProductsWithFlags

fun GetProductsWithFlags.toProductDTO() = ProductDTO(
    id = id.toInt(),
    name = name,
    description = description,
    image = image,
    price = price,
    isInCart = isInCart == 1L,
    isInFavorites = isInFavorites == 1L
)