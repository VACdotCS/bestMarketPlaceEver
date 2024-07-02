package com.kire.market_place_android.presentation.mapper.product

import com.kire.market_place_android.domain.model.product.ProductCategoryDomain
import com.kire.market_place_android.presentation.model.product.ProductCategory

/**
 * By Aleksey Timko (de4ltt)*/
fun ProductCategory.toPresentation() = ProductCategoryDomain.valueOf(this.categoryName)

/**
 * By Aleksey Timko (de4ltt)*/
fun Set<ProductCategory>.asSetProductCategoryDomain() =
    this.map { it.toPresentation() }.toSet()