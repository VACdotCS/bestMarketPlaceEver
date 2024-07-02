package com.kire.market_place_android.data.mapper.product

import com.kire.market_place_android.data.model.product.ProductCategoryEntity
import com.kire.market_place_android.domain.model.product.ProductCategoryDomain

/**
 * By Michael Gontarev (KiREHwYE)*/
fun ProductCategoryEntity.toDomain() =
    ProductCategoryDomain.valueOf(this.categoryName)