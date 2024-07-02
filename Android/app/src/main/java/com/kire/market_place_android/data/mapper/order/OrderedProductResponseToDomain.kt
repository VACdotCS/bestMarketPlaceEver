package com.kire.market_place_android.data.mapper.order

import com.kire.market_place_android.data.remote.dto.response.order.OrderedProductResponse
import com.kire.market_place_android.domain.model.order.OrderedProductDomain

fun OrderedProductResponse.toDomain() = OrderedProductDomain(
    product = this.product.toDomain(),
    quantity = this.quantity,
    price = this.price,
    deliveryDays = this.deliveryDays,
    deliveryStatus = this.deliveryStatus,
    completionDate = this.completionDate
)