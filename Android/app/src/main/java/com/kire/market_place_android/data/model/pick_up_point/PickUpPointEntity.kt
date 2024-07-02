package com.kire.market_place_android.data.model.pick_up_point

import com.kire.market_place_android.data.model.user.UserEntity

/**
 * By Aleksey Timko (de4ltt)*/
data class PickUpPointEntity(
    val id: Int,
    val address: String,
    val manager: UserEntity,
    val income: Double
)