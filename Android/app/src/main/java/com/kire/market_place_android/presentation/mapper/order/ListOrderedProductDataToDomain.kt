package com.kire.market_place_android.presentation.mapper.order

import com.kire.market_place_android.domain.model.order.OrderedProductDomain

fun List<OrderedProductDomain>.toPresentation() = this.map { orderedProductDomain ->
    orderedProductDomain.toPresentation()
}