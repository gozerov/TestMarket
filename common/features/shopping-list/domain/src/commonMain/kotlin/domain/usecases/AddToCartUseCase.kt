package domain.usecases

import domain.repositories.ShoppingListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class AddToCartUseCase(
    private val shoppingListRepository: ShoppingListRepository
) {

    suspend operator fun invoke(productId: Int, isAdding: Boolean) = withContext(Dispatchers.IO) {
        return@withContext shoppingListRepository.addToCart(productId, isAdding)
    }

}