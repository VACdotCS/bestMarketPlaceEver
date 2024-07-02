package com.kire.market_place_android.presentation.mapper.product

import com.kire.market_place_android.presentation.model.product.CompactProduct
import com.kire.market_place_android.presentation.model.product.Product

fun Product.toCompactProduct() = CompactProduct(
    id = this.id,
    title = this.title,
    image = this.image,
    unit = this.unit
)