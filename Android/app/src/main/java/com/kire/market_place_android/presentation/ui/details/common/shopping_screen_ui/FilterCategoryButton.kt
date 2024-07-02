package com.kire.market_place_android.presentation.ui.details.common.shopping_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.market_place_android.presentation.util.modifier.bounceClick

/**
 * Кнопка выбора категории
 *
 * @param category категория
 * @param onClick обработчик нажатия
 * @param isChecked флаг выбора
 *
 * @author Aleksey Timko (de4ltt)*/
@Composable
fun FilterCategoryButton(
    category: String,
    onClick: (String) -> Unit,
    isChecked: Boolean
) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .bounceClick {
                onClick(category)
            }
            .background(
                color = if (isChecked) ExtendedTheme.colors.redAccentSoft else ExtendedTheme.colors.profileBar,
                shape = RoundedCornerShape(5.dp)
            ),
        contentAlignment = Alignment.Center,
        content = {
            Text(
                modifier = Modifier.padding(8.dp),
                text = category
            )
        }
    )
}