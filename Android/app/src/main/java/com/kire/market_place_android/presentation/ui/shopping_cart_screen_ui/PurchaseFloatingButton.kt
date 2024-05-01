package com.kire.market_place_android.presentation.ui.shopping_cart_screen_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.test.R

@Composable
fun PurchaseFloatingActionButton(
    amount: Int = 0,
    totalSum: Double = 0.0,
    onClick: () -> Unit
) {

    ExtendedFloatingActionButton(
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
                    text = stringResource(id = R.string.checkout_suggestion),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = amount.toString()
                            + " "
                            + stringResource(id = R.string.unit)
                            + ", "
                            + stringResource(id = R.string.rub)
                            + totalSum.toString(),
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