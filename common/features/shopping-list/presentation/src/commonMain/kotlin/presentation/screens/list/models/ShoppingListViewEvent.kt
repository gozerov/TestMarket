package presentation.screens.list.models

sealed interface ShoppingListViewEvent {

    object GetShoppingList : ShoppingListViewEvent

    class RemoveProductFromList(val productId: Int) : ShoppingListViewEvent

    class AddProductToCart(val productId: Int, val isAdding: Boolean) : ShoppingListViewEvent

}