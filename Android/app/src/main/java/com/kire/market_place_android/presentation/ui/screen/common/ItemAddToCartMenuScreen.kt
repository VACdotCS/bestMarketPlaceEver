package com.kire.market_place_android.presentation.ui.screen.common

import androidx.activity.compose.BackHandler

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import coil.compose.AsyncImage

import coil.request.ImageRequest
import com.kire.market_place_android.presentation.constant.ImagePath

import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.model.product.CartUiEvent
import com.kire.market_place_android.presentation.navigation.transition.common.ItemAddToCartMenuScreenTransitions
import com.kire.market_place_android.presentation.ui.details.common.item_add_to_cart_menu_ui.BottomButtonFinishOperation
import com.kire.market_place_android.presentation.ui.details.common.item_add_to_cart_menu_ui.ItemAddToCartEditTopControls
import com.kire.market_place_android.presentation.ui.details.common.item_add_to_cart_menu_ui.ItemsAddToCartMenuCarousel
import com.kire.market_place_android.presentation.ui.details.common.item_add_to_cart_menu_ui.ProductItemCounter
import com.kire.market_place_android.presentation.ui.screen.destinations.ItemAddToCartMenuDestination
import com.kire.market_place_android.presentation.ui.screen.destinations.ShoppingCartScreenDestination
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.market_place_android.presentation.viewmodel.ProductViewModel

import com.kire.test.R

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Экран товара
 *
 * @param productViewModel ViewModel для работы с товарами
 * @param navigator для навигации между экранами
 * @param paddingValues отступы от краев экрана
 *
 * @author Michael Gontarev (KiREHwYE)
 * @author Aleksey Timko (de4ltt)*/
@OptIn(ExperimentalFoundationApi::class)
@Destination(style = ItemAddToCartMenuScreenTransitions::class)
@Composable
fun ItemAddToCartMenu(
    productViewModel: ProductViewModel,
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(28.dp)
) {

    BackHandler {
        navigator.popBackStack()
        return@BackHandler
    }

    val product by productViewModel.chosenProduct.collectAsStateWithLifecycle()
    val products by productViewModel.allProducts.collectAsStateWithLifecycle()

    var productItemCount by remember {
        mutableStateOf(1)
    }

    product.apply {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .pointerInput(Unit) {
                    detectVerticalDragGestures { _, dragAmount ->

                        val y = dragAmount

                        if (y > 60) {
                            navigator.popBackStack(
                                ItemAddToCartMenuDestination,
                                inclusive = true
                            )
                        }
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = product.image,
                placeholder = painterResource(id = R.drawable.item_menu_default),
                error = painterResource(id = R.drawable.default_image),
                contentDescription = "Shopping cart item image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .shadow(
                        elevation = 12.dp,
                        spotColor = Color.Gray,
                        ambientColor = Color.Black,
                        shape = RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp
                        )
                    )
                    .background(
                        Color.White,
                        RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp
                        )
                    )
                    .padding(top = 28.dp, bottom = 28.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 28.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(
                            modifier = Modifier
                                .wrapContentHeight(),
                            horizontalAlignment = Alignment.Start
                        ) {

                            Text(
                                text = title,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                                    .basicMarquee(
                                        animationMode = MarqueeAnimationMode.Immediately,
                                        delayMillis = 0
                                    )
                            )

                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = ExtendedTheme.colors.greenPrice,
                                            fontSize = 25.sp,
                                            fontWeight = FontWeight.ExtraBold
                                        )
                                    ) {
                                        append(Strings.RUB + discountPrice.toString())
                                    }

                                    append("\n")

                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = Color.DarkGray,
                                            fontSize = 19.sp,
                                            textDecoration = TextDecoration.LineThrough
                                        )
                                    ) {
                                        append(Strings.RUB + "$price")
                                    }
                                }
                            )
                        }

                        ProductItemCounter(
                            productItemCount,
                            { productItemCount++ },
                            {
                                productItemCount =
                                    if (productItemCount == 0) 0 else --productItemCount
                            }
                        )
                    }

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(Strings.DESCRIPTION)
                            }

                            append("\n")

                            withStyle(
                                style = SpanStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.DarkGray
                                )
                            ) {
                                append(description)
                            }
                        },
                        maxLines = 2,
                        modifier = Modifier.padding(start = 28.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        text = Strings.ITEMS_FROM_THIS_CATEGORY,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 28.dp)
                    )

                    val productsWithSameCategory = products.filter { it.category == product.category }

                    ItemsAddToCartMenuCarousel(
                        itemsList = productsWithSameCategory,
                        onEvent = productViewModel::onEvent
                    )
                }

                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 28.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    BottomButtonFinishOperation(
                        textValue = Strings.ADD_TO_CART + " - " + Strings.RUB + "${price.toDouble() * productItemCount}",
                        onClick = {
                            productViewModel.onEvent(CartUiEvent.addToCart(product.copy(chosenQuantity = productItemCount)))
                            navigator.navigate(ShoppingCartScreenDestination)
                        }
                    )
                }
            }
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopStart
        ) {

            ItemAddToCartEditTopControls(
                onArrowBackClick = {
                    navigator.popBackStack()
                }
            )
        }
    }
}

