package com.kire.market_place_android.presentation.ui.shopping_cart_screen_ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.test.R
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun OrderingBottomBar(
    pickUpPointAddress: String,
    deliveryClosestDate: Date,
    totalSum: Double = 0.0,
    showBottomSheet: (Boolean) -> Unit,
    sheetState: SheetState
){

    ModalBottomSheet(
        onDismissRequest = {
            showBottomSheet(false)
        },
        containerColor = Color.White,
        sheetState = sheetState,
        dragHandle = {
            Text(
                text = stringResource(id = R.string.ordering_title),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 28.dp)
            )
        },
        modifier = Modifier
            .fillMaxHeight(0.45f)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 28.dp,
                    start = 36.dp,
                    end = 36.dp
                ),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(36.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.Start
                    ) {

                        Text(
                            text = stringResource(id = R.string.delivery_to_title),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Text(
                            text = pickUpPointAddress,
                            fontWeight = FontWeight.W300,
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .basicMarquee(
                                    animationMode = MarqueeAnimationMode.Immediately,
                                    delayMillis = 0
                                )
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.Start
                    ) {

                        Text(
                            text = stringResource(id = R.string.delivery_closest_date_title),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Text(
                            text = deliveryClosestDate.day.toString() + deliveryClosestDate.month.toString(),
                            fontWeight = FontWeight.W300,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.total_sum_title),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black
                    )

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = ExtendedTheme.colors.redAccent,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(stringResource(id = R.string.rub))
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Black,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(totalSum.toString())
                            }
                        }
                    )


                }

                Button(
                    onClick = {
                        /*TODO()*/
                    },
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ExtendedTheme.colors.redAccent
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.order_suggestion),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}