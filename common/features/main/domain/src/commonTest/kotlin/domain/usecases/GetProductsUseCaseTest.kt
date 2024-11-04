package domain.usecases

import domain.models.ProductDTO
import domain.repositories.FakeProductsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class GetProductsUseCaseTest {

    private val fakeProductsRepository = FakeProductsRepository()
    private val getProductsUseCase = GetProductsUseCase(fakeProductsRepository)

    @Test
    fun `invoke should return list of products`() = runBlocking {
        val expectedProducts = listOf(
            ProductDTO(1, "Product 1", "Description 1", null, 10.0, false, false),
            ProductDTO(2, "Product 2", "Description 2", null, 20.0, false, false)
        )
        fakeProductsRepository.addProducts(expectedProducts)

        val result = getProductsUseCase.invoke().first()

        assertEquals(expectedProducts, result)
    }

}
