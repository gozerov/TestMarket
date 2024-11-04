import domain.repositories.FakeCartRepository
import domain.usecases.RemoveProductsFromCartUseCase
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RemoveProductsFromCartUseCaseTest {

    private val fakeCartRepository = FakeCartRepository()
    private val removeProductsFromCartUseCase = RemoveProductsFromCartUseCase(fakeCartRepository)

    @Test
    fun `invoke should remove products from cart and return updated list`() = runBlocking {
        fakeCartRepository.addProductsToShoppingList(listOf(1, 2, 3))

        val result = removeProductsFromCartUseCase.invoke(listOf(2))

        assertEquals(2, result.size)
        assertTrue(result.none { it.id == 2 })
    }

    @Test
    fun `invoke should return the same list if no products are removed`() = runBlocking {
        fakeCartRepository.addProductsToShoppingList(listOf(1, 2, 3))

        val result = removeProductsFromCartUseCase.invoke(listOf(4))

        assertEquals(3, result.size)
        assertTrue(result.any { it.id == 1 })
        assertTrue(result.any { it.id == 2 })
        assertTrue(result.any { it.id == 3 })
    }

    @Test
    fun `invoke should return empty list if all products are removed`() = runBlocking {
        fakeCartRepository.addProductsToShoppingList(listOf(1, 2, 3))

        removeProductsFromCartUseCase.invoke(listOf(1, 2, 3))

        val result = fakeCartRepository.getCart()

        assertEquals(0, result.size)
    }
}