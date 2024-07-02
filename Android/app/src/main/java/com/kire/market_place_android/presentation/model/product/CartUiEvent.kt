package com.kire.market_place_android.presentation.model.product

sealed class CartUiEvent {

    data class addToCart(val product: Product): CartUiEvent()
    data class productSelect(val product: Product): CartUiEvent()
    data class chooseQuantity(val chosenQuantity: Int, val productId: Int): CartUiEvent()
    data class changeChosenProduct(val chosenProduct: Product): CartUiEvent()
    data class deleteFromCart(val productId: Int): CartUiEvent()
}