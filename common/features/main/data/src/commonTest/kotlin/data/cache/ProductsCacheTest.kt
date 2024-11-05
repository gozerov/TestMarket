package data.cache

import data.remote.models.RemoteProduct
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ProductsCacheTest {

    private val fakeProductsCache = FakeProductsCache()

    @Test
    fun `saveProducts should save list of remote products as ProductDTO`() = runBlocking {
        val remoteProducts = listOf(
            RemoteProduct(
                id = 1,
                title = "Product 1",
                description = "Description 1",
                price = 10.0,
                image = null
            ),
            RemoteProduct(
                id = 2,
                title = "Product 2",
                description = "Description 2",
                price = 20.0,
                image = null
            )
        )

        fakeProductsCache.saveProducts(remoteProducts)

        val result = fakeProductsCache.getProducts()
        assertEquals(2, result.size)
        assertEquals("Product 1", result[0].name)
        assertEquals("Product 2", result[1].name)
    }

    @Test
    fun `updateCart should update isInCart status for a product`() = runBlocking {
        val remoteProducts = listOf(RemoteProduct(1, "Product 1", "Description 1", 10.0, null))
        fakeProductsCache.saveProducts(remoteProducts)

        fakeProductsCache.updateCart(productId = 1, isAdding = true)

        val result = fakeProductsCache.getProducts().first()
        assertTrue(result.isInCart)
    }

    @Test
    fun `updateShoppingList should update isInFavorites status for a product`() = runBlocking {
        val remoteProducts = listOf(RemoteProduct(1, "Product 1", "Description 1", 10.0, null))
        fakeProductsCache.saveProducts(remoteProducts)

        fakeProductsCache.updateShoppingList(productId = 1, isAdding = true)

        val result = fakeProductsCache.getProducts().first()
        assertTrue(result.isInFavorites)
    }

}
