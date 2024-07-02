package com.kire.market_place_android.data.mapper.order

import com.kire.market_place_android.data.remote.dto.response.product.CompactProductResponse
import com.kire.market_place_android.domain.model.product.CompactProductDomain

fun CompactProductDomain.toResponse() =
    CompactProductResponse(
        id = this.id,
        title = this.title,
        image = this.image,
        unit = this.unit
    )