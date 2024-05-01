package com.kire.market_place_android.presentation.screen.admin_panel_screens

import android.net.Uri
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
import com.kire.market_place_android.presentation.model.ProductItem
import com.kire.market_place_android.presentation.navigation.Transition.AdminPanelItemsScreenTransitions
import com.kire.market_place_android.presentation.screen.destinations.AdminPanelItemsEditScreenDestination
import com.kire.market_place_android.presentation.screen.destinations.ItemAddToCartMenuDestination
import com.kire.market_place_android.presentation.screen.destinations.ProfileScreenDestination
import com.kire.market_place_android.presentation.screen.shopping_screen_ui.ItemCard
import com.kire.test.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = AdminPanelItemsScreenTransitions::class)
@Composable
fun AdminPanelItemsScreen(
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(28.dp)
) {
    BackHandler {
        navigator.popBackStack(ProfileScreenDestination, inclusive = true)
        return@BackHandler
    }

    val itemsList: List<ProductItem> = listOf(
        ProductItem("Груши", "300.00", "кг", "250.00", Uri.EMPTY, true, ""),
        ProductItem("Помидоры", "250.00", "кг", "250.00", Uri.EMPTY, true, ""),
        ProductItem("Груши", "300.00", "кг", "250.00", Uri.EMPTY, true, ""),
        ProductItem("Помидоры", "250.00", "кг", "250.00", Uri.EMPTY, true, ""),
        ProductItem("Груши", "300.00", "кг", "250.00", Uri.EMPTY, true, "")
    )

    val editIcon = R.drawable.edit_profile_info

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues),
        content = {
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
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(itemsList) { item ->
                        ItemCard(
                            productItem = item,
                            onIconClick = { navigator.navigate(ItemAddToCartMenuDestination) },
                            onButtonClick = { navigator.navigate(AdminPanelItemsEditScreenDestination) },
                            buttonIcon = editIcon
                        )
                    }
                }
            }
        }
    )
}