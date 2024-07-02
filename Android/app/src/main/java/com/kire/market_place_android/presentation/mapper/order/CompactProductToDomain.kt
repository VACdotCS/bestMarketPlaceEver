package com.kire.market_place_android.presentation.mapper.order

import com.kire.market_place_android.data.mapper.order.toResponse
import com.kire.market_place_android.data.remote.dto.response.product.CompactProductResponse
import com.kire.market_place_android.domain.model.product.CompactProductDomain
import com.kire.market_place_android.presentation.model.product.CompactProduct

fun CompactProduct.toDomain() =
    CompactProductDomain(
        id = this.id,
        title = this.title,
        image = this.image,
        unit = this.unit
    )