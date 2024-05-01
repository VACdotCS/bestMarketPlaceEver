package com.kire.market_place_android.presentation.screen.cross_screen_ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kire.market_place_android.presentation.navigation.util.AppBarsDestination
import com.kire.market_place_android.presentation.screen.NavGraphs
import com.kire.market_place_android.presentation.screen.appCurrentDestinationAsState
import com.kire.market_place_android.presentation.screen.destinations.AdminPanelItemsScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.AdminPanelPickUpScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.AdminPanelUsersScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.Destination
import com.kire.market_place_android.presentation.screen.destinations.FavouritesScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.ManagerScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.ProfileScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.ShoppingCartScreenDestination
import com.kire.market_place_android.presentation.screen.startAppDestination
import com.kire.market_place_android.presentation.theme.ExtendedTheme

/*
App's Top Bar
 */
@Composable
fun TopBar(
    navHostController: NavHostController,
    paddingValuesTopDp: Dp = 84.dp,
    paddingValuesStartEndBottomDp: Dp = 28.dp
) {
    val currentDestination: Destination = navHostController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    val allowedList = listOf(
        FavouritesScreenDestination,
        AdminPanelItemsScreenDestination,
        ManagerScreenDestination,
        ShoppingCartScreenDestination,
        ProfileScreenDestination,
        AdminPanelPickUpScreenDestination,
        AdminPanelUsersScreenDestination
    )

    if (allowedList.contains(currentDestination)) {

        var appBarDestination: AppBarsDestination = AppBarsDestination.SHOPPING

        AppBarsDestination.entries.forEach { destination ->
            if (destination.direction.route == currentDestination.route)
                appBarDestination = destination
        }


        Crossfade(
            targetState = appBarDestination,
            animationSpec = tween(durationMillis = 0, delayMillis = 450),
            label = "TopBarCrossfade"
        ) { destination ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White)
                    .padding(
                        top = paddingValuesTopDp,
                        start = paddingValuesStartEndBottomDp,
                        end = paddingValuesStartEndBottomDp,
                        bottom = paddingValuesStartEndBottomDp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(id = destination.iconTop!!),
                        contentDescription = null,
                        tint = ExtendedTheme.colors.redAccent,
                        modifier = Modifier
                            .size(34.dp)
                    )

                    Text(
                        text = stringResource(id = destination.label),
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                }

                destination.plusButton?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = "plus_bold"
                    )
                }
            }
        }

    }
}