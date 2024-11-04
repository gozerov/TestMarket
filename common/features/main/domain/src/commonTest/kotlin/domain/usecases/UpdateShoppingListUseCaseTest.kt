package domain.usecases

import domain.models.ProductDTO
import domain.repositories.FakeProductsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class UpdateShoppingListUseCaseTest {

    private val fakeProductsRepository = FakeProductsRepository()
    private val updateShoppingListUseCase = UpdateShoppingListUseCase(fakeProductsRepository)

    @Test
    fun `invoke should update isInFavorites status of product`() = runBlocking {
        val product = ProductDTO(1, "Product 1", "Description 1", null, 10.0, false, false)
        fakeProductsRepository.addProducts(listOf(product))

        updateShoppingListUseCase.invoke(1, true)

        val updatedProduct = fakeProductsRepository.getProducts().first().first { it.id == 1 }
        assertEquals(true, updatedProduct.isInFavorites)
    }

}
