package com.kire.market_place_android.domain.model.order

import com.kire.market_place_android.domain.model.product.CompactProductDomain

import java.math.BigDecimal
import java.time.LocalDate

data class OrderedProductDomain(
    val product: CompactProductDomain = CompactProductDomain(),
    val quantity: Int = 0,
    val price: BigDecimal = 0.0.toBigDecimal(),
    val deliveryDays: Int = 0,
    val deliveryStatus: String = "",
    val completionDate: LocalDate? = null
)