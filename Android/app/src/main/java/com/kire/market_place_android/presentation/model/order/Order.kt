package com.kire.market_place_android.presentation.model.order

import java.time.LocalDate

data class Order(
    val orderId: Int = 0,
    val products: List<OrderedProduct> = emptyList(),
    val datetime: LocalDate? = LocalDate.parse("2018-12-12"),
    val completed: Boolean = false
)