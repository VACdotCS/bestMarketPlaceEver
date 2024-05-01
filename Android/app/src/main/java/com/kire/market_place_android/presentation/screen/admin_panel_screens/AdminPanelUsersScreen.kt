package com.kire.market_place_android.presentation.screen.admin_panel_screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kire.market_place_android.presentation.model.User
import com.kire.market_place_android.presentation.model.UserRole
import com.kire.market_place_android.presentation.screen.admin_panel_users_screen_ui.UserBar
import com.kire.market_place_android.presentation.screen.destinations.ProfileScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AdminPanelUsersScreen(
    paddingValues: PaddingValues = PaddingValues(28.dp),
    navigator: DestinationsNavigator
) {

    BackHandler {
        navigator.popBackStack(ProfileScreenDestination, inclusive = true)
        return@BackHandler
    }

    val users: List<User> = listOf(
        User(
            userId = 1,
            userName = "Хебоб",
            userRole = UserRole.MANAGER,
            userDiscount = 0,
            userSpent = 1000.0
        ),
        User(
            userId = 2,
            userName = "Александр",
            userRole = UserRole.MANAGER,
            userDiscount = 0,
            userSpent = 10030.0
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(users) {
            UserBar(user = it)
        }
    }
}