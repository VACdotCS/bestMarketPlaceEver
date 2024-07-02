package com.kire.market_place_android.presentation.ui.details.common.shopping_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kire.market_place_android.presentation.constant.Strings
import com.kire.market_place_android.presentation.model.admin.AdminPickUpPointUiEvent
import com.kire.market_place_android.presentation.model.pick_up_point.PickUpPoint
import com.kire.market_place_android.presentation.ui.theme.ExtendedTheme
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickUpPointsBottomBar(
    chosenPickUpPointId: Int,
    pickUpPoints: StateFlow<List<PickUpPoint>>,
    showBottomSheet: (Boolean) -> Unit,
    onClick: (PickUpPoint) -> Unit,
    sheetState: SheetState
) {

    val pickUpPoints by pickUpPoints.collectAsStateWithLifecycle()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            showBottomSheet(false)
        },
        containerColor = Color.White,
        dragHandle = {
            Text(
                text = Strings.PICK_UP_POINTS,
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
        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            contentPadding = PaddingValues(28.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pickUpPoints, key = {it.id}) { pickUpPoint ->
                PickUpPointCard(
                    chosenPickUpPointId = chosenPickUpPointId,
                    pickUpPoint = pickUpPoint,
                    onClick = { pickUpPoint ->
                        onClick(pickUpPoint)
                        showBottomSheet(false)
                    }
                )
            }
        }
    }


}