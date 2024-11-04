package domain.usecases

import domain.repositories.FakeCartRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CheckAllUseCaseTest {

    private val fakeCartRepository = FakeCartRepository()
    private val checkAllUseCase = CheckAllUseCase(fakeCartRepository)

    @Test
    fun `invoke should set isChecked to true for all products`() = runBlocking {
        fakeCartRepository.addProductsToShoppingList(listOf(1, 2, 3))

        val result = checkAllUseCase.invoke(isChecked = true)

        assertEquals(3, result.size)
        assertTrue(result.all { it.isChecked }, "Все продукты должны быть отмечены как checked")
    }

    @Test
    fun `invoke should set isChecked to false for all products`() = runBlocking {
        fakeCartRepository.addProductsToShoppingList(listOf(1, 2, 3))

        val result = checkAllUseCase.invoke(isChecked = false)

        assertEquals(3, result.size)
        assertTrue(result.all { !it.isChecked }, "Все продукты должны быть unchecked")
    }
}
