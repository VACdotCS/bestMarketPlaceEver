package com.kire.market_place_android.presentation.screen.admin_panel_users_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.model.admin.AdminUserInfo
import com.kire.market_place_android.presentation.model.user.User
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme

/**
 * Плитка с информацией о пользователе
 *
 * @param user пользователь
 *
 * @author Michael Gontarev (KiREHwYE)
 * @author Aleksey Timko (de4ltt)*/
@Composable
fun AdminUserBar(
    user: AdminUserInfo
) {

    user.apply {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    ExtendedTheme.colors.profileBar,
                    RoundedCornerShape(12.dp)
                )
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    fontSize = 23.sp,
                    text = username
                )

                Text(
                    fontSize = 23.sp,
                    color = Color.DarkGray,
                    text = "${userDiscount ?: 0}${Strings.PERCENT}"
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = user.role.name,
                    color = Color.DarkGray,
                    fontSize = 13.sp,
                    lineHeight = 14.sp
                )

                Text(
                    fontSize = 23.sp,
                    color = Color.DarkGray,
                    text = "${Strings.RUB}${amountSpent ?: 0}"
                )
            }
        }
    }
}