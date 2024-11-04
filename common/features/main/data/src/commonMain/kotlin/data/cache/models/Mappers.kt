package data.cache.models

import domain.models.ProductDTO
import ru.gozerov.database.ProductWithFlags

internal fun ProductWithFlags.toProductDTO() = ProductDTO(
    id = id.toInt(),
    name = name,
    description = description,
    image = image,
    price = price,
    isInCart = isInCart == 1L,
    isInFavorites = isInFavorites == 1L
)