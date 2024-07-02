package com.kire.market_place_android.presentation.ui.details.common.profile_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.constant.Strings

import com.kire.market_place_android.presentation.model.user.ProfileState
import com.kire.market_place_android.presentation.model.user.UserUiEvent
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.market_place_android.presentation.util.modifier.bounceClick

import com.kire.test.R

/**
 * Карточка карты пользователя
 *
 * @param profileState состояние ui экрана профиля
 * @param onEvent обработчик событий
 * @param roundedCornerShape радиус закругления углов
 * @param paddingValues отступы от краев экрана
 *
 * @author Michael Gontarev (KiREHwYe)*/
@Composable
fun PaymentMethod(
    profileState: ProfileState,
    onEvent: (UserUiEvent) -> Unit,
    roundedCornerShape: RoundedCornerShape =
        RoundedCornerShape(
            topStart = 18.dp,
            topEnd = 18.dp,
            bottomEnd = 18.dp
        ),
    paddingValues: PaddingValues = PaddingValues(20.dp)
) {

    var isEditable by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(roundedCornerShape)
            .background(ExtendedTheme.colors.profileBar)
            .padding(paddingValues),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = Strings.PAYMENT_METHOD_TITLE,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )

            Icon(
                painter = painterResource(id = R.drawable.edit_profile_info),
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
                    .bounceClick {
                        isEditable = !isEditable.also {
                            if (it)
                                onEvent(UserUiEvent.ChangeCard)
                        }
                    },
                tint = if (isEditable) ExtendedTheme.colors.redAccent else Color.DarkGray
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            BasicTextField(
                enabled = isEditable,
                value = profileState.cardNumber,
                onValueChange = {
                    onEvent(UserUiEvent.CardNumberChanged(it))
                },
                textStyle = LocalTextStyle.current.copy(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400
                ),
                modifier = Modifier
                    .shadow(
                        elevation = 12.dp,
                        spotColor = ExtendedTheme.colors.black10,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(Color.White)
                    .padding(16.dp),
                decorationBox = { innerTextField ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.bank_card),
                            contentDescription = "Bank Card",
                            tint = Color.DarkGray,
                            modifier = Modifier
                                .size(24.dp)
                        )

                        Box {
                            if (profileState.cardNumber.isEmpty())
                                Text(
                                    text = Strings.CARD_NUMBER_HINT,
                                    fontWeight = FontWeight.W400,
                                    color = Color.DarkGray,
                                    fontSize = 14.sp
                                )
                            innerTextField()
                        }
                    }
                }
            )


            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                columns = GridCells.Fixed(count = 2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                item {
                    BasicTextField(
                        enabled = isEditable,
                        value = profileState.validity,
                        onValueChange = {
                            onEvent(UserUiEvent.ValidityChanged(it))
                        },
                        textStyle = LocalTextStyle.current.copy(
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                        ),
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .shadow(
                                elevation = 12.dp,
                                spotColor = ExtendedTheme.colors.black10,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background(Color.White)
                            .padding(16.dp),
                        decorationBox = { innerTextField ->

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.bank_card_date),
                                    contentDescription = "Bank Card Date",
                                    tint = Color.DarkGray,
                                    modifier = Modifier
                                        .size(24.dp)
                                )

                                Box {
                                    if (profileState.validity.isEmpty())
                                        Text(
                                            text = Strings.CARD_DATE_HINT,
                                            fontWeight = FontWeight.W400,
                                            color = Color.DarkGray,
                                            fontSize = 14.sp
                                        )
                                    innerTextField()
                                }
                            }
                        }
                    )
                }

                item {

                    Box(
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        BasicTextField(
                            enabled = isEditable,
                            value = profileState.CVC,
                            onValueChange = {
                                onEvent(UserUiEvent.CvcChanged(it))
                            },
                            textStyle = LocalTextStyle.current.copy(
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W400,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier
                                .shadow(
                                    elevation = 12.dp,
                                    spotColor = ExtendedTheme.colors.black10,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .background(Color.White)
                                .padding(16.dp),
                            decorationBox = { innerTextField ->

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.cvc),
                                        contentDescription = "CVC",
                                        tint = Color.DarkGray,
                                        modifier = Modifier
                                            .size(24.dp)
                                    )

                                    Box(contentAlignment = Alignment.Center) {
                                        if (profileState.CVC.isEmpty())
                                            Text(
                                                text = Strings.CARD_CVC_HINT,
                                                fontWeight = FontWeight.W400,
                                                color = Color.DarkGray,
                                                fontSize = 18.sp
                                            )
                                        innerTextField()
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}