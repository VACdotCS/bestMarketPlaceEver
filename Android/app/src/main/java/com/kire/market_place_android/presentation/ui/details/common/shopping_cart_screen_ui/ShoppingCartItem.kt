package com.kire.market_place_android.presentation.ui.details.common.shopping_cart_screen_ui

import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kire.market_place_android.presentation.constant.ImagePath
import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.model.product.CartState
import com.kire.market_place_android.presentation.model.product.CartUiEvent
import com.kire.market_place_android.presentation.model.product.Product

import com.kire.market_place_android.presentation.ui.details.common.item_add_to_cart_menu_ui.ProductItemCounter
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.market_place_android.presentation.util.modifier.bounceClick

import com.kire.test.R

/**
 * Карточка товара в корзине
 *
 * @param name Название товара
 * @param price Цена товара
 * @param amount Количество товара
 * @param unit Единица измерения
 *
 * @author Michael Gontarev (KiREHwYE)*/
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShoppingCartItem(
    product: Product,
    cartState: CartState,
    onEvent: (CartUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    var productItemCount by remember {
        mutableIntStateOf(product.chosenQuantity)
    }

    LaunchedEffect(key1 = product) {
        productItemCount = cartState.toBuy.firstOrNull {
            it.id == product.id
        }?.chosenQuantity ?: 1
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    onEvent(CartUiEvent.productSelect(product))
                }
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .wrapContentSize(),
                contentAlignment = Alignment.BottomStart
            ) {

                AsyncImage(
                    model = product.image,
                    placeholder = painterResource(id = R.drawable.default_image),
                    error = painterResource(id = R.drawable.default_image),
                    contentDescription = "Shopping cart item image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 15.dp,
                                topEnd = 15.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 15.dp
                            )
                        )
                )

                Checkbox(
                    checked = cartState.toBuy.map { it.id }.contains(product.id) && productItemCount != 0,
                    onCheckedChange = { _ ->
                        onEvent(CartUiEvent.productSelect(product.copy(chosenQuantity = productItemCount)))
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = ExtendedTheme.colors.redAccent,
                        uncheckedColor = ExtendedTheme.colors.redAccent
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = Strings.RUB + product.price,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        text = product.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .basicMarquee(
                                animationMode = MarqueeAnimationMode.Immediately,
                                delayMillis = 0
                            )
                    )

                    Text(
                        text = productItemCount.toString() + product.unit,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }
        }


        Column(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {

            ProductItemCounter(
                curItemCount = productItemCount,
                onPlusAction = {
                    productItemCount++
                    onEvent(CartUiEvent.chooseQuantity(chosenQuantity = productItemCount, product.id))
                },
                onMinusAction = {
                    productItemCount =
                        if (productItemCount == 0)
                            0
                        else --productItemCount
                    onEvent(CartUiEvent.chooseQuantity(chosenQuantity = productItemCount, product.id))
                },
                modifier = Modifier
                    .width(100.dp)
                    .height(30.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.bin),
                contentDescription = "Bin",
                tint = ExtendedTheme.colors.redAccent,
                modifier = Modifier
                    .size(24.dp)
                    .bounceClick {
                        onEvent(CartUiEvent.deleteFromCart(product.id))
                    }
            )
        }
    }
}