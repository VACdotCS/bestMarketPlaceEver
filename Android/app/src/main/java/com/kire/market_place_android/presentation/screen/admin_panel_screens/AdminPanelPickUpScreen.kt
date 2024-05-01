package com.kire.market_place_android.presentation.screen.admin_panel_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kire.market_place_android.presentation.model.PickUpPoint
import com.kire.market_place_android.presentation.model.User
import com.kire.market_place_android.presentation.model.UserRole
import com.kire.market_place_android.presentation.screen.admin_panel_pick_up_screen_ui.AdminPickUpCard
import com.kire.market_place_android.presentation.screen.admin_panel_pick_up_screen_ui.PickUpPointBottomBar
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AdminPanelPickUpScreen(
    paddingValues: PaddingValues = PaddingValues(28.dp)
) {

    val sheetState = rememberModalBottomSheetState()

    var showBottomSheet: Boolean by remember {
        mutableStateOf(false)
    }

    val pointsList: List<PickUpPoint> = listOf(
        PickUpPoint(
            id = 1,
            adress = "г. Краснодар, ул. Ставропольская, 149",
            manager = User(
                userId = 1,
                userName = "Хебоб",
                userRole = UserRole.MANAGER,
                userDiscount = 0,
                userSpent = 1000.0
            ),
            income = 90000.0
        ),
        PickUpPoint(
            id = 2,
            adress = "г. Краснодар, ул. Ставропольская, 149, заберите меня на сво",
            manager = User(
                userId = 2,
                userName = "Александр",
                userRole = UserRole.MANAGER,
                userDiscount = 0,
                userSpent = 10030.0
            ),
            income = 97556.0
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(pointsList) { it ->
            AdminPickUpCard(
                pickUpPoint = it,
                onEditClick = { show ->
                    showBottomSheet = show
                }
            )
        }
    }

    if (showBottomSheet)
        PickUpPointBottomBar(
            showBottomSheet = {
                showBottomSheet = it
            },
            sheetState = sheetState
        )
}