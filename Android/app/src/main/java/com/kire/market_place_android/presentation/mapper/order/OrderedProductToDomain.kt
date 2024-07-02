package com.kire.market_place_android.presentation.mapper.order

import com.kire.market_place_android.data.mapper.order.toResponse
import com.kire.market_place_android.domain.model.order.OrderedProductDomain
import com.kire.market_place_android.presentation.model.order.OrderedProduct

fun OrderedProduct.toDomain() =
    OrderedProductDomain(
        quantity = this.quantity,
        product = this.product.toDomain(),
        price = this.price,
        deliveryDays = this.deliveryDays,
        deliveryStatus = this.deliveryStatus,
        completionDate = this.completionDate
    )