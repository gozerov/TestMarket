package presentation.screens.main.models

import androidx.compose.runtime.Immutable

@Immutable
data class Product(
    val id: Int,
    val name: String,
    val description: String?,
    val image: String?,
    val price: Double,
    val isInCart: Boolean,
    val isInFavorites: Boolean
)
