package com.kire.market_place_android.presentation.screen.shopping_screen_ui

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.AsyncImage
import com.kire.market_place_android.presentation.constant.ImagePath
import com.kire.market_place_android.presentation.constant.Strings

import com.kire.market_place_android.presentation.model.product.Product
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.market_place_android.presentation.util.modifier.bounceClick

import com.kire.test.R

/**
 * Плитка товара
 *
 * @param product товар
 * @param onWholeElementClick клик по карточке
 * @param onSmallButtonClick клик по кнопке
 * @param buttonIcon иконка кнопки
 *
 * @author Aleksey Timko (de4ltt)
 * @author Michael Gontarev (KiREHwYE)
 * */
@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun ItemCard(
    product: Product,
    onWholeElementClick: () -> Unit,
    onSmallButtonClick: () -> Unit,
    @DrawableRes buttonIcon: Int,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures {
                    onWholeElementClick()
                }
            },
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        product.apply {

            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                AsyncImage(
                    model = product.image,
                    placeholder = painterResource(id = R.drawable.default_image),
                    error = painterResource(id = R.drawable.default_image),
                    contentDescription = "Item card image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(
                            RoundedCornerShape(
                                topStart = 15.dp,
                                topEnd = 15.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 15.dp
                            )
                        )
                        .aspectRatio(1f / 1f)
                )

//                Box(
//                    modifier = Modifier
//                        .size(55.dp)
//                        .padding(end = 10.dp, top = 10.dp)
//                        .clip(CircleShape)
//                        .background(Color.White)
//                        .align(Alignment.TopEnd),
//                    contentAlignment = Alignment.Center,
//                    content = {
//                        Icon(
//                            painter = painterResource(id = R.drawable.favourite_top_bar),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .size(25.dp)
//                                .clickable(
//                                    indication = null,
//                                    interactionSource = MutableInteractionSource(),
//                                    onClick = { /* TODO */ }
//                                ),
//                            tint = if (false) ExtendedTheme.colors.redAccent else Color.LightGray
//                        )
//                    }
//                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = title,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier
                        .basicMarquee(
                            animationMode = MarqueeAnimationMode.Immediately,
                            delayMillis = 0
                        )
                )

                Text(
                    text = quantityAvailable.toString() + unit,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier
                        .basicMarquee(
                            animationMode = MarqueeAnimationMode.Immediately,
                            delayMillis = 0
                        )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (price == discountPrice)
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Black,
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            ) {
                                append(Strings.RUB + price.toString())
                            }
                        }
                    else
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            ) {
                                append(Strings.RUB + discountPrice.toString())
                            }
                            append("\n")
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    textDecoration = TextDecoration.LineThrough
                                )
                            ) {
                                append("₽$price")
                            }
                        },
                    lineHeight = if (price == discountPrice) 19.sp else 17.sp
                )

                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .bounceClick {
                            onSmallButtonClick()
                        }
                        .background(
                            ExtendedTheme.colors.redAccent,
                            RoundedCornerShape(5.dp)
                        ),
                    contentAlignment = Alignment.Center,
                    content = {
                        Icon(
                            painter = painterResource(buttonIcon),
                            contentDescription = "icon",
                            tint = Color.White,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                )
            }
        }
    }
}