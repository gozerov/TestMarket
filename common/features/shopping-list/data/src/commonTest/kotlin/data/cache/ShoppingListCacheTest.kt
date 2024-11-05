package data.cache

import kotlinx.coroutines.runBlocking
import ru.gozerov.database.ProductWithFlags
import kotlin.test.Test
import kotlin.test.assertEquals

class FakeShoppingListCacheTest {

    private val fakeShoppingListCache = FakeShoppingListCache()

    @Test
    fun `getShoppingListProduct should return all products in shopping list`() = runBlocking {
        val expectedProducts = listOf(
            ProductWithFlags(1, "Product 1", "Description 1", null, 10.0, 0, 1),
            ProductWithFlags(2, "Product 2", "Description 2", null, 20.0, 1, 0)
        )
        fakeShoppingListCache.addProducts(expectedProducts)

        val result = fakeShoppingListCache.getShoppingListProduct()

        assertEquals(expectedProducts, result)
    }

    @Test
    fun `updateCart should change isInCart status of a product`() = runBlocking {
        val product = ProductWithFlags(1, "Product 1", "Description 1", null, 10.0, 0, 1)
        fakeShoppingListCache.addProducts(listOf(product))

        fakeShoppingListCache.updateCart(productId = 1, isAdding = true)

        val updatedProduct = fakeShoppingListCache.getShoppingListProduct().first()
        assertEquals(1, updatedProduct.isInCart)
    }

    @Test
    fun `removeFromShoppingList should remove product by id`() = runBlocking {
        val products = listOf(
            ProductWithFlags(1, "Product 1", "Description 1", null, 10.0, 0, 1),
            ProductWithFlags(2, "Product 2", "Description 2", null, 20.0, 1, 0)
        )
        fakeShoppingListCache.addProducts(products)

        fakeShoppingListCache.removeFromShoppingList(productId = 1)

        val result = fakeShoppingListCache.getShoppingListProduct()
        assertEquals(1, result.size)
        assertEquals(2, result.first().id)
    }
}