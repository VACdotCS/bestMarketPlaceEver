package com.kire.market_place_android.data.mapper.admin

import com.kire.market_place_android.data.remote.dto.response.admin.PickUpPointResponse
import com.kire.market_place_android.domain.model.pick_up_point.PickUpPointDomain

fun PickUpPointResponse.toDomain() = PickUpPointDomain(
    id = this.id,
    address = this.address,
    managerId = this.managerId,
    managerName = this.managerName,
    income = this.income
)