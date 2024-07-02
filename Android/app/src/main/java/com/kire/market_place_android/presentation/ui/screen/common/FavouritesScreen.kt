package com.kire.market_place_android.presentation.ui.screen.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.kire.market_place_android.presentation.model.product.Product
//import com.kire.market_place_android.presentation.navigation.transition.common.FavouritesScreenTransitions
import com.kire.market_place_android.presentation.navigation.util.AppDestinations
import com.kire.market_place_android.presentation.screen.shopping_screen_ui.ItemCard
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.ListWithTopAndFab
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.TopBar
import com.kire.market_place_android.presentation.ui.screen.destinations.ItemAddToCartMenuDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.ShoppingScreenDestination
import com.kire.market_place_android.presentation.viewmodel.UserViewModel
import com.kire.test.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Экран с избранными товарами
 *
 * @param userViewModel ViewModel для работы с пользователем
 * @param navigator для навигации между экранами
 * @param paddingValues отступы от краев экрана
 *
 * @author Michael Gontarev (KiREHwYE)
 * @author Aleksey Timko (de4ltt)*/
//@Destination(style = FavouritesScreenTransitions::class)
//@Composable
//fun FavouritesScreen(
//    userViewModel: UserViewModel,
//    navigator: DestinationsNavigator,
//    paddingValues: PaddingValues = PaddingValues(start = 28.dp, end = 28.dp, bottom = 66.dp)
//) {
//
//    BackHandler {
//        navigator.popBackStack(ShoppingScreenDestination, inclusive = false)
//        return@BackHandler
//    }
//
//    val favouriteItemsList: List<Product> = listOf(
//        Product(),Product(),Product(),Product(),Product(),Product(),Product(),Product(),Product(),Product(),Product(),Product(),Product(),Product(),Product(),Product()
//    )
//
//    val plusIcon = R.drawable.plus
//
//    ListWithTopAndFab(
//        listSize = favouriteItemsList.size,
//        topBar = {
//            TopBar(destination = AppDestinations.BottomBarDestinations.FAVOURITE)
//        }
//    ) {
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2) ,
//            horizontalArrangement = Arrangement.spacedBy(10.dp),
//            verticalArrangement = Arrangement.spacedBy(10.dp),
//            modifier = it
//        ) {
//            items(favouriteItemsList) { item ->
//                ItemCard(
//                    product = item,
//                    onButtonClick = { /* TODO */ },
//                    buttonIcon = plusIcon,
//                    onClick = {
//                        navigator.navigate(ItemAddToCartMenuDestination)
//                    }
//                )
//            }
//        }
//    }
//}