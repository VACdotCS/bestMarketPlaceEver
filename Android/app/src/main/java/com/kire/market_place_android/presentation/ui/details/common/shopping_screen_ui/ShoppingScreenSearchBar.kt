package com.kire.market_place_android.presentation.ui.details.common.shopping_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.constant.Strings

import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import com.kire.market_place_android.presentation.util.modifier.bounceClick

import com.kire.test.R

/**
 * Поисковая строка главного экрана
 *
 * @param curSearchRequest текущий поисковый запрос
 * @param showFilter показать/скрыть бар с фильтрами
 *
 * @author Aleksey Timko (de4ltt)
 * @author Michael Gontarev (KiREHwYE)*/
@Composable
fun ShoppingScreenSearchBar(
    changeSearchRequest: (String) -> Unit,
    curSearchRequest: String,
    showFilter: (Boolean) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){

        BasicTextField(
            value = curSearchRequest,
            onValueChange = { changeSearchRequest(it) },
            textStyle = LocalTextStyle.current.copy(
                color = if (curSearchRequest.isEmpty()) Color.DarkGray else Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
            ),
            modifier = Modifier
                .height(56.dp)
                .weight(1f)
                .shadow(
                    elevation = 12.dp,
                    spotColor = ExtendedTheme.colors.black10,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(Color.White)
                .padding(16.dp),
            decorationBox = { innerTextField ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.magn_glass),
                        contentDescription = "Magn_Glass",
                        tint = Color.DarkGray,
                        modifier = Modifier
                            .size(24.dp)
                    )

                    Box {
                        if (curSearchRequest.isEmpty())
                            Text(
                                text = Strings.SEARCH_ITEMS,
                                fontWeight = FontWeight.W400,
                                color = if (curSearchRequest == null) Color.DarkGray else Color.Black,
                                fontSize = 14.sp
                            )
                        innerTextField()
                    }
                }
            }
        )

        Box(
            modifier = Modifier
                .padding(start = 10.dp)
                .size(56.dp)
                .bounceClick {
                    showFilter(true)
                }
                .shadow(
                    elevation = 12.dp,
                    spotColor = ExtendedTheme.colors.black10,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(
                    color = ExtendedTheme.colors.redAccent,
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.filter),
                contentDescription = "Filter",
                tint = Color.White
            )
        }
    }
}