package presentation.screens.main.models

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class MainViewState(
    val products: ImmutableList<Product> = persistentListOf()
)
