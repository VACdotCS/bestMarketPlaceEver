package com.kire.market_place_android.presentation.model.pick_up_point

/**
 * By Aleksey Timko (de4ltt)*/
data class PickUpPoint(
    val id: Int = 0,
    val address: String = "",
    val managerId: Int = 0,
    val managerName: String = "",
    val income: Double = 0.0
)