package com.kire.market_place_android.presentation.ui.details.common.shopping_screen_ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.model.pick_up_point.PickUpPoint
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.market_place_android.presentation.util.modifier.bounceClick
import com.kire.test.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PickUpPointCard(
    chosenPickUpPointId: Int,
    pickUpPoint: PickUpPoint,
    onClick: (PickUpPoint) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .bounceClick {
                onClick(pickUpPoint)
            }
            .background(
                ExtendedTheme.colors.profileBar,
                RoundedCornerShape(12.dp)
            )
            .padding(
                horizontal = 20.dp,
                vertical = 15.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = R.drawable.location),
            contentDescription = "",
            tint =
                if (chosenPickUpPointId == pickUpPoint.id)
                    ExtendedTheme.colors.redAccent
                else Color.Black
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    ) { append(Strings.PICK_UP_POINT_ID) }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 18.sp
                        )
                    ) { append(pickUpPoint.id.toString()) }
                },
                modifier = Modifier
                    .basicMarquee(
                        animationMode = MarqueeAnimationMode.Immediately,
                        delayMillis = 0
                    )
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    ) { append(Strings.PICK_UP_POINT_ADDRESS) }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 18.sp
                        )
                    ) { append(pickUpPoint.address) }
                },
                modifier = Modifier
                    .basicMarquee(
                        animationMode = MarqueeAnimationMode.Immediately,
                        delayMillis = 0
                    )
            )
        }
    }
}