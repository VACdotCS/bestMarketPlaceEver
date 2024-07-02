package com.kire.market_place_android.data.remote.api.order

import com.kire.market_place_android.data.remote.dto.request.order.ConfirmOrderRequest
import com.kire.market_place_android.data.remote.dto.request.order.OrderRequest
import com.kire.market_place_android.data.remote.dto.response.order.OrderResponse

interface IOrderApi {

    suspend fun getOrdersByUser(): List<OrderResponse>
    suspend fun createOrder(orderRequest: OrderRequest)
}