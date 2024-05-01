package com.kire.market_place_android.presentation.screen.admin_panel_pick_up_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.model.PickUpPoint
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.test.R

@Composable
fun AdminPickUpCard(
    pickUpPoint: PickUpPoint,
    onEditClick: (Boolean) -> Unit
) {

    pickUpPoint.apply {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(
                    ExtendedTheme.colors.profileBar,
                    RoundedCornerShape(12.dp)
                )
                .padding(
                    horizontal = 20.dp,
                    vertical = 15.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp
                            )
                        ) {
                            append("КРД-${pickUpPoint.id}\n")
                        }

                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Light,
                                fontSize = 15.sp,
                                color = Color.DarkGray
                            )
                        ) {
                            append(pickUpPoint.adress)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.9f),
                    lineHeight = 18.sp
                )

                Icon(
                    painter = painterResource(id = R.drawable.pencil),
                    contentDescription = "edit",
                    tint = ExtendedTheme.colors.redAccent,
                    modifier = Modifier.padding(top = 5.dp).size(20.dp)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                onEditClick(true)
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(stringResource(id = R.string.manager) + "\n")
                    }

                    append(pickUpPoint.manager.userName + " ")

                    withStyle(
                        style = SpanStyle(
                            color = Color.DarkGray
                        )
                    ) {
                        append("(MG-" + pickUpPoint.manager.userId + ")")
                    }
                },
                fontSize = 17.sp,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(id = R.string.income),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )

                Text(
                    text = "₽${pickUpPoint.income}",
                    fontSize = 23.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}