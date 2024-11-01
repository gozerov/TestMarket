package presentation.screens.list.models

sealed interface ShoppingListViewAction {

    class ShowError(val message: String) : ShoppingListViewAction

}