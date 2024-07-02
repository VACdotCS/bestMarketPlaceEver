package com.kire.market_place_android.domain.model.product

import java.math.BigDecimal

/**
 * By Aleksey Timko (de4ltt)*/
data class ProductDomain(
    val id: Int = 0,
    val title: String = "",
    val image: String = "",
    val description: String = "",
    val price: BigDecimal = 0.0.toBigDecimal(),
    val discountPrice: BigDecimal = 0.0.toBigDecimal(),
    val quantityAvailable: Int = 0,
    val unit: String = "",
    val deliveryDays: Int = 0,
    val category: String = ""
)