package com.kire.market_place_android.presentation.model.admin

sealed class AdminPickUpPointUiEvent {

    data class ChangeOnDismissRequest(val value: Boolean): AdminPickUpPointUiEvent()
    data class DeletePickUpPoint(val id: Int): AdminPickUpPointUiEvent()
    data class CreatePickUpPoint(val managerId: Int, val address: String): AdminPickUpPointUiEvent()

    data class ChangeIsCreateBottomBarExpanded(val value: Boolean): AdminPickUpPointUiEvent()
    data class ChangeIsUpdateBottomBarExpanded(val value: Boolean, val pickUpPointToUpdateId: String, val address: String, val managerId: String): AdminPickUpPointUiEvent()

    data class bottomBarAddressChanged(val value: String): AdminPickUpPointUiEvent()
    data class bottomBarManagerIdChanged(val value: String): AdminPickUpPointUiEvent()
    data class UpdatePickUpPoint(val pickUpPointToUpdateId: Int, val managerId: Int, val address: String): AdminPickUpPointUiEvent()
}
