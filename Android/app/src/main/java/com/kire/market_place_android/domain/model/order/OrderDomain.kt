package com.kire.market_place_android.domain.model.order

import java.time.LocalDate

data class OrderDomain(
    val orderId: Int = 0,
    val products: List<OrderedProductDomain> = emptyList(),
    val datetime: LocalDate? = LocalDate.parse("2018-12-12"),
    val completed: Boolean = false
)