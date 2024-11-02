package presentation.screens.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import domain.models.ProductWithAmount
import kotlinx.coroutines.launch
import presentation.screens.list.models.ShoppingListViewAction
import presentation.screens.list.models.ShoppingListViewEvent
import presentation.screens.list.views.EmptyShoppingListView
import presentation.screens.list.views.FilledShoppingListView
import presentation.screens.list.views.RemoveFromListSheetContent
import theme.TestMarketTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    viewModel: ShoppingListViewModel = viewModel { ShoppingListViewModel() },
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState()
    val currentProduct = remember { mutableStateOf<ProductWithAmount?>(null) }
    val showBottomSheet = remember { mutableStateOf(false) }

    val viewState = viewModel.viewStates().collectAsState().value
    val viewAction = viewModel.viewActions().collectAsState(null).value

    LaunchedEffect(null) {
        viewModel.obtainEvent(ShoppingListViewEvent.GetShoppingList)
    }

    when (viewAction) {
        is ShoppingListViewAction.ShowError -> {
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
        if (showBottomSheet.value && currentProduct.value != null) {
            ModalBottomSheet(
                containerColor = TestMarketTheme.colors.primaryBackground,
                onDismissRequest = {
                    currentProduct.value = null
                    showBottomSheet.value = false
                },
                sheetState = sheetState
            ) {
                currentProduct.value?.let { product ->
                    RemoveFromListSheetContent(
                        product = product,
                        onRemoveClicked = {
                            viewModel.obtainEvent(
                                ShoppingListViewEvent.RemoveProductFromList(product.id)
                            )
                            currentProduct.value = null
                            showBottomSheet.value = false
                        }
                    )
                }
            }
        }
        if (viewState.products.isNotEmpty()) {
            FilledShoppingListView(
                products = viewState.products,
                onCartClicked = { product, isAddedToCart: Boolean ->
                    viewModel.obtainEvent(
                        ShoppingListViewEvent.AddProductToCart(product.id, isAddedToCart)
                    )
                },
                onMenuClicked = { product ->
                    currentProduct.value = product
                    showBottomSheet.value = true
                },
                onChangeAmountClicked = { }
            )
        } else {
            EmptyShoppingListView()
        }
    }
}