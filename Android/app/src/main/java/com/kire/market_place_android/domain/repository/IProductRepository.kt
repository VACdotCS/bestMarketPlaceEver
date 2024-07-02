package com.kire.market_place_android.domain.repository

import com.kire.market_place_android.domain.model.IRequestResultDomain
import com.kire.market_place_android.domain.model.product.ProductDomain

/**
 * By Michael Gontarev (KiREHwYE)*/
interface IProductRepository {

    suspend fun getAllProducts(): IRequestResultDomain
    suspend fun getAllAvailableCategories():  IRequestResultDomain
    suspend fun updateProduct(id: Int, image: ByteArray, product: ProductDomain): IRequestResultDomain
    suspend fun addProduct(image: ByteArray, product: ProductDomain): IRequestResultDomain

}