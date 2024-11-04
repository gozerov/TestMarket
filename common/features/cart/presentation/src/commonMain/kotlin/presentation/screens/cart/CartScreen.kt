package presentation.screens.cart

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import presentation.screens.cart.models.CartViewAction
import presentation.screens.cart.models.CartViewEvent
import presentation.screens.cart.views.EmptyCartView
import presentation.screens.cart.views.FilledCartView
import theme.TestMarketTheme

@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel { CartViewModel() },
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val viewState = viewModel.viewStates().collectAsState().value
    val viewAction = viewModel.viewActions().collectAsState(null).value

    val areAllChecked = remember { mutableStateOf(false) }

    LaunchedEffect(null) {
        viewModel.obtainEvent(CartViewEvent.GetCart)
    }

    when (viewAction) {
        is CartViewAction.ShowError -> {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(viewAction.message)
            }
        }

        else -> {}

    }

    Scaffold(
        modifier = modifier.then(Modifier.fillMaxSize()),
        backgroundColor = TestMarketTheme.colors.primaryBackground,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        if (viewState.products.isNotEmpty()) {
            FilledCartView(
                products = viewState.products,
                onChecked = { id, isChecked ->
                    println("$id $isChecked")
                    viewModel.obtainEvent(CartViewEvent.UpdateProductStatus(id, isChecked))
                },
                onChangeAmountClicked = { },
                areAllChecked = areAllChecked,
                onAllChecked = { value ->
                    areAllChecked.value = value
                    viewModel.obtainEvent(CartViewEvent.CheckAll(value))
                },
                onShoppingListClicked = {
                    val ids = viewState.products
                        .filter { product -> product.isChecked }
                        .map { product -> product.id }
                    viewModel.obtainEvent(CartViewEvent.AddToShoppingList(ids))
                },
                onClearClicked = {
                    val ids = viewState.products
                        .filter { product -> product.isChecked }
                        .map { product -> product.id }
                    viewModel.obtainEvent(CartViewEvent.RemoveFromCart(ids))
                }
            )
        } else {
            EmptyCartView()
        }
    }


}