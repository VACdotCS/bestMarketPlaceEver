package com.kire.market_place_android.data.mapper.product

import com.kire.market_place_android.data.remote.dto.request.product.ProductRequest
import com.kire.market_place_android.domain.model.product.ProductDomain

fun ProductDomain.toRequest(image: ByteArray) = ProductRequest(
    title = title,
    image = image,
    description = description,
    price = price,
    discountPrice = discountPrice,
    quantityOfAvailable = quantityAvailable,
    unit = unit,
    deliveryDays = deliveryDays,
    category = this.category
)