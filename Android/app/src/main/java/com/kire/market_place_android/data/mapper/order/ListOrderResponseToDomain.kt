package com.kire.market_place_android.data.mapper.order

import com.kire.market_place_android.data.remote.dto.response.order.OrderResponse

fun List<OrderResponse>.toDomain() = this.map { orderResponse ->
    orderResponse.toDomain()
}