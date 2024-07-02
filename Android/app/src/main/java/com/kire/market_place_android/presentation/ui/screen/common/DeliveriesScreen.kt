package com.kire.market_place_android.presentation.ui.screen.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kire.market_place_android.presentation.constant.Strings

import com.kire.market_place_android.presentation.navigation.transition.common.DeliveriesScreenTransitions
import com.kire.market_place_android.presentation.screen.deliveries_screen_ui.DeliveryCard
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.RequestResultMessage
import com.kire.market_place_android.presentation.ui.screen.destinations.DeliveriesScreenDestination
import com.kire.market_place_android.presentation.viewmodel.OrderViewModel

import com.kire.test.R

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Экран с доставками пользователя
 *
 * @param orderViewModel viewModel для работы с заказами
 * @param paddingValues отступы от краев экрана
 * @param navigator для навигации между экранами
 *
 * @author Michael Gontarev (KiREHwYE)
 * @author Aleksey Timko (de4ltt)*/
@Destination(style = DeliveriesScreenTransitions::class)
@Composable
fun DeliveriesScreen(
    orderViewModel: OrderViewModel,
    paddingValues: PaddingValues = PaddingValues(28.dp),
    navigator: DestinationsNavigator
) {

    BackHandler {
        navigator.popBackStack(DeliveriesScreenDestination, inclusive = true)
        return@BackHandler
    }

    val orders by orderViewModel.orders.collectAsStateWithLifecycle()

    RequestResultMessage(
        requestResultStateFlow = orderViewModel.requestResult,
        makeRequestResultIdle = orderViewModel::makeRequestResultIdle
    )

    Column(
        modifier = Modifier
            .background(Color.White)
    ) {

        Row(
            modifier = Modifier
                .padding(start = 28.dp, end = 28.dp, bottom = 30.dp)
                .fillMaxWidth()
                .height(120.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "arrow back",
                    modifier = Modifier
                        .size(34.dp)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                navigator.popBackStack(
                                    DeliveriesScreenDestination,
                                    inclusive = true
                                )
                            }
                        }
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = Strings.DELIVERIES,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.BottomEnd
        ) {
            when (orders.size) {
                0 -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 56.dp),
                        contentAlignment = Alignment.Center,
                        content = {
                            Text(
                                text = Strings.NOTHING_WAS_FOUND_FAV,
                                fontSize = 16.sp,
                                color = Color.DarkGray
                            )
                        }
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 28.dp),
                        contentPadding = PaddingValues(bottom = 28.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        items(
                            orders,
                            key = {it.orderId}
                        ) {
                            it.products.forEach {
                                DeliveryCard(delivery = it)
                            }
                        }
                    }
                }
            }
        }
    }
}