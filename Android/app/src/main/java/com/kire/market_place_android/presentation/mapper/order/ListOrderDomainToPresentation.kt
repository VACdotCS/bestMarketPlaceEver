package com.kire.market_place_android.presentation.mapper.order

import com.kire.market_place_android.domain.model.order.OrderDomain

fun List<OrderDomain>.toDomain() = this.map { orderDomain ->
    orderDomain.toDomain()
}