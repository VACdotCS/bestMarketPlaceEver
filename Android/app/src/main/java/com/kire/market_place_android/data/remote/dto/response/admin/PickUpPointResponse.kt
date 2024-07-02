package com.kire.market_place_android.data.remote.dto.response.admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PickUpPointResponse(
    val id: Int = 0,
    val address: String = "",
    @SerialName("manager_id")
    val managerId: Int = 0,
    @SerialName("manager_name")
    val managerName: String = "",
    val income: Double = 0.0
)
