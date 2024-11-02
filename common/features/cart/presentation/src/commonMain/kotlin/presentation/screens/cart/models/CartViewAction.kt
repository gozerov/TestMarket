package presentation.screens.cart.models

sealed interface CartViewAction {

    class ShowError(
        val message: String
    ) : CartViewAction

}