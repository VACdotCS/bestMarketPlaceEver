package com.kire.market_place_android.data.mapper.product

import com.kire.market_place_android.data.remote.dto.response.product.ProductResponse
import com.kire.market_place_android.domain.model.product.ProductDomain

fun ProductResponse.toDomain() = ProductDomain(
    id = this.id,
    title = this.title,
    image = this.image,
    description = this.description,
    price = this.price,
    discountPrice = this.discountPrice,
    quantityAvailable = this.quantityAvailable,
    unit = this.unit,
    deliveryDays = this.deliveryDays,
    category = this.category
)