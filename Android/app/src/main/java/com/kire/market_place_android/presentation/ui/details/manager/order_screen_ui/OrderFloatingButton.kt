package com.kire.market_place_android.presentation.ui.details.manager.order_screen_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.test.R

/**
 * Плавающая кнопка с предложением выдать заказ
 *
 * @param amountToGive количество товара, которое нужно выдать
 * @param amountToReturn количество товара, которое нужно вернуть
 * @param totalSum сумма заказа
 * @param onClick обработчик нажатия
 *
 * @author Michael Gontarev (KiREHwYE)*/
@Composable
fun OrderFloatingButton(
    modifier: Modifier = Modifier,
    amountToGive: Int = 0,
    amountToReturn: Int = 0,
    totalSum: Double = 0.0,
    onClick: () -> Unit
) {

    ExtendedFloatingActionButton(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        text = {
            Column(
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 8.dp,
                        top = 8.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = Strings.GIVE_ORDER_SUGGESTION,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = Strings.RUB + totalSum.toString()
                    + " "
                    + "("
                    + amountToGive.toString()
                    + " "
                    + Strings.GIVINGS
                    + ", "
                    + amountToReturn.toString()
                    + " "
                    + Strings.RETURNINGS
                    + ")",
                    fontWeight = FontWeight.W300,
                    fontSize = 13.sp,
                    color = Color.White
                )
            }
        },
        icon = {  },
        containerColor = ExtendedTheme.colors.redAccent,
        onClick = { onClick() }
    )
}