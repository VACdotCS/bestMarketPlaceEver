package com.kire.market_place_android.presentation.ui.cross_screen_ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight

import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.destinations.OrderScreenDestination
import com.kire.market_place_android.presentation.destinations.ShoppingScreenDestination

import com.kire.market_place_android.presentation.navigation.util.AppDestinations
import com.kire.market_place_android.presentation.navigation.util.getDirection
import com.kire.market_place_android.presentation.navigation.util.getIconTop
import com.kire.market_place_android.presentation.navigation.util.getLabel
import com.kire.market_place_android.presentation.navigation.util.getPlusButton
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme

import com.kire.test.R
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/*
App's Top Bar
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopBar(
    destination: AppDestinations,
    curPickUpPoint: String? = "г. Краснодар, ул. Ставропольская, д. 149, эт. 1, кб. 133",
    orderCode: String? = "XXX-XXX",
    paddingValuesTop: Dp = 84.dp,
    navigator: DestinationsNavigator? = null
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .padding(top = paddingValuesTop),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            //Replace resource on ?: right with default icon
            Icon(
                painter = painterResource(id = destination.getIconTop() ?: R.drawable.shopping_top_bar),
                contentDescription = null,
                tint = ExtendedTheme.colors.redAccent,
                modifier = Modifier
                    .size(34.dp)
                    .pointerInput(Unit){
                        detectTapGestures {
                            navigator?.popBackStack(OrderScreenDestination, inclusive = true)
                        }
                    }
            )

            if (destination.getDirection() == ShoppingScreenDestination) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.pick_up_point),
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp,
                        color = Color.DarkGray
                    )

                    Text(
                        text = curPickUpPoint ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .basicMarquee(
                                animationMode = MarqueeAnimationMode.Immediately,
                                delayMillis = 0
                            )
                    )
                }
            } else
                Text(
                    text =
                        if (destination.getDirection() == OrderScreenDestination)
                            stringResource(id = destination.getLabel()) + " " + stringResource(id = R.string.number) + orderCode.toString()
                        else
                            stringResource(id = destination.getLabel()),
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .padding(start = 12.dp)
                )
        }

        destination.getPlusButton()?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = "plus_bold",
                tint = ExtendedTheme.colors.redAccent,
                modifier = Modifier
                    .size(30.dp)
            )
        }
    }
}