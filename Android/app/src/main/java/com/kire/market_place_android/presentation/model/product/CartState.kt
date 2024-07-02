package com.kire.market_place_android.presentation.model.product

data class CartState(
    val pickUpPointId: Int = 0,
    val toBuy: List<Product> = emptyList(),
    val allProductsInCart: List<Product> = emptyList(),
    val product: Product = Product()
)