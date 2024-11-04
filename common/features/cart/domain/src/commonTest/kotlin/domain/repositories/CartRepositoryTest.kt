import domain.repositories.FakeCartRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CartRepositoryTest {

    private val cartRepository = FakeCartRepository()

    @Test
    fun `add products to shopping list`() = runBlocking {
        val productIds = listOf(1, 2, 3)
        val result = cartRepository.addProductsToShoppingList(productIds)

        assertEquals(3, result.size)
        assertTrue(result.all { it.isChecked })
    }

    @Test
    fun `remove products from cart`() = runBlocking {
        cartRepository.addProductsToShoppingList(listOf(1, 2, 3))
        val result = cartRepository.removeProductsFromCart(listOf(2))

        assertEquals(2, result.size)
        assertTrue(result.none { it.id == 2 })
    }

    @Test
    fun `update product status`() = runBlocking {
        cartRepository.addProductsToShoppingList(listOf(1))
        cartRepository.updateProductStatus(1, isChecked = true)

        val product = cartRepository.getCart().first()
        assertTrue(product.isChecked)
    }

    @Test
    fun `check all products`() = runBlocking {
        cartRepository.addProductsToShoppingList(listOf(1, 2))
        val result = cartRepository.checkAll(isChecked = false)

        assertTrue(result.all { !it.isChecked })
    }
}
