package presentation.screens.list

import androidx.lifecycle.viewModelScope
import di.Injector
import domain.usecases.AddToCartUseCase
import domain.usecases.GetProductsWithAmountUseCase
import domain.usecases.RemoveFromShoppingListUseCase
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import presentation.screens.list.models.ShoppingListViewAction
import presentation.screens.list.models.ShoppingListViewEvent
import presentation.screens.list.models.ShoppingListViewState
import utils.runCatchingNonCancellation
import viewmodel.BaseViewModel

class ShoppingListViewModel :
    BaseViewModel<ShoppingListViewState, ShoppingListViewAction, ShoppingListViewEvent>(
        ShoppingListViewState()
    ) {

    private val getProductsWithAmountUseCase: GetProductsWithAmountUseCase = Injector.instance()
    private val removeFromShoppingListUseCase: RemoveFromShoppingListUseCase = Injector.instance()
    private val addToCartUseCase: AddToCartUseCase = Injector.instance()

    override fun obtainEvent(viewEvent: ShoppingListViewEvent) {
        viewModelScope.launch {
            when (viewEvent) {
                is ShoppingListViewEvent.GetShoppingList -> {
                    runCatchingNonCancellation {
                        getProductsWithAmountUseCase.invoke()
                    }
                        .onSuccess { products ->
                            viewState = viewState.copy(products = products.toImmutableList())
                        }
                        .onFailure { e ->
                            viewAction = ShoppingListViewAction.ShowError(e.message.toString())
                        }
                }

                is ShoppingListViewEvent.RemoveProductFromList -> {
                    runCatchingNonCancellation {
                        removeFromShoppingListUseCase.invoke(viewEvent.productId)
                    }
                        .onSuccess {
                            val newProducts = viewState.products
                                .filter { product -> product.id != viewEvent.productId }
                                .toImmutableList()

                            viewState = viewState.copy(products = newProducts)
                        }
                        .onFailure { e ->
                            viewAction = ShoppingListViewAction.ShowError(e.message.toString())
                        }
                }

                is ShoppingListViewEvent.AddProductToCart -> {
                    runCatchingNonCancellation {
                        addToCartUseCase.invoke(viewEvent.productId, viewEvent.isAdding)
                    }
                        .onSuccess {
                            val newProducts = viewState.products
                                .map { product ->
                                    if (product.id == viewEvent.productId)
                                        product.copy(isInCart = viewEvent.isAdding)
                                    else product
                                }
                                .toImmutableList()

                            viewState = viewState.copy(products = newProducts)
                        }
                        .onFailure { e ->
                            viewAction = ShoppingListViewAction.ShowError(e.message.toString())
                        }
                }
            }
        }
    }

}