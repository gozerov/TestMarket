package presentation.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import domain.models.ProductWithAmount
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import presentation.screens.list.models.ShoppingListViewAction
import presentation.screens.list.models.ShoppingListViewEvent
import presentation.screens.list.views.RemoveFromListSheet
import presentation.screens.list.views.WishlistCard
import ru.gozerov.test_market.common.features.`shopping-list`.presentation.resources.Res
import ru.gozerov.test_market.common.features.`shopping-list`.presentation.resources.shopping_list
import theme.TestMarketTheme
import views.DefaultDivider

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
                    RemoveFromListSheet(
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
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                text = stringResource(Res.string.shopping_list),
                color = TestMarketTheme.colors.text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            DefaultDivider(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(viewState.products.size) { ind ->

                    val product = viewState.products[ind]
                    val isAddedToCart = remember { mutableStateOf(product.isInCart) }
                    WishlistCard(
                        product = product,
                        onCartClicked = {
                            isAddedToCart.value = !isAddedToCart.value
                            viewModel.obtainEvent(
                                ShoppingListViewEvent.AddProductToCart(
                                    product.id,
                                    isAddedToCart.value
                                )
                            )
                        },
                        onChangeAmountClicked = {},
                        onMenuClicked = {
                            currentProduct.value = product
                            showBottomSheet.value = true
                        },
                        isAddedToCart = isAddedToCart,
                        isLastItem = ind == viewState.products.size - 1
                    )

                }

                item {
                    if (viewState.products.isNotEmpty()) {
                        DefaultDivider()
                    }
                }
            }
        }
    }
}