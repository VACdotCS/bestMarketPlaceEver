package com.kire.market_place_android.presentation.ui.details.manager.order_screen_ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kire.market_place_android.presentation.constant.ImagePath

import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.model.manager.ManagerOrderState
import com.kire.market_place_android.presentation.model.manager.ManagerOrderUiEvent
import com.kire.market_place_android.presentation.model.order.OrderedProduct

import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme

import com.kire.test.R

/**
 * Карточка заказа
 *
 * @param managerOrderState состояние интерфейса заказа
 * @param orderedProduct заказанный товар
 * @param onEvent действие пользователя
 *
 * @author Michael Gontarev (KiREHwYE)*/
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderItem(
    managerOrderState: ManagerOrderState,
    orderedProduct: OrderedProduct,
    onEvent: (ManagerOrderUiEvent) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    onEvent(ManagerOrderUiEvent.productSelect(orderedProduct.product.id))
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

            AsyncImage(
                model = orderedProduct.product.image,
                placeholder = painterResource(id = R.drawable.default_image),
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

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = Strings.RUB + orderedProduct.price,
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
                        text = orderedProduct.product.title,
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
                        text = orderedProduct.quantity.toString() + orderedProduct.product.unit,
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

            Checkbox(
                checked = managerOrderState.received.contains(orderedProduct.product.id),
                onCheckedChange = {
                    onEvent(ManagerOrderUiEvent.productSelect(orderedProduct.product.id))
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = ExtendedTheme.colors.redAccent,
                    uncheckedColor = ExtendedTheme.colors.redAccent
                )
            )

            Text(
                text = Strings.ORDER_DATE + " " + orderedProduct.completionDate,
                fontWeight = FontWeight.W300,
                fontSize = 12.sp,
                color = Color.Black
            )
        }
    }
}