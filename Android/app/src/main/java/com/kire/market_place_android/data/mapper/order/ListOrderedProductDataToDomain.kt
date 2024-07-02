package com.kire.market_place_android.data.mapper.order

import com.kire.market_place_android.data.remote.dto.response.order.OrderedProductResponse

fun List<OrderedProductResponse>.toDomain() = this.map { orderedProductResponse ->
    orderedProductResponse.toDomain()
}