package com.kire.market_place_android.presentation.model.product

sealed class AdminProductEvent {
    data class addProduct(val product: Product): AdminProductEvent()
    data class editProduct(val product: Product): AdminProductEvent()
}