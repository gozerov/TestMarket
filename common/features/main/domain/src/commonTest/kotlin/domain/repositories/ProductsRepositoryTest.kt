package domain.repositories

import domain.models.ProductDTO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class ProductsRepositoryTest {

    private val fakeProductsRepository = FakeProductsRepository()

    @Test
    fun `getProducts should return all products`() = runBlocking {
        val expectedProducts = listOf(
            ProductDTO(1, "Product 1", "Description 1", null, 10.0, false, false),
            ProductDTO(2, "Product 2", "Description 2", null, 20.0, false, false)
        )
        fakeProductsRepository.addProducts(expectedProducts)

        val result = fakeProductsRepository.getProducts().first()

        assertEquals(expectedProducts, result)
    }

    @Test
    fun `updateCart should update isInCart status of product`() = runBlocking {
        val product = ProductDTO(1, "Product 1", "Description 1", null, 10.0, false, false)
        fakeProductsRepository.addProducts(listOf(product))

        fakeProductsRepository.updateCart(1, true)

        val updatedProduct = fakeProductsRepository.getProducts().first().first { it.id == 1 }
        assertEquals(true, updatedProduct.isInCart)
    }

    @Test
    fun `updateShoppingList should update isInFavorites status of product`() = runBlocking {
        val product = ProductDTO(1, "Product 1", "Description 1", null, 10.0, false, false)
        fakeProductsRepository.addProducts(listOf(product))

        fakeProductsRepository.updateShoppingList(1, true)

        val updatedProduct = fakeProductsRepository.getProducts().first().first { it.id == 1 }
        assertEquals(true, updatedProduct.isInFavorites)
    }
}