package com.kire.market_place_android.presentation.screen.admin_panel_users_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.model.User
import com.kire.market_place_android.presentation.model.UserRole
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme

@Composable
fun UserBar(
    user: User
) {

    user.apply {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(107.dp)
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
                    text = userName
                )

                Text(
                    fontSize = 23.sp,
                    color = Color.DarkGray,
                    text = "$userDiscount%"
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "${when (userRole) {
                        UserRole.CLIENT -> "USR"
                        UserRole.MANAGER -> "MAG"
                        UserRole.ADMIN -> "ADM"
                        UserRole.DEVELOPER -> "DEV"
                    }}-$userId\n${
                        when (userRole) {
                            UserRole.CLIENT -> "user"
                            UserRole.MANAGER -> "manager"
                            UserRole.ADMIN -> "admin"
                            UserRole.DEVELOPER -> "developer"
                        }
                    }",
                    color = Color.DarkGray,
                    fontSize = 13.sp,
                    lineHeight = 14.sp
                )

                Text(
                    fontSize = 23.sp,
                    color = Color.DarkGray,
                    text = "₽$userSpent"
                )
            }
        }
    }
}