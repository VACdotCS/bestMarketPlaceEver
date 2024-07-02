package com.kire.market_place_android.presentation.mapper.order

import com.kire.market_place_android.domain.model.product.ImageDomain
import com.kire.market_place_android.presentation.model.product.Image

fun Image.toDomain() =
    ImageDomain(
        id = this.id,
        alt = this.alt
    )