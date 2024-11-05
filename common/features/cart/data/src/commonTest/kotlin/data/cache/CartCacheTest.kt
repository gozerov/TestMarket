package data.cache

import FakeCartCache
import kotlinx.coroutines.runBlocking
import ru.gozerov.database.SelectableProduct
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CartCacheTest {

    private val fakeCartCache = FakeCartCache()

    @Test
    fun `getCart should return all products in cart`() = runBlocking {
        val expectedProducts = listOf(
            SelectableProduct(1, "Product 1", "Description 1", null, 10.0, 0),
            SelectableProduct(2, "Product 2", "Description 2", null, 20.0, 1)
        )
        fakeCartCache.addProducts(expectedProducts)

        val result = fakeCartCache.getCart()

        assertEquals(expectedProducts, result)
    }

    @Test
    fun `removeProductsFromCart should remove products by ids`() = runBlocking {
        val products = listOf(
            SelectableProduct(1, "Product 1", "Description 1", null, 10.0, 0),
            SelectableProduct(2, "Product 2", "Description 2", null, 20.0, 1)
        )
        fakeCartCache.addProducts(products)

        fakeCartCache.removeProductsFromCart(listOf(1))

        val result = fakeCartCache.getCart()
        assertEquals(1, result.size)
        assertEquals(2, result.first().id)
    }

    @Test
    fun `updateProductStatus should change isSelected status of a product`() = runBlocking {
        val product = SelectableProduct(1, "Product 1", "Description 1", null, 10.0, 0)
        fakeCartCache.addProducts(listOf(product))

        fakeCartCache.updateProductStatus(1, isChecked = true)

        val updatedProduct = fakeCartCache.getCart().first()
        assertEquals(1, updatedProduct.isSelected)
    }

    @Test
    fun `addProductsToShoppingList should set isSelected to 1 for specified product ids`() =
        runBlocking {
            val products = listOf(
                SelectableProduct(1, "Product 1", "Description 1", null, 10.0, 0),
                SelectableProduct(2, "Product 2", "Description 2", null, 20.0, 0)
            )
            fakeCartCache.addProducts(products)

            fakeCartCache.addProductsToShoppingList(listOf(1))

            val result = fakeCartCache.getCart()
            assertEquals(1, result[0].isSelected)
            assertEquals(0, result[1].isSelected)
        }

    @Test
    fun `checkAll should update isSelected for all products`() = runBlocking {
        val products = listOf(
            SelectableProduct(1, "Product 1", "Description 1", null, 10.0, 0),
            SelectableProduct(2, "Product 2", "Description 2", null, 20.0, 0)
        )
        fakeCartCache.addProducts(products)

        fakeCartCache.checkAll(isChecked = true)

        val result = fakeCartCache.getCart()
        assertTrue(result.all { it.isSelected == 1L })
    }
}
