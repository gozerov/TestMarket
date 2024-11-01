package presentation.screens.list.models

import androidx.compose.runtime.Immutable
import domain.models.ProductWithAmount
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class ShoppingListViewState(
    val products: ImmutableList<ProductWithAmount> = persistentListOf(),
    val summaryPrice: Int = 0
)
