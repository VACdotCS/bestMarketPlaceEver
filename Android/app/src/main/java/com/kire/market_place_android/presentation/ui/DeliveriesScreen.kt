package com.kire.market_place_android.presentation.screen

import android.net.Uri
import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.destinations.DeliveriesScreenDestination
import com.kire.market_place_android.presentation.model.Delivery
import com.kire.market_place_android.presentation.model.DeliveryStatus
import com.kire.market_place_android.presentation.model.ProductItem
import com.kire.market_place_android.presentation.screen.deliveries_screen_ui.DeliveryCard
import com.kire.test.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DeliveriesScreen(
    paddingValues: PaddingValues = PaddingValues(28.dp),
    navigator: DestinationsNavigator
) {

    BackHandler {
        navigator.popBackStack(DeliveriesScreenDestination, inclusive = true)
        return@BackHandler
    }

    val deliveriesList: List<Delivery> = listOf(
        Delivery(
            deliveryId = 1,
            productItem = ProductItem(
                "Помидоры", "250.0", "кг", "250.0", Uri.EMPTY, false, ""
            ),
            2,
            DeliveryStatus.READY,
            123,
            "29.09.2003",
            deliveryEstimate = "29.09.2003",
            deliveryEnd = null
        ),
        Delivery(
            deliveryId = 2,
            productItem = ProductItem(
                "Помидоры", "250.0", "кг", "250.0", Uri.EMPTY, false, ""
            ),
            2,
            DeliveryStatus.DENIED,
            123,
            "29.09.2003",
            deliveryEstimate = "29.09.2003",
            deliveryEnd = "29.09.2003"
        )
    )

    Column {

        Row(
            modifier = Modifier
                .padding(start = 28.dp, end = 28.dp, bottom = 30.dp)
                .fillMaxWidth()
                .height(120.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Box(modifier = Modifier.fillMaxWidth().height(50.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "arrow back",
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(34.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                { navigator.popBackStack() }
                            )
                        }
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.deliveries),
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
            }
        }

        when (deliveriesList.size) {
            0 -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(bottom = 56.dp),
                    contentAlignment = Alignment.Center,
                    content = {
                        Text(
                            text = stringResource(R.string.nothing_was_found_fav),
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
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(deliveriesList) {
                        DeliveryCard(delivery = it)
                    }
                }
            }
        }
    }
}