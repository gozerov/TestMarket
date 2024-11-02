package domain.usecases

import domain.models.CheckedProduct
import domain.repositories.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class GetCartUseCase(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(): List<CheckedProduct> = withContext(Dispatchers.IO) {
        return@withContext cartRepository.getCart()
    }

}