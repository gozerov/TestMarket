package presentation.screens.cart.models

import domain.models.CheckedProduct
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class CartViewState(
    val products: ImmutableList<CheckedProduct> = persistentListOf()
)
