package presentation.screens.cart

import androidx.lifecycle.viewModelScope
import di.Injector
import domain.usecases.AddProductsToShoppingListUseCase
import domain.usecases.GetCartUseCase
import domain.usecases.RemoveProductsFromCartUseCase
import domain.usecases.UpdateProductStatusUseCase
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import presentation.screens.cart.models.CartViewAction
import presentation.screens.cart.models.CartViewEvent
import presentation.screens.cart.models.CartViewState
import utils.runCatchingNonCancellation
import viewmodel.BaseViewModel

class CartViewModel : BaseViewModel<CartViewState, CartViewAction, CartViewEvent>(CartViewState()) {

    private val getCartUseCase: GetCartUseCase = Injector.instance()
    private val removeProductsFromCartUseCase: RemoveProductsFromCartUseCase = Injector.instance()
    private val updateProductStatusUseCase: UpdateProductStatusUseCase = Injector.instance()
    private val addProductsToShoppingListUseCase: AddProductsToShoppingListUseCase =
        Injector.instance()

    override fun obtainEvent(viewEvent: CartViewEvent) {
        viewModelScope.launch {
            when (viewEvent) {
                is CartViewEvent.GetCart -> {
                    runCatchingNonCancellation {
                        getCartUseCase.invoke()
                    }
                        .onSuccess { products ->
                            viewState = viewState.copy(products = products.toImmutableList())
                        }
                        .onFailure { e ->
                            viewAction = CartViewAction.ShowError(e.message.toString())
                        }
                }

                is CartViewEvent.UpdateProductStatus -> {
                    runCatchingNonCancellation {
                        updateProductStatusUseCase.invoke(viewEvent.productId, viewEvent.isChecked)
                    }
                        .onSuccess {
                            val newProducts = viewState.products.map { product ->
                                if (product.id == viewEvent.productId)
                                    product.copy(isChecked = viewEvent.isChecked)
                                else product
                            }
                            viewState = viewState.copy(products = newProducts.toImmutableList())
                        }
                        .onFailure { e ->
                            viewAction = CartViewAction.ShowError(e.message.toString())
                        }
                }

                is CartViewEvent.RemoveFromCart -> {
                    runCatchingNonCancellation {
                        removeProductsFromCartUseCase.invoke(viewEvent.productIds)
                    }
                        .onSuccess { products ->
                            viewState = viewState.copy(products = products.toImmutableList())
                        }
                        .onFailure { e ->
                            viewAction = CartViewAction.ShowError(e.message.toString())
                        }
                }

                is CartViewEvent.AddToShoppingList -> {
                    runCatchingNonCancellation {
                        addProductsToShoppingListUseCase.invoke(viewEvent.productIds)
                    }
                        .onSuccess { products ->
                            viewState = viewState.copy(products = products.toImmutableList())
                        }
                        .onFailure { e ->
                            viewAction = CartViewAction.ShowError(e.message.toString())
                        }
                }
            }
        }
    }

}