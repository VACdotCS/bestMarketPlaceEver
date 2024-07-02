package com.kire.market_place_android.presentation.model.order

import com.kire.market_place_android.presentation.model.product.CompactProduct
import java.math.BigDecimal
import java.time.LocalDate

data class OrderedProduct(
    val product: CompactProduct = CompactProduct(),
    val quantity: Int = 0,
    val price: BigDecimal = 0.0.toBigDecimal(),
    val deliveryDays: Int = 0,
    val deliveryStatus: String = "",
    val completionDate: LocalDate? = null
)