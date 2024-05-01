package com.kire.market_place_android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.kire.market_place_android.presentation.NavGraphs
import com.kire.market_place_android.presentation.destinations.AdminPanelItemsEditScreenDestination
import com.kire.market_place_android.presentation.destinations.AdminPanelItemsScreenDestination
import com.kire.market_place_android.presentation.destinations.AdminPanelPickUpScreenDestination
import com.kire.market_place_android.presentation.destinations.AdminPanelScreenDestination
import com.kire.market_place_android.presentation.destinations.AdminPanelUsersScreenDestination
import com.kire.market_place_android.presentation.destinations.DeliveriesScreenDestination
import com.kire.market_place_android.presentation.destinations.FavouritesScreenDestination
import com.kire.market_place_android.presentation.destinations.ItemAddToCartMenuDestination
import com.kire.market_place_android.presentation.destinations.LogInScreenDestination
import com.kire.market_place_android.presentation.destinations.LogOnScreenDestination
import com.kire.market_place_android.presentation.destinations.ManagerScreenDestination
import com.kire.market_place_android.presentation.destinations.ProfileScreenDestination
import com.kire.market_place_android.presentation.destinations.ShoppingScreenDestination
import com.kire.market_place_android.presentation.screen.AdminPanelScreen
import com.kire.market_place_android.presentation.screen.DeliveriesScreen
import com.kire.market_place_android.presentation.screen.admin_panel_screens.AdminPanelItemsEditScreen
import com.kire.market_place_android.presentation.ui.screen.AdminPanelItemsScreen

import com.kire.market_place_android.presentation.ui.screen.AdminPanelPickUpScreen
import com.kire.market_place_android.presentation.ui.screen.AdminPanelUsersScreen
import com.kire.market_place_android.presentation.ui.screen.FavouritesScreen
import com.kire.market_place_android.presentation.ui.screen.ItemAddToCartMenu
import com.kire.market_place_android.presentation.ui.screen.LogInScreen
import com.kire.market_place_android.presentation.ui.screen.LogOnScreen
import com.kire.market_place_android.presentation.ui.screen.ManagerScreen
import com.kire.market_place_android.presentation.ui.screen.ProfileScreen
import com.kire.market_place_android.presentation.ui.screen.ShoppingScreen

import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.spec.NavHostEngine

@Composable
fun NavigationUI(
    navHostController: NavHostController,
    navHostEngine: NavHostEngine
){

    DestinationsNavHost(
        navGraph = NavGraphs.root,
        engine = navHostEngine,
        navController = navHostController
    ) {
        composable(DeliveriesScreenDestination) {
            DeliveriesScreen(
                navigator = destinationsNavigator
            )
        }
        composable(FavouritesScreenDestination) {
            FavouritesScreen(
                navigator = destinationsNavigator
            )
        }
        composable(ItemAddToCartMenuDestination) {
            ItemAddToCartMenu(
                navigator = destinationsNavigator
            )
        }
        composable(LogInScreenDestination) {
            LogInScreen(
                navigator = destinationsNavigator
            )
        }
        composable(LogOnScreenDestination) {
            LogOnScreen(
                navigator = destinationsNavigator
            )
        }
        composable(AdminPanelScreenDestination) {
            AdminPanelScreen(
                navigator = destinationsNavigator
            )
        }
        composable(AdminPanelItemsScreenDestination) {
            AdminPanelItemsScreen(
                navigator = destinationsNavigator
            )
        }
        composable(AdminPanelItemsEditScreenDestination) {
            AdminPanelItemsEditScreen(
                navigator = destinationsNavigator
            )
        }
        composable(ProfileScreenDestination) {
            ProfileScreen(
                navigator = destinationsNavigator
            )
        }
        composable(ManagerScreenDestination) {
            ManagerScreen(
                navigator = destinationsNavigator
            )
        }
        composable(ShoppingScreenDestination) {
            ShoppingScreen(
                navigator = destinationsNavigator
            )
        }
        composable(AdminPanelPickUpScreenDestination) {
            AdminPanelPickUpScreen(
                navigator = destinationsNavigator
            )
        }
        composable(AdminPanelUsersScreenDestination) {
            AdminPanelUsersScreen(
                navigator = destinationsNavigator
            )
        }
    }
}
