package domain.repositories

import domain.models.ProductWithAmount
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ShoppingListRepositoryTest {

    private val fakeShoppingListRepository = FakeShoppingListRepository()

    @Test
    fun `getProducts should return all products`() = runBlocking {
        val expectedProducts = listOf(
            ProductWithAmount(1, "Product 1", "Description 1", null, 10.0, false, 2),
            ProductWithAmount(2, "Product 2", "Description 2", null, 20.0, true, 1)
        )
        fakeShoppingListRepository.addProducts(expectedProducts)

        val result = fakeShoppingListRepository.getProducts()

        assertEquals(expectedProducts, result)
    }

    @Test
    fun `removeFromShoppingList should remove product by id`() = runBlocking {
        val product = ProductWithAmount(1, "Product 1", "Description 1", null, 10.0, false, 2)
        fakeShoppingListRepository.addProducts(listOf(product))

        fakeShoppingListRepository.removeFromShoppingList(1)

        assertTrue(fakeShoppingListRepository.getProducts().isEmpty())
    }

    @Test
    fun `addToCart should update isInCart status of product`() = runBlocking {
        val product = ProductWithAmount(1, "Product 1", "Description 1", null, 10.0, false, 2)
        fakeShoppingListRepository.addProducts(listOf(product))

        fakeShoppingListRepository.addToCart(1, true)

        val updatedProduct = fakeShoppingListRepository.getProducts().first { it.id == 1 }
        assertTrue(updatedProduct.isInCart)
    }

}
