import domain.repositories.FakeCartRepository
import domain.usecases.GetCartUseCase
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class GetCartUseCaseTest {

    private val fakeCartRepository = FakeCartRepository()
    private val getCartUseCase = GetCartUseCase(fakeCartRepository)

    @Test
    fun `invoke should return current cart products`() = runBlocking {
        val productIds = listOf(1, 2, 3)
        fakeCartRepository.addProductsToShoppingList(productIds)

        val result = getCartUseCase.invoke()

        assertEquals(3, result.size)
        assertEquals(1, result[0].id)
        assertEquals(2, result[1].id)
        assertEquals(3, result[2].id)
    }

    @Test
    fun `invoke should return empty list if cart is empty`() = runBlocking {
        val result = getCartUseCase.invoke()

        assertEquals(0, result.size)
    }
}