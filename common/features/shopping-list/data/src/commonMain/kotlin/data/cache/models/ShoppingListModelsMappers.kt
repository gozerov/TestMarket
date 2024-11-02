package data.cache.models

import domain.models.ProductWithAmount
import ru.gozerov.database.ProductWithFlags

fun ProductWithFlags.toProductWithAmount() =
    ProductWithAmount(id.toInt(), name, description, image, price, isInCart == 1L, 1)

fun List<ProductWithFlags>.toProductWithAmountList() =
    map { product -> product.toProductWithAmount() }
