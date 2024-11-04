package domain.usecases

import domain.models.ProductWithAmount
import domain.repositories.FakeShoppingListRepository
import domain.usecases.GetProductsWithAmountUseCase
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class GetProductsWithAmountUseCaseTest {

    private val fakeShoppingListRepository = FakeShoppingListRepository()
    private val getProductsWithAmountUseCase =
        GetProductsWithAmountUseCase(fakeShoppingListRepository)

    @Test
    fun `invoke should return all products with their amounts`() = runBlocking {
        val expectedProducts = listOf(
            ProductWithAmount(1, "Product 1", "Description 1", null, 10.0, false, 2),
            ProductWithAmount(2, "Product 2", "Description 2", null, 20.0, true, 1)
        )
        fakeShoppingListRepository.addProducts(expectedProducts)

        val result = getProductsWithAmountUseCase.invoke()

        assertEquals(expectedProducts, result)
    }

}
