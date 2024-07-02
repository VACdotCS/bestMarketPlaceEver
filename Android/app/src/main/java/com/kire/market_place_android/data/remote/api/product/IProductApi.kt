package com.kire.market_place_android.data.remote.api.product

import com.kire.market_place_android.data.remote.dto.request.product.ProductRequest
import com.kire.market_place_android.data.remote.dto.response.product.ProductResponse

interface IProductApi {

    suspend fun getAllProducts() : List<ProductResponse>
    suspend fun getAllAvailableCategories() : Set<String>
    suspend fun updateProductById(id:Int, product: ProductRequest)
    suspend fun addProduct(product: ProductRequest)
}