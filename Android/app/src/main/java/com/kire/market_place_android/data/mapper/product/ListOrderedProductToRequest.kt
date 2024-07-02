package com.kire.market_place_android.data.mapper.product

import com.kire.market_place_android.domain.model.order.OrderedProductDomain

fun List<OrderedProductDomain>.toRequest() = this.map { orderedProductDomain ->
    orderedProductDomain.toRequest()
}