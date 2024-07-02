package com.kire.market_place_android.data.mapper.admin

import com.kire.market_place_android.data.remote.dto.response.admin.PickUpPointResponse

fun List<PickUpPointResponse>.toDomain() = this.map { pickUpPointResponse ->
    pickUpPointResponse.toDomain()
}