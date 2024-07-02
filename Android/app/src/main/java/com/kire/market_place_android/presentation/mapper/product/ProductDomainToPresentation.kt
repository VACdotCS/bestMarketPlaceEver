package com.kire.market_place_android.presentation.mapper.product

import com.kire.market_place_android.domain.model.product.ProductDomain
import com.kire.market_place_android.presentation.model.product.Product

/**
 * By Aleksey Timko (de4ltt)*/
fun ProductDomain.toPresentation() = Product(
    id = this.id,
    title = this.title,
    image = this.image,
    description = this.description,
    discountPrice = this.discountPrice,
    price = this.price,
    quantityAvailable = this.quantityAvailable,
    unit = this.unit,
    deliveryDays = this.deliveryDays,
    category = this.category
)