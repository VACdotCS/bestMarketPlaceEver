package com.kire.market_place_android.domain.model.pick_up_point

/**
 * By Aleksey Timko (de4ltt)*/
data class PickUpPointDomain(
    val id: Int = 0,
    val address: String = "",
    val managerId: Int = 0,
    val managerName: String = "",
    val income: Double = 0.0
)