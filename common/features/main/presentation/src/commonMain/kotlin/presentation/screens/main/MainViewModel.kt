package presentation.screens.main

import androidx.lifecycle.viewModelScope
import di.Injector
import domain.usecases.GetProductsUseCase
import domain.usecases.UpdateCartUseCase
import domain.usecases.UpdateShoppingListUseCase
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch
import presentation.screens.main.models.MainViewAction
import presentation.screens.main.models.MainViewEvent
import presentation.screens.main.models.MainViewState
import presentation.screens.main.models.toProductList
import utils.runCatchingNonCancellation
import viewmodel.BaseViewModel

class MainViewModel : BaseViewModel<MainViewState, MainViewAction, MainViewEvent>(MainViewState()) {

    private val getProductsUseCase: GetProductsUseCase = Injector.instance()
    private val updateCartUseCase: UpdateCartUseCase = Injector.instance()
    private val updateShoppingListUseCase: UpdateShoppingListUseCase = Injector.instance()

    override fun obtainEvent(viewEvent: MainViewEvent) {
        viewModelScope.launch {
            when (viewEvent) {
                is MainViewEvent.GetProducts -> {
                    runCatchingNonCancellation {
                        getProductsUseCase.invoke()
                    }
                        .onSuccess { productsFlow ->
                            productsFlow.collect { products ->
                                viewState = viewState.copy(products = products.toProductList())
                            }
                        }
                        .onFailure { error ->
                            viewAction = MainViewAction.ShowError(error.message.toString())
                        }
                }

                is MainViewEvent.UpdateProductCart -> {
                    runCatchingNonCancellation {
                        updateCartUseCase.invoke(viewEvent.productId, viewEvent.isAdding)
                    }
                        .onSuccess {
                            val newProducts = viewState.products.map { product ->
                                if (product.id == viewEvent.productId) product.copy(isInCart = viewEvent.isAdding) else product
                            }.toPersistentList()
                            viewState = viewState.copy(products = newProducts)
                        }
                        .onFailure { error ->
                            viewAction = MainViewAction.ShowError(error.message.toString())
                        }
                }

                is MainViewEvent.UpdateShoppingList -> {
                    runCatchingNonCancellation {
                        updateShoppingListUseCase.invoke(viewEvent.productId, viewEvent.isAdding)
                    }
                        .onSuccess {
                            val newProducts = viewState.products.map { product ->
                                if (product.id == viewEvent.productId) product.copy(isInFavorites = viewEvent.isAdding) else product
                            }.toPersistentList()
                            viewState = viewState.copy(products = newProducts)
                        }
                        .onFailure { error ->
                            viewAction = MainViewAction.ShowError(error.message.toString())
                        }
                }
            }
        }
    }

}