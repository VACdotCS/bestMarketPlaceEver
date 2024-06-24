package com.kire.market_place_android.presentation.ui.screen.admin

import androidx.activity.compose.BackHandler

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import com.kire.market_place_android.presentation.model.product.Product
import com.kire.market_place_android.presentation.navigation.transition.AdminPanelItemsScreenTransitions
import com.kire.market_place_android.presentation.navigation.util.AppDestinations
import com.kire.market_place_android.presentation.screen.shopping_screen_ui.ItemCard
import com.kire.market_place_android.presentation.ui.details.common_screen.cross_screen_ui.TopBar
import com.kire.market_place_android.presentation.ui.screen.destinations.AdminPanelItemsEditScreenDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.ItemAddToCartMenuDestination
import com.kire.market_place_android.presentation.viewmodel.AdminViewModel

import com.kire.test.R

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * By Aleksey Timko (de4ltt)*/
@Destination(style = AdminPanelItemsScreenTransitions::class)
@Composable
fun AdminPanelItemsScreen(
    adminViewModel: AdminViewModel,
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(start = 28.dp, end = 28.dp)
) {

    BackHandler {
        navigator.popBackStack()
        return@BackHandler
    }

    val itemsList: List<Product> = listOf(
        Product()
    )

    val editIcon = R.drawable.edit_profile_info

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopBar(destination = AppDestinations.AdminDestinations.ADMIN_PANEL_ITEMS)

//        Spacer(modifier = Modifier.height(12.dp))

        when (itemsList.size) {
            0 -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                    content = {
                        Text(
                            text = stringResource(R.string.nothing_was_found_fav),
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                    }
                )
            }

            else -> LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 28.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(itemsList) { item ->
                    ItemCard(
                        product = item,
                        onClick = { navigator.navigate(ItemAddToCartMenuDestination) },
                        onButtonClick = { navigator.navigate(AdminPanelItemsEditScreenDestination) },
                        buttonIcon = editIcon
                    )
                }
            }
        }
    }
}