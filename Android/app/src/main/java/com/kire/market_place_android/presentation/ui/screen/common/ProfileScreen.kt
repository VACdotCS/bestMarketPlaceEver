package com.kire.market_place_android.presentation.ui.screen.common

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.compose.BackHandler

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kire.market_place_android.presentation.constant.BottomBarHeight
import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.model.order.Order

import com.kire.market_place_android.presentation.navigation.transition.common.ProfileScreenTransitions
import com.kire.market_place_android.presentation.navigation.util.AppDestinations
import com.kire.market_place_android.presentation.screen.profile_screen_ui.ChangePasswordBottomBar
import com.kire.market_place_android.presentation.screen.profile_screen_ui.ProfileDataBottomBar
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.ListWithTopAndFab
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.RequestResultMessage
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.TopBar
import com.kire.market_place_android.presentation.ui.details.common.profile_screen_ui.ChangePasswordBar
import com.kire.market_place_android.presentation.ui.details.common.profile_screen_ui.PaymentMethod
import com.kire.market_place_android.presentation.ui.details.common.profile_screen_ui.PurchaseRelatedInfoBar
import com.kire.market_place_android.presentation.ui.details.common.profile_screen_ui.UserProfileInfo
import com.kire.market_place_android.presentation.ui.screen.destinations.DeliveriesScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.ShoppingScreenDestination
import com.kire.market_place_android.presentation.viewmodel.UserViewModel

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.Year

/**
 * Профиль пользователя
 *
 * @param userViewModel ViewModel для работы с пользователем
 * @param navigator для навигации между экранами
 * @param paddingValues отступы
 *
 * @author Michael Gontarev (KiREHwYE)*/
@OptIn(ExperimentalMaterial3Api::class)
@Destination(style = ProfileScreenTransitions::class)
@Composable
fun ProfileScreen(
    userViewModel: UserViewModel,
    orders: StateFlow<List<Order>>,
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(start = 28.dp, end = 28.dp, bottom = BottomBarHeight.BOTTOM_BAR_HEIGHT)
) {

    BackHandler {
        navigator.popBackStack(ShoppingScreenDestination, inclusive = false)
        return@BackHandler
    }

    val profileState = userViewModel.profileState

    RequestResultMessage(
        requestResultStateFlow = userViewModel.requestResult,
        makeRequestResultIdle = userViewModel::makeRequestResultIdle
    )

    val orders by orders.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val closestDelivery = remember {
        try {
            orders.minOf { order ->
                order.products.minOf { orderedProduct ->
                    orderedProduct.completionDate ?: LocalDate.MAX
                }
            }
        } catch (e: Exception) {
            Toast.makeText(
                context,
                Strings.SOME_ERROR,
                LENGTH_SHORT
            ).show()

            LocalDate.MAX
        }
    }

    val sheetState = rememberModalBottomSheetState()

    var showChangePasswordBottomBar by remember {
        mutableStateOf(false)
    }
    var showProfileDataBottomBar by remember {
        mutableStateOf(false)
    }

    ListWithTopAndFab(
        listSize = 1,
        topBar = {
            TopBar(
                logOut = userViewModel::logOut,
                destination = AppDestinations.BottomBarDestinations.PROFILE,
                navigator = navigator
            )
        }
    ) {

        profileState.apply {

            Column(
                modifier = it,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                UserProfileInfo(
                    name = username,
                    phone = phone,
                    email = email,
                    showBottomSheet = { show ->
                        showProfileDataBottomBar = show
                    }
                )

                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    columns = GridCells.Fixed(count = 2),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    item {
                        PurchaseRelatedInfoBar(
                            title = Strings.TOTAL_PURCHASES_TITLE,
                            sign = Strings.RUB,
                            info = amountSpent?.toString() ?: 0.toString()
                        )
                    }

                    item {
                        PurchaseRelatedInfoBar(
                            title = Strings.TOTAL_PURCHASES_PERCENT_TITLE,
                            sign = Strings.PERCENT,
                            info =
                            if (redemptionPercent?.isNaN() == true)
                                0.0.toString()
                            else redemptionPercent?.toString() ?: 0.0.toString()
                        )
                    }

                    item {
                        PurchaseRelatedInfoBar(
                            title = Strings.DISCOUNT_TITLE,
                            sign = Strings.PERCENT,
                            info = userDiscount?.toString() ?: 0.0.toString()
                        )
                    }

                    item {
                        PurchaseRelatedInfoBar(
                            title = Strings.DELIVERIES_TITLE,
                            sign =
                                if (closestDelivery.year < Year.MAX_VALUE)
                                    closestDelivery.dayOfWeek.value.toString()
                                else "",
                            info =
                                if (closestDelivery.year < Year.MAX_VALUE)
                                    closestDelivery.month.toString()
                                else Strings.NO_DELIVERIES_INFO,
                            onClick = {
                                navigator.navigate(DeliveriesScreenDestination)
                            }
                        )
                    }
                }

                PaymentMethod(
                    profileState = profileState,
                    onEvent = userViewModel::onEvent
                )

                ChangePasswordBar(
                    onClick = { show ->
                        showChangePasswordBottomBar = show
                    }
                )
            }
        }
    }

    if (showChangePasswordBottomBar)
        ChangePasswordBottomBar(
            sheetState = sheetState,
            profileState = profileState,
            onEvent = userViewModel::onEvent,
            showBottomSheet = { show ->
                showChangePasswordBottomBar = show
            },
        )

    if (showProfileDataBottomBar)
        ProfileDataBottomBar(
            profileState = profileState,
            onEvent = userViewModel::onEvent,
            sheetState = sheetState,
            showBottomSheet = { show ->
                showProfileDataBottomBar = show
            }
        )
}