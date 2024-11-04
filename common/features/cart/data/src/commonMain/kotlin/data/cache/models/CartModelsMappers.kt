package data.cache.models

import domain.models.CheckedProduct
import ru.gozerov.database.SelectableProduct

internal fun SelectableProduct.toCheckedProduct() =
    CheckedProduct(id.toInt(), name, description, image, price, 1, isSelected == 1L)

internal fun List<SelectableProduct>.toCheckedProductList() =
    map { product -> product.toCheckedProduct() }
