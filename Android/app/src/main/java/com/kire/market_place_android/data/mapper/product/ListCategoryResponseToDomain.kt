package com.kire.market_place_android.data.mapper.product

import com.kire.market_place_android.data.remote.dto.response.product.CategoryResponse

fun List<CategoryResponse>.toDomain() = this.map {  categoryResponse ->
    categoryResponse.toDomain()
}