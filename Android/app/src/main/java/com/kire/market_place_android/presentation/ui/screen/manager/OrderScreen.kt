package com.kire.market_place_android.presentation.ui.screen.manager

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.detectVerticalDragGestures

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kire.market_place_android.presentation.model.manager.ManagerOrderUiEvent

import com.kire.market_place_android.presentation.navigation.transition.manager.OrderScreenTransitions
import com.kire.market_place_android.presentation.navigation.util.AppDestinations
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.ListWithTopAndFab
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.RequestResultMessage
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.TopBar
import com.kire.market_place_android.presentation.ui.details.manager.order_screen_ui.OrderFloatingButton
import com.kire.market_place_android.presentation.ui.details.manager.order_screen_ui.OrderItem
import com.kire.market_place_android.presentation.viewmodel.ManagerViewModel

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Экран обработки заказа менеджером
 *
 * @param navigator для навигации между экранами
 * @param orderedProductsToHandle список продуктов, которые необходимо обработать
 * @param paddingValues отступы от краев экрана
 *
 * @author Michael Gontarev (KiREHwYE)*/
@Destination(style = OrderScreenTransitions::class)
@Composable
fun OrderScreen(
    navigator: DestinationsNavigator,
    managerViewModel: ManagerViewModel,
    paddingValues: PaddingValues = PaddingValues(start = 28.dp, end = 28.dp)
) {

    BackHandler {
        navigator.popBackStack()
        return@BackHandler
    }

    RequestResultMessage(
        requestResultStateFlow = managerViewModel.requestResult,
        makeRequestResultIdle = managerViewModel::makeRequestResultIdle
    )

    val managerOrderState by managerViewModel.managerOrderState.collectAsStateWithLifecycle()

    val order by managerViewModel.order.collectAsStateWithLifecycle()

    ListWithTopAndFab(
        listSize = order.products.size,
        topBar = {
            TopBar(
                destination = AppDestinations.ManagerDestinations.ORDER,
                navigator = navigator
            )
        },
        floatingButton = { shiftBarDown ->
            OrderFloatingButton(
                amountToGive = managerOrderState.received.size,
                amountToReturn = managerOrderState.returned.size,
                totalSum =
                    order.products.sumOf { orderedProduct ->
                        if (managerOrderState.received.contains(orderedProduct.product.id))
                            (orderedProduct.price * orderedProduct.quantity.toBigDecimal()).toDouble()
                        else 0.0
                    },
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectVerticalDragGestures { _, dragAmount ->
                            if (dragAmount > 0) {
                                shiftBarDown()
                            }
                        }
                    },
                onClick = {
                    managerViewModel.onEvent(event = ManagerOrderUiEvent.confirmOrder)
                }
            )
        }
    ) {
        LazyColumn(
            modifier = it,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 28.dp)
        ) {

            items(
                order.products,
                key = null
            ) { orderedProduct ->

                OrderItem(
                    managerOrderState = managerOrderState,
                    orderedProduct = orderedProduct,
                    onEvent = managerViewModel::onEvent
                )
            }
        }
    }
}