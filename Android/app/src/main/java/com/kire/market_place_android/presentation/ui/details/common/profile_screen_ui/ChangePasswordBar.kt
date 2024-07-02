package com.kire.market_place_android.presentation.ui.details.common.profile_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.constant.Strings

import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.market_place_android.presentation.util.modifier.bounceClick

import com.kire.test.R

/**
 * Плитка для открытия окна смены пароля
 *
 * @param roundedCornerShape - радиус закругления углов
 * @param paddingValues - отступы от краев экрана
 * @param onClick - обработчик нажатия
 *
 * @author Michael Gontarev (KiREHwYE)*/
@Composable
fun ChangePasswordBar(
    roundedCornerShape: RoundedCornerShape =
        RoundedCornerShape(
            topStart = 18.dp,
            topEnd = 18.dp,
            bottomEnd = 18.dp
        ),
    paddingValues: PaddingValues = PaddingValues(20.dp),
    onClick: (Boolean) -> Unit = { }
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(roundedCornerShape)
            .background(ExtendedTheme.colors.profileBar)
            .bounceClick {
                onClick(true)
            }
            .padding(paddingValues),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = Strings.CHANGE_PASSWORD_SUGGESTION,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Icon(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = null,
            tint = ExtendedTheme.colors.redAccent,
            modifier = Modifier
                .size(24.dp)
        )
    }
}