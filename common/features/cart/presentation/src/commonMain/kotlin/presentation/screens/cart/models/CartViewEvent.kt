package presentation.screens.cart.models

sealed interface CartViewEvent {

    object GetCart : CartViewEvent

    class UpdateProductStatus(val productId: Int, val isChecked: Boolean) : CartViewEvent

    class RemoveFromCart(val productIds: List<Int>) : CartViewEvent

    class AddToShoppingList(val productIds: List<Int>) : CartViewEvent

}