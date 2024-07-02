package com.kire.market_place_android.data.mapper.product

import com.kire.market_place_android.data.remote.dto.response.product.ProductResponse

fun List<ProductResponse>.toDomain() = this.map { productResponse ->
    productResponse.toDomain()
}