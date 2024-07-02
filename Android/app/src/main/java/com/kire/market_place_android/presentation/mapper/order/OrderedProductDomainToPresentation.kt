package com.kire.market_place_android.presentation.mapper.order

import com.kire.market_place_android.domain.model.order.OrderedProductDomain
import com.kire.market_place_android.presentation.model.order.OrderedProduct

fun OrderedProductDomain.toPresentation() = OrderedProduct(
    product = this.product.toPresentation(),
    quantity = this.quantity,
    price = this.price,
    deliveryDays = this.deliveryDays,
    deliveryStatus = this.deliveryStatus,
    completionDate = this.completionDate
)