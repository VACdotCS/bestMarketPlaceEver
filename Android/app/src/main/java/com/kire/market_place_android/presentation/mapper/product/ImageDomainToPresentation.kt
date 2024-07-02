package com.kire.market_place_android.presentation.mapper.product

import com.kire.market_place_android.domain.model.product.ImageDomain
import com.kire.market_place_android.presentation.model.product.Image

fun ImageDomain.toPresentation() = Image(
    id = this.id,
    alt = this.alt
)