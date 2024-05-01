package com.kire.market_place_android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.kire.market_place_android.presentation.screen.admin_panel_screens.AdminPanelItemsEditScreen
import com.kire.market_place_android.presentation.screen.admin_panel_screens.AdminPanelItemsScreen
import com.kire.market_place_android.presentation.screen.AdminPanelScreen
import com.kire.market_place_android.presentation.screen.FavouritesScreen
import com.kire.market_place_android.presentation.screen.ItemAddToCartMenu
import com.kire.market_place_android.presentation.screen.LogInScreen
import com.kire.market_place_android.presentation.screen.LogOnScreen
import com.kire.market_place_android.presentation.screen.ManagerScreen
import com.kire.market_place_android.presentation.screen.NavGraphs
import com.kire.market_place_android.presentation.screen.ProfileScreen
import com.kire.market_place_android.presentation.screen.ShoppingScreen
import com.kire.market_place_android.presentation.screen.admin_panel_screens.AdminPanelPickUpScreen
import com.kire.market_place_android.presentation.screen.admin_panel_screens.AdminPanelUsersScreen
import com.kire.market_place_android.presentation.screen.destinations.AdminPanelItemsEditScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.AdminPanelItemsScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.AdminPanelPickUpScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.AdminPanelScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.AdminPanelUsersScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.FavouritesScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.ItemAddToCartMenuDestination
import com.kire.market_place_android.presentation.screen.destinations.LogInScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.LogOnScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.ManagerScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.ProfileScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.ShoppingScreenDestination
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
            AdminPanelPickUpScreen()
        }
        composable(AdminPanelUsersScreenDestination) {
            AdminPanelUsersScreen()
        }
    }
}
