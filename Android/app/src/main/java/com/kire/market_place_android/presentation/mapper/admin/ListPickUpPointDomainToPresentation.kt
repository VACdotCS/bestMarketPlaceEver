package com.kire.market_place_android.presentation.mapper.admin

import com.kire.market_place_android.domain.model.pick_up_point.PickUpPointDomain
import com.kire.market_place_android.presentation.mapper.pick_up_point.toPresentation

fun List<PickUpPointDomain>.toPresentation() = this.map { pickUpPointDomain ->
    pickUpPointDomain.toPresentation()
}