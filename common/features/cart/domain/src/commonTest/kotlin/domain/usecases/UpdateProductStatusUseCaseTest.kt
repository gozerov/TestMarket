import domain.repositories.FakeCartRepository
import domain.usecases.UpdateProductStatusUseCase
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class UpdateProductStatusUseCaseTest {

    private val fakeCartRepository = FakeCartRepository()
    private val updateProductStatusUseCase = UpdateProductStatusUseCase(fakeCartRepository)

    @Test
    fun `invoke should update product status to checked`() = runBlocking {
        fakeCartRepository.addProductsToShoppingList(listOf(1, 2, 3))

        updateProductStatusUseCase.invoke(productId = 2, isChecked = true)

        val product = fakeCartRepository.getCart().first { it.id == 2 }
        assertTrue(product.isChecked)
    }

    @Test
    fun `invoke should update product status to unchecked`() = runBlocking {
        fakeCartRepository.addProductsToShoppingList(listOf(1, 2, 3))
        updateProductStatusUseCase.invoke(productId = 2, isChecked = true)

        updateProductStatusUseCase.invoke(productId = 2, isChecked = false)

        val product = fakeCartRepository.getCart().first { it.id == 2 }
        assertTrue(!product.isChecked)
    }

    @Test
    fun `invoke should not affect other products status`() = runBlocking {
        fakeCartRepository.addProductsToShoppingList(listOf(1, 2, 3))

        updateProductStatusUseCase.invoke(productId = 2, isChecked = true)

        val product1 = fakeCartRepository.getCart().first { it.id == 1 }
        val product3 = fakeCartRepository.getCart().first { it.id == 3 }

        assertTrue(product1.isChecked)
        assertTrue(product3.isChecked)
    }
}