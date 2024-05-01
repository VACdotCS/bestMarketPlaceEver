package com.kire.market_place_android.presentation.navigation.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.kire.market_place_android.presentation.screen.destinations.AdminPanelItemsScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.AdminPanelPickUpScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.AdminPanelScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.AdminPanelUsersScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.FavouritesScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.LogInScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.LogOnScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.ManagerScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.ProfileScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.ShoppingCartScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.ShoppingScreenDestination
import com.kire.test.R
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class AppBarsDestination(
    val direction: DirectionDestinationSpec,
    @DrawableRes val iconTop: Int?,
    @DrawableRes val iconBottom: Int?,
    @StringRes val label: Int,
    @DrawableRes val plusButton: Int?
) {

    //Uncomment when the main screens are made
//    SHOPPING(ShoppingScreenDestination, R.drawable.shopping_top_bar, R.drawable.home_bottom_bar, R.string.home_screen_label),
//    FAVOURITE(FavouriteScreenDestination, R.drawable.favourite_top_bar, R.drawable.favourite_bottom_bar, R.string.favourite_screen_label),
//    PROFILE(ProfileScreenDestination, R.drawable.profile_top_bar, R.drawable.profile_bottom_bar, R.string.profile_screen_label),
//    SHOPPING_CART(DeliveriesScreenDestination, R.drawable.shopping_cart_top_bar, R.drawable.shopping_cart_bottom_bar, R.string.shopping_cart_screen_label)

    //Delete when the main screens are made
    SHOPPING(ShoppingScreenDestination, null, R.drawable.shopping_bottom_bar, R.string.home_screen_label, null),
    FAVOURITE(FavouritesScreenDestination, R.drawable.favourite_top_bar, R.drawable.favourite_bottom_bar, R.string.favourite_screen_label, null),
    ADMIN_PANEL(AdminPanelScreenDestination, null, R.drawable.admin_panel_icon, R.string.manager_screen_label, null),
    PROFILE(ProfileScreenDestination, R.drawable.profile_top_bar, R.drawable.profile_bottom_bar, R.string.profile_screen_label, null),

    ADMIN_PANEL_ITEMS(AdminPanelItemsScreenDestination, R.drawable.admin_panel_icon, null, R.string.items, R.drawable.plus_bold),
    ADMIN_PANEL_USERS(AdminPanelUsersScreenDestination, R.drawable.admin_panel_icon, null, R.string.users, null),
    ADMIN_PANEL_PICK_UP(AdminPanelPickUpScreenDestination, R.drawable.admin_panel_icon, null, R.string.pick_up_points, R.drawable.plus_bold ),

    MANAGER(ManagerScreenDestination, R.drawable.manager_top_bottom_bars, R.drawable.manager_top_bottom_bars, R.string.manager_screen_label, null),
    SHOPPING_CART(ShoppingCartScreenDestination, R.drawable.shopping_cart_top_bar, R.drawable.shopping_cart_bottom_bar, R.string.shopping_cart_screen_label, null)
}