package com.kire.market_place_android.presentation.mapper.product

import com.kire.market_place_android.presentation.model.product.Product

fun List<Product>.toListOrderedProduct() = this.map { product ->
    product.toOrderedProduct()
}