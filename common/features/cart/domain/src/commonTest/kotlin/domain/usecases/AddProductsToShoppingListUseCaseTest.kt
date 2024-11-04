package domain.usecases

import domain.repositories.FakeCartRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AddProductsToShoppingListUseCaseTest {

    private val fakeCartRepository = FakeCartRepository()
    private val addProductsToShoppingListUseCase =
        AddProductsToShoppingListUseCase(fakeCartRepository)

    @Test
    fun `invoke should add products to shopping list and return checked products`() = runBlocking {
        val productIds = listOf(1, 2, 3)

        val result = addProductsToShoppingListUseCase.invoke(productIds)

        assertEquals(3, result.size)
        assertTrue(result.all { it.isChecked }, "Все продукты должны быть отмечены как checked")

        productIds.forEachIndexed { index, id ->
            assertEquals(id, result[index].id)
            assertEquals("Product $id", result[index].name)
            assertEquals(10.0 * id, result[index].price)
        }
    }
}
