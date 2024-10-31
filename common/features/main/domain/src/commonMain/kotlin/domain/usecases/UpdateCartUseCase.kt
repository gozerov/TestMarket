package domain.usecases

import domain.repositories.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class UpdateCartUseCase(
    private val productsRepository: ProductsRepository
) {

    suspend operator fun invoke(productId: Int, isAdding: Boolean): Boolean =
        withContext(Dispatchers.IO) {
            return@withContext productsRepository.updateCart(productId, isAdding)
        }

}