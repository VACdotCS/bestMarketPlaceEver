package com.kire.market_place_android.presentation.mapper.product

import com.kire.market_place_android.domain.model.product.ProductDomain

fun List<ProductDomain>.toPresentation() = this.map { productDomain ->
    productDomain.toPresentation()
}