package com.kire.market_place_android.presentation.ui.screen

import android.net.Uri
import androidx.activity.compose.BackHandler

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.destinations.ShoppingCartScreenDestination

import com.kire.market_place_android.presentation.model.ProductItem
import com.kire.market_place_android.presentation.navigation.Transition.ShoppingCartScreenTransitions
import com.kire.market_place_android.presentation.navigation.util.AppDestinations
import com.kire.market_place_android.presentation.ui.cross_screen_ui.TopBar
import com.kire.market_place_android.presentation.ui.shopping_cart_screen_ui.PurchaseFloatingActionButton
import com.kire.market_place_android.presentation.ui.shopping_cart_screen_ui.ShoppingCartItem

import com.kire.test.R

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = ShoppingCartScreenTransitions::class)
@Composable
fun ShoppingCartScreen(
    navigator: DestinationsNavigator,
    shoppingCartItems: List<ProductItem> = listOf(
        ProductItem("Помидоры", "250.00", "кг","250.00", Uri.EMPTY, false, ""),
        ProductItem("Груши", "300.00","кг","250.00", Uri.EMPTY, true, ""),
        ProductItem("Помидоры", "250.00","кг","300.00", Uri.EMPTY, true, ""),
        ProductItem("Груши", "300.00","кг","250.00", Uri.EMPTY, false, ""),
        ProductItem("Помидоры", "250.00","кг","250.00", Uri.EMPTY, false, ""),
        ProductItem("Груши", "300.00","кг","250.00", Uri.EMPTY, true, "")
    ),
    paddingValues: PaddingValues = PaddingValues(start = 28.dp, end = 28.dp, bottom = 66.dp)
) {

    BackHandler {
        navigator.popBackStack(ShoppingCartScreenDestination, inclusive = true)
        return@BackHandler
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopBar(destination = AppDestinations.BottomBarDestinations.SHOPPING_CART)

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f, fill = false),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            when(shoppingCartItems.size) {
                0 -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                        content = {
                            Text(
                                text = stringResource(R.string.nothing_was_found),
                                fontSize = 16.sp,
                                color = Color.DarkGray
                            )
                        }
                    )
                }
                else -> {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        itemsIndexed(
                            shoppingCartItems,
                            key = null
                        ) { index, item ->

                            ShoppingCartItem(
                                name = item.itemName,
                                price = item.itemPrice,
                                amount = 3.0
                            )
                        }
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 86.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        PurchaseFloatingActionButton(
            onClick = {
                /*TODO*/
            }
        )
    }
}