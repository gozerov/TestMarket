package presentation.screens.main.models

import domain.models.ProductDTO
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

fun ProductDTO.toProduct() =
    Product(id, name, description, image, price, isInCart, isInFavorites)

fun List<ProductDTO>.toProductList(): ImmutableList<Product> =
    map { dto -> dto.toProduct() }.toImmutableList()