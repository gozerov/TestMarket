package presentation.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
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
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import presentation.screens.main.models.MainViewAction
import presentation.screens.main.models.MainViewEvent
import presentation.screens.main.views.ProductCard
import ru.gozerov.test_market.common.features.main.presentation.resources.Res
import ru.gozerov.test_market.common.features.main.presentation.resources.recommend
import theme.TestMarketTheme

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel { MainViewModel() },
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val viewState = viewModel.viewStates().collectAsState().value
    val viewAction = viewModel.viewActions().collectAsState(null).value

    LaunchedEffect(null) {
        viewModel.obtainEvent(MainViewEvent.GetProducts)
    }

    when (viewAction) {
        is MainViewAction.ShowError -> {
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
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 12.dp),
                        text = stringResource(Res.string.recommend),
                        color = TestMarketTheme.colors.text,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                items(viewState.products.size) { ind ->

                    val product = viewState.products[ind]
                    val isAddedToCart = remember { mutableStateOf(product.isInCart) }
                    val isAddedToShoppingList = remember { mutableStateOf(product.isInFavorites) }
                    ProductCard(
                        product = product,
                        onCartClicked = {
                            isAddedToCart.value = !isAddedToCart.value
                            viewModel.obtainEvent(
                                MainViewEvent.UpdateProductCart(
                                    product.id,
                                    isAddedToCart.value
                                )
                            )
                        },
                        onShoppingListClicked = {
                            isAddedToShoppingList.value = !isAddedToShoppingList.value
                            viewModel.obtainEvent(
                                MainViewEvent.UpdateShoppingList(
                                    product.id,
                                    isAddedToShoppingList.value
                                )
                            )
                        },
                        isAddedToCart,
                        isAddedToShoppingList,
                        isLastItem = ind == viewState.products.size - 1
                    )

                }

                item {
                    if (viewState.products.isNotEmpty()) {
                        Divider(
                            modifier = Modifier.padding(start = 16.dp).fillMaxWidth(),
                            color = TestMarketTheme.colors.secondary
                        )
                    }
                }
            }

        }
    }
}