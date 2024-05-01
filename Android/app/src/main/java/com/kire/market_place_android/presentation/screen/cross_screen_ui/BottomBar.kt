package com.kire.market_place_android.presentation.screen.cross_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight

import androidx.compose.material3.Icon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination

import androidx.navigation.NavHostController
import com.kire.market_place_android.presentation.model.UserRole

import com.kire.market_place_android.presentation.navigation.util.AppBarsDestination

import com.kire.market_place_android.presentation.screen.NavGraphs
import com.kire.market_place_android.presentation.screen.appCurrentDestinationAsState
import com.kire.market_place_android.presentation.screen.destinations.Destination
import com.kire.market_place_android.presentation.screen.startAppDestination
import com.kire.market_place_android.presentation.theme.ExtendedTheme

import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec


/*
Implements navigation through screens
 */
@Composable
fun BottomBar(
    userRole: UserRole,
    navHostController: NavHostController,
    paddingStartEndBottom: Dp = 28.dp
) {

    val currentDestination: Destination = navHostController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    val interactionSource = remember { MutableInteractionSource() }

    val appBarsDestinations: List<DirectionDestinationSpec> = AppBarsDestination.entries.map { it.direction }

    var permittedList by remember {
        mutableStateOf(emptyList<AppBarsDestination>())
    }

    LaunchedEffect(key1 = userRole) {
        permittedList = when(userRole) {
            UserRole.CLIENT ->
                listOf(AppBarsDestination.MANAGER, AppBarsDestination.ADMIN_PANEL)
            UserRole.MANAGER ->
                listOf(AppBarsDestination.ADMIN_PANEL)
            UserRole.ADMIN ->
                listOf(AppBarsDestination.MANAGER)
            UserRole.DEVELOPER ->
                emptyList()
        }
    }


    if (appBarsDestinations.contains(currentDestination as DirectionDestinationSpec)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White)
                .padding(
                    start = paddingStartEndBottom,
                    end = paddingStartEndBottom,
                    top = 18.dp,
                    bottom = 18.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AppBarsDestination.entries.forEach { destination ->

                if (!permittedList.contains(destination))
                    destination.iconBottom?.let {
                        Icon(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            tint =
                            if (currentDestination == destination.direction)
                                ExtendedTheme.colors.redAccent
                            else Color.Black,
                            modifier = Modifier
                                .size(30.dp)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) {
                                    if (currentDestination.route != destination.direction.route)
                                        navHostController.navigate(destination.direction)
                                }
                        )
                    }
            }
        }
    }
}
