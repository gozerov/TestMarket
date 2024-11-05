package data.remote

import data.remote.models.RemoteProduct
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FakeProductsRemoteTest {

    private val fakeProductsRemote = FakeProductsRemote()

    @Test
    fun `getProducts should return empty list when no products are set`() = runBlocking {
        val result = fakeProductsRemote.getProducts()
        assertTrue(result.isEmpty(), "Initially, the list of products should be empty.")
    }

    @Test
    fun `getProducts should return list of remote products when products are set`() = runBlocking {
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

        fakeProductsRemote.setProducts(remoteProducts)
        val result = fakeProductsRemote.getProducts()

        assertEquals(2, result.size)
        assertEquals("Product 1", result[0].title)
        assertEquals("Product 2", result[1].title)
        assertEquals(10.0, result[0].price)
        assertEquals(20.0, result[1].price)
    }

    @Test
    fun `setProducts should overwrite previously set products`() = runBlocking {
        val initialProducts = listOf(
            RemoteProduct(
                id = 1,
                title = "Initial Product",
                description = "Initial Description",
                price = 5.0,
                image = null
            )
        )
        val newProducts = listOf(
            RemoteProduct(
                id = 2,
                title = "New Product",
                description = "New Description",
                price = 15.0,
                image = null
            )
        )

        fakeProductsRemote.setProducts(initialProducts)
        assertEquals(1, fakeProductsRemote.getProducts().size)

        fakeProductsRemote.setProducts(newProducts)
        val result = fakeProductsRemote.getProducts()

        assertEquals(1, result.size)
        assertEquals("New Product", result[0].title)
        assertEquals(15.0, result[0].price)
    }

}
