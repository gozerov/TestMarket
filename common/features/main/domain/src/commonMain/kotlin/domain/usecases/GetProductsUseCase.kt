package domain.usecases

import domain.models.ProductDTO
import domain.repositories.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class GetProductsUseCase(
    private val productsRepository: ProductsRepository
) {

    suspend operator fun invoke(): List<ProductDTO> = withContext(Dispatchers.IO) {
        return@withContext productsRepository.getProducts()
    }

}