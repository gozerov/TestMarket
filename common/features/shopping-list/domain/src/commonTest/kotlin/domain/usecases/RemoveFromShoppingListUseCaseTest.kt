package domain.usecases

import domain.models.ProductWithAmount
import domain.repositories.FakeShoppingListRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class RemoveFromShoppingListUseCaseTest {

    private val fakeShoppingListRepository = FakeShoppingListRepository()
    private val removeFromShoppingListUseCase =
        RemoveFromShoppingListUseCase(fakeShoppingListRepository)

    @Test
    fun `invoke should remove product from shopping list by id`() = runBlocking {
        val product = ProductWithAmount(1, "Product 1", "Description 1", null, 10.0, false, 2)
        fakeShoppingListRepository.addProducts(listOf(product))

        removeFromShoppingListUseCase.invoke(1)

        assertTrue(fakeShoppingListRepository.getProducts().isEmpty())
    }

}
