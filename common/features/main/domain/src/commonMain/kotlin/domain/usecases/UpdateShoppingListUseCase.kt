package domain.usecases

import domain.repositories.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class UpdateShoppingListUseCase(
    private val productsRepository: ProductsRepository
) {

    suspend operator fun invoke(productId: Int, isAdding: Boolean) =
        withContext(Dispatchers.IO) {
            return@withContext productsRepository.updateShoppingList(productId, isAdding)
        }

}