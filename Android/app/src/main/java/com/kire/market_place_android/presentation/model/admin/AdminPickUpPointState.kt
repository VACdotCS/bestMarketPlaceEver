package com.kire.market_place_android.presentation.model.admin

data class AdminPickUpPointState(
    val onDismissRequest: Boolean = false,
    val isCreateBottomBarExpanded: Boolean = false,
    val isUpdateBottomBarExpanded: Boolean = false,
    val pickUpPointToUpdateId: String = "",
    val bottomBarAddress: String = "",
    val bottomBarManagerId: String = ""
)
