package com.kire.market_place_android.domain.model.delivery

/**
 * By Aleksey Timko (de4ltt)*/
enum class DeliveryStatusDomain(
    val status: String
) {

    DENIED("denied"),
    READY("ready"),
    ON_THE_WAY("on the way"),
    DELIVERED("delivered")
}