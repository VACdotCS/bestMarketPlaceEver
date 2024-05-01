package com.kire.market_place_android.presentation.ui.screen

import androidx.activity.compose.BackHandler

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kire.market_place_android.presentation.destinations.ManagerScreenDestination
import com.kire.market_place_android.presentation.destinations.OrderScreenDestination

import com.kire.market_place_android.presentation.navigation.Transition.ManagerScreenTransitions
import com.kire.market_place_android.presentation.navigation.util.AppDestinations
import com.kire.market_place_android.presentation.ui.cross_screen_ui.TopBar
import com.kire.market_place_android.presentation.ui.manager_screen_ui.OrderReleasingBar
import com.kire.market_place_android.presentation.ui.manager_screen_ui.PickUpPointIncomeBar
import com.kire.market_place_android.presentation.ui.manager_screen_ui.PickUpPointInfoBar

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = ManagerScreenTransitions::class)
@Composable
fun ManagerScreen(
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(start = 28.dp, end = 28.dp, bottom = 66.dp)
){

    BackHandler {
        navigator.popBackStack(ManagerScreenDestination, inclusive = true)
        return@BackHandler
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopBar(
            destination = AppDestinations.BottomBarDestinations.MANAGER,
            navigator = navigator
        )

        Spacer(modifier = Modifier.height(28.dp))

        OrderReleasingBar(
            onClick = {
                navigator.navigate(OrderScreenDestination)
            }
        )

        PickUpPointInfoBar(
            pickUpPointAddress = "г. Краснодар, ул. Ставропольская, 149",
            pickUpPointCode = "КРД-0001",
            pickUpPointManager = "Бобр"
        )

        PickUpPointIncomeBar(
            income = 47250.00
        )
    }
}