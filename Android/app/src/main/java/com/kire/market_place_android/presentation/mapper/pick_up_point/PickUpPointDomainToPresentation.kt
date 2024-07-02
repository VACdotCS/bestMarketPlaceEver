package com.kire.market_place_android.presentation.mapper.pick_up_point

import com.kire.market_place_android.domain.model.pick_up_point.PickUpPointDomain
import com.kire.market_place_android.presentation.model.pick_up_point.PickUpPoint

fun PickUpPointDomain.toPresentation() = PickUpPoint(
    id = this.id,
    address = this.address,
    managerId = this.managerId,
    managerName = this.managerName,
    income = this.income
)