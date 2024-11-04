package presentation.screens.cart.utils

internal fun getTotalProductsWord(count: Int): String {
    val lastDigit = count % 10
    val lastTwoDigits = count % 100

    return when {
        lastTwoDigits in 11..19 -> "товаров"
        lastDigit == 1 -> "товар"
        lastDigit in 2..4 -> "товара"
        else -> "товаров"
    }
}