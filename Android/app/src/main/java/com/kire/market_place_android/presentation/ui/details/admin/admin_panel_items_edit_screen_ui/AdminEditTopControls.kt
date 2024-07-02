package com.kire.market_place_android.presentation.ui.details.admin.admin_panel_items_edit_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kire.market_place_android.presentation.util.modifier.bounceClick
import com.kire.test.R

/**
 * Верхний бар экрана AdminPanelItemsEditScreen
 *
 * @param onArrowBackClick Обработчик нажатия кнопки "Назад"
 * @param onUploadButtonClick Обработчик нажатия кнопки кнопки загрузки изображения
 *
 * @author Michael Gontarev (KiREHwYE)*/
@Composable
fun AdminEditTopControls(
    onArrowBackClick: () -> Unit,
    onUploadButtonClick:  () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
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

            Box(
                modifier = Modifier
                    .size(55.dp)
                    .bounceClick {
                        onUploadButtonClick()
                    }
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.update_sign),
                    contentDescription = "update_sign",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp),
                )
            }
        }
    )
}