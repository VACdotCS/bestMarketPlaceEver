package com.kire.market_place_android.presentation.ui.details.common.item_add_to_cart_menu_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kire.market_place_android.presentation.util.modifier.bounceClick
import com.kire.test.R

/**
 * Верхний бар экрана ItemAddToCart
 *
 * @param onArrowBackClick Обработчик нажатия кнопки "Назад"
 * @param rightButton Функция для отображения кнопки "Загрузить изображение"
 *
 * @author Michael Gontarev (KiREHwYE)*/
@Composable
fun ItemAddToCartEditTopControls(
    onArrowBackClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.TopStart,
        content = {
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .bounceClick {
                        onArrowBackClick()
                    }
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(27.dp),
                    tint = Color.Black
                )
            }
        }
    )
}