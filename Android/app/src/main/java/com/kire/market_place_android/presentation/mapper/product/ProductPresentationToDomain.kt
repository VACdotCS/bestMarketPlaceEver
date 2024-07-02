package com.kire.market_place_android.presentation.mapper.product

import com.kire.market_place_android.domain.model.product.ProductDomain
import com.kire.market_place_android.presentation.mapper.order.toDomain
import com.kire.market_place_android.presentation.model.product.Product

fun Product.toDomain() = ProductDomain(
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