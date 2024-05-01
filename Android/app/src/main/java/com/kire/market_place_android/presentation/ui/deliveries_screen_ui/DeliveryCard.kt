package com.kire.market_place_android.presentation.screen.deliveries_screen_ui

import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kire.market_place_android.presentation.model.Delivery
import com.kire.market_place_android.presentation.model.DeliveryStatus
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.test.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DeliveryCard(
    delivery: Delivery
) {
    delivery.apply {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
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
                    model = Bitmap.createBitmap(120, 120, Bitmap.Config.RGB_565),
                    /*placeholder = painterResource(id = R.drawable.default_image) ,*/
                    contentDescription = "Deliveries item image",
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
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.rub) + "${productItem.itemPrice.toDouble() * quantity}",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Row(
                            modifier = Modifier
                                .height(26.dp)
                                .width(
                                    width = when (deliveryStatus) {
                                        DeliveryStatus.DENIED -> 65.dp
                                        DeliveryStatus.READY -> 110.dp
                                        DeliveryStatus.ON_THE_WAY -> 65.dp
                                        DeliveryStatus.DELIVERED -> 110.dp
                                    }
                                )
                                .background(
                                    color = when (deliveryStatus) {
                                        DeliveryStatus.DENIED -> ExtendedTheme.colors.deniedBar
                                        DeliveryStatus.READY -> ExtendedTheme.colors.readyBar
                                        DeliveryStatus.ON_THE_WAY -> ExtendedTheme.colors.onTheWayBar
                                        DeliveryStatus.DELIVERED -> ExtendedTheme.colors.deliveredBar
                                    },
                                    shape = RoundedCornerShape(25.dp)
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            content = {
                                Text(
                                    text = when (deliveryStatus) {
                                        DeliveryStatus.DENIED -> stringResource(id = R.string.denied)
                                        DeliveryStatus.READY -> stringResource(id = R.string.ready)
                                        DeliveryStatus.ON_THE_WAY -> stringResource(id = R.string.on_the_way)
                                        DeliveryStatus.DELIVERED -> stringResource(id = R.string.delivered)
                                    },
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontStyle = FontStyle.Italic
                                )
                            }
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {

                        Text(
                            text = productItem.itemName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .basicMarquee(
                                    animationMode = MarqueeAnimationMode.Immediately,
                                    delayMillis = 0
                                )
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$quantity${productItem.itemScale}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Gray
                            )

                            Text(
                                text = when (deliveryStatus) {
                                    DeliveryStatus.DENIED -> deliveryEnd ?: ""
                                    DeliveryStatus.READY -> "${stringResource(id = R.string.code)}: $deliveryCode"
                                    DeliveryStatus.ON_THE_WAY -> deliveryEstimate
                                    DeliveryStatus.DELIVERED -> deliveryEnd ?: ""
                                },
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }
    }
}