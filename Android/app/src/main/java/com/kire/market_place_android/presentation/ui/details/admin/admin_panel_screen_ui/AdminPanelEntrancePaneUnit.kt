package com.kire.market_place_android.presentation.ui.details.admin.admin_panel_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.market_place_android.presentation.util.modifier.bounceClick
import com.kire.test.R

/**
 * Кнопка из меню админа для входа в одну из админ панелей
 *
 * @param textValue Текст кнопки
 * @param onClick Обработчик клика
 *
 * @author Aleksey Timko (de4ltt)*/
@Composable
fun AdminPanelEntrancePaneUnit(
    textValue: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .height(100.dp)
            .background(
                color = ExtendedTheme.colors.profileBar,
                shape = RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 15.dp
                )
            )
            .bounceClick {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = textValue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    painter = painterResource(id = R.drawable.long_arrow),
                    contentDescription = "long_arrow",
                    tint = ExtendedTheme.colors.redAccent,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    )
}