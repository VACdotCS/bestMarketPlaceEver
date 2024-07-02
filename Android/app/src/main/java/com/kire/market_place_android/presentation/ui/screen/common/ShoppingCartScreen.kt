package com.kire.market_place_android.presentation.ui.screen.common

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import androidx.navigation.NavController
import com.kire.market_place_android.presentation.constant.BottomBarHeight
import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.model.pick_up_point.PickUpPoint

import com.kire.market_place_android.presentation.navigation.transition.common.ShoppingCartScreenTransitions
import com.kire.market_place_android.presentation.navigation.util.AppDestinations
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.ListWithTopAndFab
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.RequestResultMessage
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.TopBar
import com.kire.market_place_android.presentation.ui.details.common.shopping_cart_screen_ui.OrderingBottomBar
import com.kire.market_place_android.presentation.ui.details.common.shopping_cart_screen_ui.PurchaseFloatingActionButton
import com.kire.market_place_android.presentation.ui.details.common.shopping_cart_screen_ui.ShoppingCartItem
import com.kire.market_place_android.presentation.ui.screen.destinations.ItemAddToCartMenuDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.ShoppingScreenDestination
import com.kire.market_place_android.presentation.viewmodel.OrderViewModel
import com.kire.market_place_android.presentation.viewmodel.ProductViewModel

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.popBackStack
import com.ramcosta.composedestinations.utils.isRouteOnBackStack
import kotlinx.coroutines.flow.StateFlow

/**
 * Корзина покупок
 *
 * @param userViewModel ViewModel для работы с пользователем
 * @param navController NavController для навигации между экранами
 * @param shoppingCartItems список товаров в корзине
 * @param paddingValues отступы от краев экрана
 *
 * @author Michael Gontarev (KiREHwYE)*/
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Destination(style = ShoppingCartScreenTransitions::class)
@Composable
fun ShoppingCartScreen(
    productViewModel: ProductViewModel,
    orderViewModel: OrderViewModel,
    chosenPickUpPoint: StateFlow<PickUpPoint>,
    navController: NavController,
    paddingValues: PaddingValues = PaddingValues(start = 28.dp, end = 28.dp, bottom = 66.dp)
) {

    BackHandler {
        if (navController.isRouteOnBackStack(ItemAddToCartMenuDestination))
            navController.popBackStack(ItemAddToCartMenuDestination, inclusive = false)
        else
            navController.popBackStack(ShoppingScreenDestination, inclusive = false)
        return@BackHandler
    }

    val chosenPickUpPoint by chosenPickUpPoint.collectAsStateWithLifecycle()

    val sheetState = rememberModalBottomSheetState()
    val isBottomSheetShown = remember {
        mutableStateOf(false)
    }

    val cartState by productViewModel.cartState.collectAsStateWithLifecycle()

    RequestResultMessage(
        requestResultStateFlow = orderViewModel.requestResult,
        makeRequestResultIdle = orderViewModel::makeRequestResultIdle
    )
    RequestResultMessage(
        requestResultStateFlow = productViewModel.requestResult,
        makeRequestResultIdle = productViewModel::makeRequestResultIdle
    )

    ListWithTopAndFab(
        listSize = cartState.allProductsInCart.size,
        paddingValues = paddingValues,
        topBar = {
            TopBar(destination = AppDestinations.BottomBarDestinations.SHOPPING_CART)
        },
        floatingButton = { shiftBarDown ->
            if (cartState.allProductsInCart.isNotEmpty())
                PurchaseFloatingActionButton(
                    amount = cartState.toBuy.sumOf { it.chosenQuantity },
                    totalSum = cartState.toBuy.sumOf { it.price * it.chosenQuantity.toBigDecimal() }.toDouble(),
                    onClick = {
                        isBottomSheetShown.value = true
                    },
                    modifier = Modifier
                        .pointerInput(Unit) {
                            detectVerticalDragGestures { _, dragAmount ->
                                if (dragAmount > 0) {
                                    shiftBarDown()
                                }
                            }
                        }
                )
        }
    ) {
        LazyColumn(
            modifier = it.padding(bottom = BottomBarHeight.BOTTOM_BAR_HEIGHT),
            contentPadding = PaddingValues(bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                cartState.allProductsInCart,
                key = {it.id}
            ) { product ->
                ShoppingCartItem(
                    product = product,
                    cartState = cartState,
                    onEvent = productViewModel::onEvent,
                    modifier = Modifier.animateItemPlacement(
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = LinearOutSlowInEasing
                        )
                    )
                )
            }
        }
    }

    if (isBottomSheetShown.value)
        OrderingBottomBar(
            pickUpPointAddress = chosenPickUpPoint.address.ifEmpty { Strings.NO_CHOSEN_PICK_UP_POINT },
            deliveryClosestDate = cartState.toBuy.minOf { it.deliveryDays }.toString(),
            totalSum = cartState.toBuy.sumOf { it.price * it.chosenQuantity.toBigDecimal() }.toDouble(),
            showBottomSheet = {
                isBottomSheetShown.value = false
            },
            sheetState = sheetState,
            createOrder = {
                orderViewModel.createOrder(
                    chosenPickUpPoint.id,
                    cartState.toBuy,
                    cartState.toBuy.sumOf { it.price * it.chosenQuantity.toBigDecimal() }
                )
            }
        )
}