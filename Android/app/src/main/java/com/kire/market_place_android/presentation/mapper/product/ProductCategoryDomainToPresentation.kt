package com.kire.market_place_android.presentation.mapper.product

import com.kire.market_place_android.domain.model.product.ProductCategoryDomain
import com.kire.market_place_android.presentation.model.product.ProductCategory

/**
 * By Aleksey Timko (de4ltt)*/
fun ProductCategoryDomain.toPresentation() = ProductCategory.valueOf(this.categoryName)

/**
 * By Aleksey Timko (de4ltt)*/
fun Set<ProductCategoryDomain>.asSetProductCategory() =
    this.map { it.toPresentation() }.toSet()