package com.kire.market_place_android.data.mapper.order

import com.kire.market_place_android.data.remote.dto.response.order.OrderedProductResponse
import com.kire.market_place_android.domain.model.order.OrderedProductDomain

fun OrderedProductDomain.toResponse() =
    OrderedProductResponse(
        quantity = this.quantity,
        product = this.product.toResponse(),
        price = this.price,
        deliveryDays = this.deliveryDays,
        deliveryStatus = this.deliveryStatus,
        completionDate = this.completionDate
    )