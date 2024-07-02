package com.kire.market_place_android.presentation.ui.details.common.shopping_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet

import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import java.math.BigDecimal

/**
 * Нижний бар с фильтрами запроса
 *
 * @param allCategories список категорий
 * @param showBottomSheet показать/скрыть BottomSheet
 * @param sheetState состояние BottomSheet
 *
 * @author Michael Gontarev (KiREHwYE)
 * @author Aleksey Timko (de4ltt)*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomBar(
    curChosenCategories: List<String>,
    curChosenPriceRange: Pair<BigDecimal, BigDecimal>,
    allCategories: Set<String>,
    showBottomSheet: (Boolean) -> Unit,
    sheetState: SheetState,
    changeFilterRequest: (List<String>, Pair<BigDecimal, BigDecimal>) -> Unit
) {

    var curCategories by remember {
        mutableStateOf(curChosenCategories)
    }

    var curLowPrice: String by remember {
        mutableStateOf(
            if (curChosenPriceRange.first == 0.toBigDecimal()) ""
            else curChosenPriceRange.first.toString()
        )
    }

    var curTopPrice: String by remember {
        mutableStateOf(
            if (curChosenPriceRange.second == Double.MAX_VALUE.toBigDecimal()) ""
            else curChosenPriceRange.second.toString()
        )
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            showBottomSheet(false)
        },
        containerColor = Color.White,
        dragHandle = {
            Text(
                text = Strings.FILTER,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 28.dp)
            )
        },
        modifier = Modifier
            .wrapContentHeight()
    ) {

        Box(
            modifier = Modifier
                .wrapContentHeight()
                .padding(
                    top = 28.dp,
                    start = 36.dp,
                    end = 36.dp
                ),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(36.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .wrapContentSize(),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {

                        Text(
                            text = Strings.CATEGORIES,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(Modifier.height(8.dp))

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .wrapContentSize(),
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            items(allCategories.toList()) {
                                FilterCategoryButton(
                                    category = it,
                                    onClick = { category ->
                                        curCategories =
                                            if (curCategories.contains(category))
                                                curCategories.minus(category)
                                            else curCategories.plus(category)
                                    },
                                    isChecked = curCategories.contains(it)
                                )
                            }
                        }

                        Spacer(Modifier.height(15.dp))

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            item {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxHeight(),
                                        text = Strings.PRICE,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }

                            }

                            item {
                                BasicTextField(
                                    value = curLowPrice,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            color = ExtendedTheme.colors.profileBar,
                                            RoundedCornerShape(8.dp)
                                        ),
                                    onValueChange = {
                                        if (it.matches("\\d*".toRegex()))
                                            curLowPrice = it
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number
                                    ),
                                    textStyle = TextStyle(
                                        fontSize = 16.sp,
                                        lineHeight = 16.sp
                                    ),
                                    decorationBox = { innerTextField ->
                                        Box(
                                            modifier = Modifier
                                                .padding(12.dp)
                                                .fillMaxWidth(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            if (curLowPrice.isEmpty())
                                                Text(
                                                    text = Strings.FROM,
                                                    fontSize = 16.sp,
                                                    lineHeight = 16.sp
                                                )
                                            innerTextField()
                                        }
                                    }
                                )
                            }

                            item {
                                BasicTextField(
                                    value = curTopPrice,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            color = ExtendedTheme.colors.profileBar,
                                            RoundedCornerShape(8.dp)
                                        ),
                                    onValueChange = {
                                        if (it.matches("\\d*".toRegex()))
                                            curTopPrice = it
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number
                                    ),
                                    textStyle = TextStyle(
                                        fontSize = 16.sp,
                                        lineHeight = 16.sp
                                    ),
                                    decorationBox = { innerTextField ->
                                        Box(
                                            modifier = Modifier
                                                .padding(12.dp)
                                                .fillMaxWidth(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            if (curTopPrice.isEmpty())
                                                Text(
                                                    text = Strings.UNTIL,
                                                    fontSize = 16.sp,
                                                    lineHeight = 16.sp
                                                )
                                            innerTextField()
                                        }
                                    }
                                )
                            }
                        }
                    }

                    Button(
                        onClick = {
                            changeFilterRequest(
                                curCategories,
                                Pair(
                                    if (curLowPrice.isEmpty()) 0.toBigDecimal() else curLowPrice.toBigDecimal(),
                                    if (curTopPrice.isEmpty()) Double.MAX_VALUE.toBigDecimal() else curTopPrice.toBigDecimal()
                                )
                            )
                            showBottomSheet(false)
                        },
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ExtendedTheme.colors.redAccent
                        )
                    ) {
                        Text(
                            text = Strings.SAVE,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
}