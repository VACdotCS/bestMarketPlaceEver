package com.kire.market_place_android.domain.model.delivery

import com.kire.market_place_android.domain.model.product.ProductDomain

/**
 * By Aleksey Timko (de4ltt)*/
data class DeliveryDomain(
    val deliveryId: Int,
    val productItem: ProductDomain,
    val quantity: Int,
    val deliveryStatus: DeliveryStatusDomain,
    val deliveryCode: Int,
    val deliveryStart: String,
    val deliveryEstimate: String,
    val deliveryEnd: String?
)
