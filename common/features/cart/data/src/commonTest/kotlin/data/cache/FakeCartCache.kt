import data.cache.CartCache
import ru.gozerov.database.SelectableProduct

class FakeCartCache : CartCache {

    private val products = mutableListOf<SelectableProduct>()

    override suspend fun getCart(): List<SelectableProduct> = products.toList()

    override suspend fun removeProductsFromCart(ids: List<Int>) {
        products.removeAll { ids.contains(it.id.toInt()) }
    }

    override suspend fun updateProductStatus(id: Int, isChecked: Boolean) {
        val productIndex = products.indexOfFirst { it.id == id.toLong() }
        if (productIndex != -1) {
            val updatedProduct = products[productIndex].copy(isSelected = if (isChecked) 1 else 0)
            products[productIndex] = updatedProduct
        }
    }

    override suspend fun addProductsToShoppingList(ids: List<Int>) {
        ids.forEach { id ->
            val productIndex = products.indexOfFirst { it.id == id.toLong() }
            if (productIndex != -1) {
                val updatedProduct = products[productIndex].copy(isSelected = 1)
                products[productIndex] = updatedProduct
            }
        }
    }

    override suspend fun checkAll(isChecked: Boolean) {
        products.clear()
        products.addAll(
            products.map {
                it.copy(isSelected = if (isChecked) 1L else 0L)
            }
        )
    }

    fun addProducts(newProducts: List<SelectableProduct>) {
        products.addAll(newProducts)
    }

}
