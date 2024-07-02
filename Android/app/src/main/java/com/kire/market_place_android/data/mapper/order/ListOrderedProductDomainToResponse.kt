package com.kire.market_place_android.data.mapper.order

import com.kire.market_place_android.domain.model.order.OrderedProductDomain

fun List<OrderedProductDomain>.toResponse() = this.map { orderedProductDomain ->
    orderedProductDomain.toResponse()
}