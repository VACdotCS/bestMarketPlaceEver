package com.kire.market_place_android.data.mapper.product

import com.kire.market_place_android.data.remote.dto.request.product.OrderedProductRequest
import com.kire.market_place_android.domain.model.order.OrderedProductDomain

fun OrderedProductDomain.toRequest() = OrderedProductRequest(
    productId = this.product.id,
    countProduct = this.quantity
)