package com.kire.market_place_android.data.remote.api.manager

import com.kire.market_place_android.data.remote.dto.request.order.ConfirmOrderRequest
import com.kire.market_place_android.data.remote.dto.response.admin.PickUpPointResponse
import com.kire.market_place_android.data.remote.dto.response.order.OrderResponse

interface IManagerApi {
    suspend fun getOrderedProductsByOrderId(id: Int): OrderResponse
    suspend fun confirmOrder(id: Int, confirmOrderRequest: ConfirmOrderRequest)
    suspend fun getPickUpPointByManagerId(id: Int): PickUpPointResponse
}