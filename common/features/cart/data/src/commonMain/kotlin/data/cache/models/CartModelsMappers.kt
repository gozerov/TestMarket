package data.cache.models

import domain.models.CheckedProduct
import ru.gozerov.database.SelectableProduct

fun SelectableProduct.toCheckedProduct() =
    CheckedProduct(id.toInt(), name, description, image, price, 1, isSelected == 1L)

fun List<SelectableProduct>.toCheckedProductList() =
    map { product -> product.toCheckedProduct() }
