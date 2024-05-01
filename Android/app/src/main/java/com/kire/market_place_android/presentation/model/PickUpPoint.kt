package com.kire.market_place_android.presentation.model

data class PickUpPoint(
    val id: Int,
    val adress: String,
    val manager: User,
    val income: Double
)