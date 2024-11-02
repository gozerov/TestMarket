package domain.usecases

import domain.repositories.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class UpdateProductStatusUseCase(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(productId: Int, isChecked: Boolean) = withContext(Dispatchers.IO) {
        return@withContext cartRepository.updateProductStatus(productId, isChecked)
    }

}