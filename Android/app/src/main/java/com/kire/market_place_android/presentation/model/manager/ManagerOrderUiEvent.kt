package com.kire.market_place_android.presentation.model.manager

sealed class ManagerOrderUiEvent {

    data class productSelect(val id: Int): ManagerOrderUiEvent()
    object confirmOrder: ManagerOrderUiEvent()
}