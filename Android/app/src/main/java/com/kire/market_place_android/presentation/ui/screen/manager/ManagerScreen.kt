package com.kire.market_place_android.presentation.ui.screen.manager

import androidx.activity.compose.BackHandler

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kire.market_place_android.presentation.constant.Strings

import com.kire.market_place_android.presentation.navigation.transition.manager.ManagerScreenTransitions
import com.kire.market_place_android.presentation.navigation.util.AppDestinations
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.RequestResultMessage
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.TopBar
import com.kire.market_place_android.presentation.ui.details.manager.manager_screen_ui.OrderReleasingBar
import com.kire.market_place_android.presentation.ui.details.manager.manager_screen_ui.PickUpPointIncomeBar
import com.kire.market_place_android.presentation.ui.details.manager.manager_screen_ui.PickUpPointInfoBar
import com.kire.market_place_android.presentation.ui.screen.destinations.OrderScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.ShoppingScreenDestination
import com.kire.market_place_android.presentation.viewmodel.ManagerViewModel

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Экран менеджера
 *
 * @param managerViewModel ViewModel менеджера
 * @param navigator для навигации между экранами
 * @param paddingValues отступы от краев экрана
 *
 * @author Michael Gontarev (KiREHwYE)*/
@Destination(style = ManagerScreenTransitions::class)
@Composable
fun ManagerScreen(
    managerViewModel: ManagerViewModel,
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(start = 28.dp, end = 28.dp, bottom = 66.dp)
){

    BackHandler {
        navigator.popBackStack(ShoppingScreenDestination, inclusive = false)
        return@BackHandler
    }

    RequestResultMessage(
        requestResultStateFlow = managerViewModel.requestResult,
        makeRequestResultIdle = managerViewModel::makeRequestResultIdle
    )

    val pickUpPoint by managerViewModel.pickUpPoint.collectAsStateWithLifecycle()

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

        if (pickUpPoint.address.isNotEmpty()){
            OrderReleasingBar(
                onClick = { orderCode ->
                    managerViewModel.getOrderedProductsByOrderId(orderCode)
                    navigator.navigate(OrderScreenDestination)
                }
            )

            PickUpPointInfoBar(
                pickUpPointAddress = pickUpPoint.address,
                pickUpPointCode = pickUpPoint.id.toString(),
                pickUpPointManager = pickUpPoint.managerName
            )

            PickUpPointIncomeBar(
                income = pickUpPoint.income
            )
        } else {

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
                content = {
                    Text(
                        text = Strings.NO_PICK_UP_POINT,
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                }
            )
        }
    }
}