package domain.usecases

import domain.repositories.ShoppingListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class RemoveFromShoppingListUseCase(
    private val shoppingListRepository: ShoppingListRepository
) {

    suspend operator fun invoke(productId: Int) = withContext(Dispatchers.IO) {
        return@withContext shoppingListRepository.removeFromShoppingList(productId)
    }

}