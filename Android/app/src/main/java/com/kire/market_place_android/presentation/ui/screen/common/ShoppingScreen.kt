package com.kire.market_place_android.presentation.ui.screen.common

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kire.market_place_android.presentation.constant.BottomBarHeight
import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.model.product.CartUiEvent
import com.kire.market_place_android.presentation.model.user.UserUiEvent

import com.kire.market_place_android.presentation.navigation.transition.common.ShoppingScreenTransitions

import com.kire.market_place_android.presentation.navigation.util.AppDestinations
import com.kire.market_place_android.presentation.screen.shopping_screen_ui.ItemCard
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.ListWithTopAndFab
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.RequestResultMessage
import com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui.TopBar
import com.kire.market_place_android.presentation.ui.details.common.shopping_screen_ui.FilterBottomBar
import com.kire.market_place_android.presentation.ui.details.common.shopping_screen_ui.PickUpPointsBottomBar
import com.kire.market_place_android.presentation.ui.details.common.shopping_screen_ui.ShoppingScreenSearchBar
import com.kire.market_place_android.presentation.ui.screen.destinations.ItemAddToCartMenuDestination
import com.kire.market_place_android.presentation.util.modifier.bounceClick
import com.kire.market_place_android.presentation.util.search.onSearchRequestChange
import com.kire.market_place_android.presentation.viewmodel.ProductViewModel
import com.kire.market_place_android.presentation.viewmodel.UserViewModel

import com.kire.test.R

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.math.BigDecimal

/**
 * Основной экран магазина со списком товаров
 *
 * @param productViewModel ViewModel для работы с товарами
 * @param navigator для навигации между экранами
 * @param paddingValues отступы от краёв экрана
 *
 * @author Michael Gontarev (KiREHwYE)
 * @author Aleksey Timko (de4ltt)*/
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Destination(style = ShoppingScreenTransitions::class)
@Composable
fun ShoppingScreen(
    productViewModel: ProductViewModel,
    userViewModel: UserViewModel,
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(start = 28.dp, end = 28.dp, bottom = 66.dp)
) {

    val allProducts by productViewModel.allProducts.collectAsStateWithLifecycle()
    val allCategories by productViewModel.allCategories.collectAsStateWithLifecycle()

    val chosenPickUpPoint by userViewModel.chosenPickUpPoint.collectAsStateWithLifecycle()

    var searchRequestString: String by remember {
        mutableStateOf("")
    }
    var searchRequestCategory: List<String> by remember {
        mutableStateOf(emptyList())
    }
    var searchRequestPriceRange: Pair<BigDecimal, BigDecimal> by remember {
        mutableStateOf(Pair(BigDecimal.ZERO, Double.MAX_VALUE.toBigDecimal()))
    }

    val searchRequestProducts = onSearchRequestChange(
        products = allProducts,
        searchString = searchRequestString,
        categoryList = searchRequestCategory,
        priceRange = searchRequestPriceRange
    )

    RequestResultMessage(
        requestResultStateFlow = productViewModel.requestResult,
        makeRequestResultIdle = productViewModel::makeRequestResultIdle
    )
    RequestResultMessage(
        requestResultStateFlow = userViewModel.requestResult,
        makeRequestResultIdle = userViewModel::makeRequestResultIdle
    )

    val plusIcon = R.drawable.plus

    var isFilterShown by remember {
        mutableStateOf(false)
    }

    var isPickUpPointBarShown by remember {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState()

    ListWithTopAndFab(
        listSize = searchRequestProducts.size,
        topBar = {
            TopBar(
                destination = AppDestinations.BottomBarDestinations.SHOPPING,
                pickUpPointInfoBar = {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(start = 4.dp)
                            .bounceClick {
                                isPickUpPointBarShown = true
                            },
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = Strings.PICK_UP_POINT,
                            fontWeight = FontWeight.Light,
                            fontSize = 15.sp,
                            color = Color.DarkGray,
                            modifier = Modifier
                                .basicMarquee(
                                    animationMode = MarqueeAnimationMode.Immediately,
                                    delayMillis = 0
                                )
                        )

                        Text(
                            text =
                                if (chosenPickUpPoint.address.isNotEmpty())
                                    chosenPickUpPoint.address
                                else
                                    Strings.NO_CHOSEN_PICK_UP_POINT,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .basicMarquee(
                                    animationMode = MarqueeAnimationMode.Immediately,
                                    delayMillis = 0
                                )
                        )
                    }
                },
                searchBar = {
                    ShoppingScreenSearchBar(
                        changeSearchRequest = { it ->
                            searchRequestString = it
                        },
                        curSearchRequest = searchRequestString,
                        showFilter = {
                            isFilterShown = it
                        }
                    )
                }
            )
        }
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 28.dp),
            modifier = it.padding(bottom = BottomBarHeight.BOTTOM_BAR_HEIGHT)
        ) {
            items(
                searchRequestProducts,
                key = {it.id}
            ) { product ->
                ItemCard(
                    product = product,
                    onSmallButtonClick = {
                        productViewModel.onEvent(CartUiEvent.addToCart(product.copy(chosenQuantity = 1)))
                    },
                    buttonIcon = plusIcon,
                    onWholeElementClick = {
                        productViewModel.onEvent(CartUiEvent.changeChosenProduct(product))
                        navigator.navigate(ItemAddToCartMenuDestination)
                    },
                    modifier = Modifier.animateItemPlacement(
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = LinearOutSlowInEasing
                        )
                    )
                )
            }
        }
    }


    if (isPickUpPointBarShown)
        PickUpPointsBottomBar(
            chosenPickUpPointId = chosenPickUpPoint.id,
            pickUpPoints = userViewModel.allPickUpPoints,
            showBottomSheet = {
                isPickUpPointBarShown = it
            },
            onClick = { pickUpPoint ->
                userViewModel.onEvent(UserUiEvent.ChoosePickUpPoint(pickUpPoint))
            },
            sheetState = sheetState
        )

    if (isFilterShown)
        FilterBottomBar(
            curChosenCategories = searchRequestCategory,
            curChosenPriceRange = searchRequestPriceRange,
            allCategories = allCategories,
            showBottomSheet = {
                isFilterShown = it
            },
            sheetState = sheetState,
            changeFilterRequest = { categoryList, priceRange ->
                searchRequestCategory = categoryList
                searchRequestPriceRange = priceRange
            }
        )
}
