package com.kire.market_place_android.presentation.ui.details.common.cross_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kire.market_place_android.presentation.constant.BottomBarHeight
import com.kire.market_place_android.presentation.constant.Strings

import com.kire.test.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * Контейнер-список с топ баром и плавающей кнопкой
 *
 * @param listSize размер списка
 * @param paddingValues отступы от краев экрана
 * @param topBar верхний бар
 * @param floatingButton плавающая кнопка
 * @param content основной контент экрана
 *
 * @author Michael Gontarev (KiREHwYE)
 */
@Composable
fun ListWithTopAndFab(
    listSize: Int = 0,
    paddingValues: PaddingValues = PaddingValues(start = 28.dp, end = 28.dp),
    topBar: @Composable () -> Unit = {},
    floatingButton: @Composable (() -> Unit) -> Unit = {},
    content: @Composable (Modifier) -> Unit = {},
) {

    /** область выполнения корутин */
    val coroutineScope = rememberCoroutineScope()

    /** измерения контейнера в пикселях */
    val localDensity = LocalDensity.current

    /** высота верхнего бара в пикселях*/
    val topBarHeightPx = remember { mutableStateOf(0f) }

    /** свдиг верхнего бара по высоте */
    val topBarOffsetHeightPx = remember { mutableStateOf(0f) }

    /** высота плавающей кнопки в пикселях */
    val fabHeightPx = remember { mutableStateOf(0f) }

    /** свдиг нижнего бара по высоте */
    val fabOffsetHeightPx = remember { mutableStateOf(0f) }

    /** отступ нижней системной панели навигации */
    val bottomInsetPaddingPx = with(localDensity) {
        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding().toPx()
    }

    /** динамический отступ для списка */
    val spaceHeight = remember {
        derivedStateOf {
            (topBarHeightPx.value + topBarOffsetHeightPx.value) / localDensity.density
        }
    }

    /** функция для сдвига бара навигации за нижнюю область экрана */
    fun shiftButtonDown() {
        var value = -fabHeightPx.value - bottomInsetPaddingPx

        coroutineScope.launch {
            while (value < 0) {
                fabOffsetHeightPx.value -= 1
                value += 1f
                delay(1)
            }
        }
    }

    /** слушатель скролла экрана */
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y

                val newBottomBarOffset = fabOffsetHeightPx.value + delta
                fabOffsetHeightPx.value =
                    newBottomBarOffset.coerceIn(
                        minimumValue = -fabHeightPx.value - bottomInsetPaddingPx,
                        maximumValue = 0f
                    )

                var consumed = Offset.Zero

                val newTopBarOffset = (topBarOffsetHeightPx.value + delta).coerceIn(
                    minimumValue = -topBarHeightPx.value,
                    maximumValue = 0f
                )
                consumed = Offset(0f, newTopBarOffset - topBarOffsetHeightPx.value)
                topBarOffsetHeightPx.value = newTopBarOffset

                return consumed
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // Основной контент
    //////////////////////////////////////////////////////////////////////////////////////////
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(paddingValues)
            .nestedScroll(nestedScrollConnection)
    ) {
        when (listSize) {
            0 -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = spaceHeight.value.dp),
                    contentAlignment = Alignment.Center,
                    content = {
                        Text(
                            text = Strings.NOTHING_WAS_FOUND,
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                    }
                )
            }
            else -> {
                content(
                    Modifier
                        .padding(top = spaceHeight.value.dp)
                        .fillMaxSize()
                )
            }
        }

        ///////////////////////////////////////////////////////////////////////////////////////////
        // Верхний бар
        //////////////////////////////////////////////////////////////////////////////////////////
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(color = Color.Transparent)
                .onGloballyPositioned {
                    topBarHeightPx.value = it.size.height.toFloat()
                }
                .align(alignment = Alignment.TopCenter)
                .offset { IntOffset(x = 0, y = topBarOffsetHeightPx.value.roundToInt()) },
            contentAlignment = Alignment.Center
        ) {
            topBar()
        }

        ///////////////////////////////////////////////////////////////////////////////////////////
        // Плавающая кнопка
        //////////////////////////////////////////////////////////////////////////////////////////
        Box(
            modifier = Modifier
                .navigationBarsPadding()
                .wrapContentSize()
                .background(color = Color.Transparent)
                .padding(28.dp)
                .onGloballyPositioned {
                    fabHeightPx.value = it.size.height.toFloat() + with(localDensity) { 28.dp.toPx() }
                }
                .align(alignment = Alignment.BottomCenter)
                .offset { IntOffset(x = 0, y = -fabOffsetHeightPx.value.roundToInt()) },
            contentAlignment = Alignment.Center
        ) {
            floatingButton(::shiftButtonDown)
        }
    }
}