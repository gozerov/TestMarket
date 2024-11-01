package domain.usecases

import domain.models.ProductWithAmount
import domain.repositories.ShoppingListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class GetProductsWithAmountUseCase(
    private val shoppingListRepository: ShoppingListRepository
) {

    suspend operator fun invoke(): List<ProductWithAmount> = withContext(Dispatchers.IO) {
        return@withContext shoppingListRepository.getProducts()
    }

}