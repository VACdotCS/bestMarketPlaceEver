package com.kire.market_place_android.data.mapper.product

import com.kire.market_place_android.data.remote.dto.response.product.CategoryResponse
import com.kire.market_place_android.domain.model.product.CategoryDomain

fun CategoryResponse.toDomain() = CategoryDomain(
    categoryId = this.categoryId,
    title = this.title
)