package com.kire.market_place_android.presentation.ui.order_screen_ui

import android.graphics.Bitmap

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.basicMarquee
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.AsyncImage

import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme

import com.kire.test.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderItem(
    name: String,
    price: String,
    amount: Double = 0.0,
    orderDate: String = "29.09.23",
    unit: String = "кг"
) {

    val checked = remember { mutableStateOf(false) }


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
                    text = stringResource(id = R.string.rub) + price.toString(),
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
                        text = name,
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
                        text = amount.toString() + unit,
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
                checked = checked.value,
                onCheckedChange = {
                        isChecked -> checked.value = isChecked
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = ExtendedTheme.colors.redAccent
                )
            )

            Text(
                text = stringResource(id = R.string.order_date) + " " + orderDate,
                fontWeight = FontWeight.W300,
                fontSize = 12.sp,
                color = Color.Black
            )
        }
    }
}