package com.kire.market_place_android.data.mapper.order

import com.kire.market_place_android.data.remote.dto.response.order.OrderResponse
import com.kire.market_place_android.domain.model.order.OrderDomain

fun OrderResponse.toDomain() = OrderDomain(
    orderId = this.orderId,
    products = this.products.toDomain(),
    datetime = this.datetime,
    completed = this.completed
)