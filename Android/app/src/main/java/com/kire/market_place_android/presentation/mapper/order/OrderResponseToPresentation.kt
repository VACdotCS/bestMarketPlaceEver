package com.kire.market_place_android.presentation.mapper.order

import com.kire.market_place_android.domain.model.order.OrderDomain
import com.kire.market_place_android.presentation.model.order.Order

fun OrderDomain.toPresentation() = Order(
    orderId = this.orderId,
    products = this.products.toPresentation(),
    datetime = this.datetime,
    completed = this.completed
)