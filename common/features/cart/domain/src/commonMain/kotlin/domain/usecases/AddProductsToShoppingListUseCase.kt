package domain.usecases

import domain.models.CheckedProduct
import domain.repositories.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class AddProductsToShoppingListUseCase(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(ids: List<Int>): List<CheckedProduct> =
        withContext(Dispatchers.IO) {
            return@withContext cartRepository.addProductsToShoppingList(ids)
        }

}