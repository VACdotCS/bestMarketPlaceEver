package com.kire.market_place_android.presentation.screen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kire.market_place_android.presentation.model.ProductItem
import com.kire.market_place_android.presentation.screen.item_add_to_cart_menu.BottomButtonFinishOperation
import com.kire.market_place_android.presentation.screen.item_add_to_cart_menu.ItemsAddToCartMenuCarousel
import com.kire.market_place_android.presentation.screen.item_add_to_cart_menu.ProductItemCounter
import com.kire.market_place_android.presentation.theme.ExtendedTheme
import com.kire.test.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnrememberedMutableInteractionSource")
@Destination
@Composable
fun ItemAddToCartMenu(
    productItem: ProductItem = ProductItem(
        "Помидоры", "250.00",
        "кг", "250.00",
        Uri.EMPTY, false,
        "Помогите, они украли мою семью..."
    ),
    navigator: DestinationsNavigator
) {
    var productItemCount by remember {
        mutableIntStateOf(1)
    }

    productItem.apply {
        //TODO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(430.dp),
            content = {
                AsyncImage(
                    model = Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Yellow)
                    ),
                    /*placeholder = painterResource(id = R.drawable.item_menu_default) ,*/
                    contentDescription = "Item Cart Image",
                    contentScale = ContentScale.Crop
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 45.dp,
                            start = 20.dp,
                            end = 20.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .size(55.dp)
                            .background(Color.White, RoundedCornerShape(30.dp)),
                        contentAlignment = Alignment.Center,
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_back),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(27.dp)
                                    .clickable(
                                        indication = null,
                                        interactionSource = MutableInteractionSource(),
                                        onClick = { navigator.popBackStack() }
                                    ),
                                tint = Color.Black
                            )
                        }
                    )

                    Box(
                        modifier = Modifier
                            .size(55.dp)
                            .background(Color.White, RoundedCornerShape(30.dp)),
                        contentAlignment = Alignment.Center,
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.favourite_top_bar),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(25.dp)
                                    .clickable(
                                        indication = null,
                                        interactionSource = MutableInteractionSource(),
                                        onClick = { /* TODO */ }
                                    ),
                                tint = if (isFavourite) ExtendedTheme.colors.redAccent else Color.LightGray
                            )
                        }
                    )
                }

            }
        )

        Column(
            modifier = Modifier
                .padding(top = 405.dp)
                .fillMaxSize()
                .background(
                    Color.White,
                    RoundedCornerShape(
                        topStart = 25.dp,
                        topEnd = 25.dp
                    )
                ),
            content = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                        .padding(
                            top = 25.dp,
                            start = 25.dp,
                            end = 25.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = itemName,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .width(200.dp)
                            .basicMarquee(
                                animationMode = MarqueeAnimationMode.Immediately,
                                delayMillis = 0
                            )
                    )

                    ProductItemCounter(
                        productItemCount,
                        { productItemCount++ },
                        { productItemCount = if (productItemCount == 0) 0 else --productItemCount }
                    )
                }

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = ExtendedTheme.colors.greenPrice,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        ) {
                            append("₽$itemPrice")
                        }

                        append("\t\t")

                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.DarkGray,
                                fontSize = 21.sp
                            )
                        ) {
                            append("1$itemScale")
                        }
                    },
                    modifier = Modifier.padding(start = 25.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(stringResource(id = R.string.description))
                            }

                            append("\n")

                            withStyle(
                                style = SpanStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.DarkGray
                                )
                            ) {
                                append(itemDescription)
                            }
                        },
                        modifier = Modifier.padding(horizontal = 25.dp),
                        maxLines = 2
                    )

                    Column{
                        Text(
                            text = stringResource(id = R.string.items_from_this_category),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 25.dp)
                        )
                        
                        Spacer(modifier = Modifier.size(12.dp))
                        
                        //TODO
                        //temporary
                        val itemsList: List<ProductItem> = listOf(
                            ProductItem("Помидоры", "250.00", "кг", "250.00", Uri.EMPTY, false, ""),
                            ProductItem("Груши", "300.00", "кг", "250.00", Uri.EMPTY, true, ""),
                            ProductItem("Помидоры", "250.00", "кг", "300.00", Uri.EMPTY, true, ""),
                            ProductItem("Груши", "300.00", "кг", "250.00", Uri.EMPTY, false, ""),
                            ProductItem("Помидоры", "250.00", "кг", "250.00", Uri.EMPTY, false, ""),
                            ProductItem("Груши", "300.00", "кг", "250.00", Uri.EMPTY, true, "")
                        )

                        ItemsAddToCartMenuCarousel(itemsList = itemsList)
                    }

                    Spacer(modifier = Modifier.size(15.dp))

                    BottomButtonFinishOperation(
                        textValue = stringResource(id = R.string.add_to_cart) + " - " + "₽" + "${itemPrice.toDouble() * productItemCount}",
                        onClick = { /* TODO */ }
                    )

                    Spacer(modifier = Modifier.size(15.dp))
                }
            }
        )
    }
}