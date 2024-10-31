package presentation.screens.main.models

sealed interface MainViewEvent {

    object GetProducts: MainViewEvent

    class UpdateProductCart(val productId: Int, val isAdding: Boolean) : MainViewEvent

    class UpdateShoppingList(val productId: Int, val isAdding: Boolean) : MainViewEvent

}