package com.kire.market_place_android.presentation.mapper.order

import com.kire.market_place_android.presentation.model.order.OrderedProduct

fun List<OrderedProduct>.toDomain() = this.map { orderedProduct ->
    orderedProduct.toDomain()
}