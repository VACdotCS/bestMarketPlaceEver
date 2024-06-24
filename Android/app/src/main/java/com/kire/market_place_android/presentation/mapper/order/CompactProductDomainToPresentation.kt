package com.kire.market_place_android.presentation.mapper.order

import com.kire.market_place_android.domain.model.product.CompactProductDomain
import com.kire.market_place_android.presentation.model.product.CompactProduct
import com.kire.market_place_android.presentation.mapper.product.toPresentation

fun CompactProductDomain.toDomain() = CompactProduct(
    id = this.id,
    title = this.title,
    image = this.image.toPresentation(),

)