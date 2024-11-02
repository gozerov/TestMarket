package domain.models

data class CheckedProduct(
    val id: Int,
    val name: String,
    val description: String?,
    val image: String?,
    val price: Double,
    val amount: Int,
    val isChecked: Boolean = false
)
