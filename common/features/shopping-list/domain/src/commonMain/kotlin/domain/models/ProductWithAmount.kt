package domain.models

data class ProductWithAmount(
    val id: Int,
    val name: String,
    val description: String?,
    val image: String?,
    val price: Double,
    val isInCart: Boolean,
    val amount: Int
)
