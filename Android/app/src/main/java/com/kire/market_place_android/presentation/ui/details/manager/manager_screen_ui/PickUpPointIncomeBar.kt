package com.kire.market_place_android.presentation.ui.details.manager.manager_screen_ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.constant.Strings

import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme

import com.kire.test.R

/**
 * Плитка с информацией о доходе пункта выдачи
 *
 * @param income Доход пункта выдачи
 * @param roundedCornerShape Радиус закругления углов
 * @param paddingValues Отступы от краев плитки
 *
 * @author Michael Gontarev (KiREHwYE)*/
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PickUpPointIncomeBar(
    income: Double = 0.0,
    roundedCornerShape: RoundedCornerShape =
        RoundedCornerShape(
            topStart = 18.dp,
            topEnd = 18.dp,
            bottomEnd = 18.dp
        ),
    paddingValues: PaddingValues = PaddingValues(20.dp)
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(roundedCornerShape)
            .background(ExtendedTheme.colors.profileBar)
            .padding(paddingValues),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = Strings.PICK_UP_POINT_INCOME_TITLE,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(Strings.RUB)
                }
                withStyle(
                    style = SpanStyle(
                        color = ExtendedTheme.colors.redAccent,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(income.toString())
                }
            },
            modifier = Modifier
                .basicMarquee(
                    animationMode = MarqueeAnimationMode.Immediately,
                    delayMillis = 0
                )
        )
    }
}