package com.kire.market_place_android.presentation.model.manager

data class ManagerOrderState(
    val received: List<Int> = emptyList(),
    val returned: List<Int> = emptyList()
)