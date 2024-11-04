package domain.usecases

import domain.models.ProductWithAmount
import domain.repositories.FakeShoppingListRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class AddToCartUseCaseTest {

    private val fakeShoppingListRepository = FakeShoppingListRepository()
    private val addToCartUseCase = AddToCartUseCase(fakeShoppingListRepository)

    @Test
    fun `invoke should update isInCart status of product`() = runBlocking {
        val product = ProductWithAmount(1, "Product 1", "Description 1", null, 10.0, false, 2)
        fakeShoppingListRepository.addProducts(listOf(product))

        addToCartUseCase.invoke(1, true)

        val updatedProduct = fakeShoppingListRepository.getProducts().first { it.id == 1 }
        assertTrue(updatedProduct.isInCart)
    }

}
