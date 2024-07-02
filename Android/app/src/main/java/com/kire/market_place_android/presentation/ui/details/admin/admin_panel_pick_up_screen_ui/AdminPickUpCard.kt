package com.kire.market_place_android.presentation.ui.details.admin.admin_panel_pick_up_screen_ui

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.model.pick_up_point.PickUpPoint
import com.kire.market_place_android.presentation.model.admin.AdminPickUpPointState
import com.kire.market_place_android.presentation.model.admin.AdminPickUpPointUiEvent
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.market_place_android.presentation.util.modifier.bounceClick
import com.kire.test.R

/**
 * Карточка пункта выдачи
 *
 * @param adminPickUpPointState состояние ui экрана админа
 * @param pickUpPoint информация о пункте выдачи
 * @param onEvent обработчик событий
 * @param modifier модификатор
 *
 * @author Michael Gontarev (KiREHwYE)
 * @author Aleksey Timko (de4ltt)*/
@Composable
fun AdminPickUpCard(
    adminPickUpPointState: AdminPickUpPointState,
    pickUpPoint: PickUpPoint,
    onEvent: (AdminPickUpPointUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    var isDeleteButtonExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    val blurDp = animateDpAsState(
        targetValue = if (isDeleteButtonExpanded) 20.dp else 0.dp,
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
    )
    val unBlurDp= animateDpAsState(
        targetValue = if (isDeleteButtonExpanded) 0.dp else 20.dp,
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
    )
    val iconSize = animateDpAsState(
        targetValue = if (isDeleteButtonExpanded) 52.dp else 0.dp,
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
    )

    LaunchedEffect(adminPickUpPointState.onDismissRequest) {
        if (adminPickUpPointState.onDismissRequest) {
            isDeleteButtonExpanded = false
            onEvent(AdminPickUpPointUiEvent.ChangeOnDismissRequest(false))
        }
    }

    pickUpPoint.apply {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .zIndex(1f)
                    .wrapContentSize()
                    .clip(CircleShape)
                    .blur(unBlurDp.value)
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bin),
                        contentDescription = null,
                        tint = ExtendedTheme.colors.redAccent,
                        modifier = Modifier
                            .size(iconSize.value)
                            .bounceClick {
                                isDeleteButtonExpanded = !isDeleteButtonExpanded
                                onEvent(AdminPickUpPointUiEvent.DeletePickUpPoint(pickUpPoint.id))
                            }
                    )
            }

            Column(
                modifier = Modifier
                    .zIndex(0f)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                if (!isDeleteButtonExpanded)
                                    onEvent(AdminPickUpPointUiEvent.ChangeOnDismissRequest(true))
                            },
                            onLongPress = {
                                isDeleteButtonExpanded = !isDeleteButtonExpanded
                            }
                        )
                    }
                    .background(
                        ExtendedTheme.colors.profileBar,
                        RoundedCornerShape(12.dp)
                    )
                    .blur(blurDp.value)
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
                                append(pickUpPoint.address)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(0.9f),
                        lineHeight = 18.sp
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.pencil),
                        contentDescription = "edit",
                        tint = ExtendedTheme.colors.redAccent,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .size(20.dp)
                            .bounceClick {
                                if (!isDeleteButtonExpanded)
                                    onEvent(AdminPickUpPointUiEvent.ChangeIsUpdateBottomBarExpanded(
                                        value = true,
                                        pickUpPointToUpdateId = pickUpPoint.id.toString(),
                                        address = pickUpPoint.address,
                                        managerId = pickUpPoint.managerId.toString()
                                    ))
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
                            append(Strings.MANAGER + "\n")
                        }

                        append(pickUpPoint.managerName)

                        withStyle(
                            style = SpanStyle(
                                color = Color.DarkGray
                            )
                        ) {
                            append("(MG-" + 0 + ")")
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
                        text = Strings.INCOME,
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(pickUpPoint.id) {
                detectTapGestures {
                    isDeleteButtonExpanded = false
                }
            }
    )
}