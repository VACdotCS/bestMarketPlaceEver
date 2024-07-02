package com.kire.market_place_android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.kire.market_place_android.presentation.ui.screen.NavGraphs

import com.kire.market_place_android.presentation.ui.screen.admin.AdminPanelScreen
import com.kire.market_place_android.presentation.ui.screen.admin.AdminPanelItemsEditScreen
import com.kire.market_place_android.presentation.ui.screen.admin.AdminPanelItemsScreen

import com.kire.market_place_android.presentation.ui.screen.admin.AdminPanelPickUpScreen
import com.kire.market_place_android.presentation.ui.screen.admin.AdminPanelUsersScreen
import com.kire.market_place_android.presentation.ui.screen.common.DeliveriesScreen
//import com.kire.market_place_android.presentation.ui.screen.common.FavouritesScreen
import com.kire.market_place_android.presentation.ui.screen.common.ItemAddToCartMenu
import com.kire.market_place_android.presentation.ui.screen.common.LogInScreen
import com.kire.market_place_android.presentation.ui.screen.common.LogOnScreen
import com.kire.market_place_android.presentation.ui.screen.manager.ManagerScreen
import com.kire.market_place_android.presentation.ui.screen.common.ProfileScreen
import com.kire.market_place_android.presentation.ui.screen.common.ShoppingCartScreen
import com.kire.market_place_android.presentation.ui.screen.common.ShoppingScreen
import com.kire.market_place_android.presentation.ui.screen.destinations.AdminPanelItemsEditScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.AdminPanelItemsScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.AdminPanelPickUpScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.AdminPanelScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.AdminPanelUsersScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.DeliveriesScreenDestination
//import com.kire.market_place_android.presentation.ui.screen.destinations.FavouritesScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.ItemAddToCartMenuDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.LogInScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.LogOnScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.ManagerScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.OrderScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.ProfileScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.ShoppingCartScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.ShoppingScreenDestination
import com.kire.market_place_android.presentation.ui.screen.manager.OrderScreen
import com.kire.market_place_android.presentation.viewmodel.AdminViewModel
import com.kire.market_place_android.presentation.viewmodel.AuthViewModel
import com.kire.market_place_android.presentation.viewmodel.ManagerViewModel
import com.kire.market_place_android.presentation.viewmodel.OrderViewModel
import com.kire.market_place_android.presentation.viewmodel.ProductViewModel
import com.kire.market_place_android.presentation.viewmodel.UserViewModel

import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.spec.NavHostEngine

/**
 * By Michael Gontarev (KiREHwYE)
 * By Aleksey Timko (de4ltt)*/
@Composable
fun NavigationUI(
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel,
    adminViewModel: AdminViewModel,
    managerViewModel: ManagerViewModel,
    productViewModel: ProductViewModel,
    orderViewModel: OrderViewModel,
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
                orderViewModel = orderViewModel,
                navigator = destinationsNavigator
            )
        }
//        composable(FavouritesScreenDestination) {
//            FavouritesScreen(
//                userViewModel = userViewModel,
//                navigator = destinationsNavigator
//            )
//        }
        composable(ItemAddToCartMenuDestination) {
            ItemAddToCartMenu(
                productViewModel = productViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(OrderScreenDestination) {
            OrderScreen(
                navigator = destinationsNavigator,
                managerViewModel = managerViewModel
            )
        }
        composable(LogInScreenDestination) {
            LogInScreen(
                authViewModel = authViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(LogOnScreenDestination) {
            LogOnScreen(
                authViewModel = authViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(AdminPanelScreenDestination) {
            AdminPanelScreen(
                adminViewModel = adminViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(AdminPanelItemsScreenDestination) {
            AdminPanelItemsScreen(
                adminViewModel = adminViewModel,
                productViewModel = productViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(AdminPanelItemsEditScreenDestination) {
            AdminPanelItemsEditScreen(
                productViewModel = productViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(ProfileScreenDestination) {
            ProfileScreen(
                userViewModel = userViewModel,
                orders = orderViewModel.orders,
                navigator = destinationsNavigator
            )
        }
        composable(ManagerScreenDestination) {
            ManagerScreen(
                managerViewModel = managerViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(ShoppingScreenDestination) {
            ShoppingScreen(
                productViewModel = productViewModel,
                userViewModel = userViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(AdminPanelPickUpScreenDestination) {
            AdminPanelPickUpScreen(
                adminViewModel = adminViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(AdminPanelUsersScreenDestination) {
            AdminPanelUsersScreen(
                adminViewModel = adminViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(ShoppingCartScreenDestination){
            ShoppingCartScreen(
                productViewModel = productViewModel,
                chosenPickUpPoint = userViewModel.chosenPickUpPoint,
                navController = navHostController,
                orderViewModel = orderViewModel
            )
        }
    }
}
