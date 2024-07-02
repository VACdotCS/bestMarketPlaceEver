package com.kire.market_place_android.data.remote.api.order

import com.kire.market_place_android.data.remote.dto.HttpRoutes
import com.kire.market_place_android.data.remote.dto.request.order.ConfirmOrderRequest
import com.kire.market_place_android.data.remote.dto.request.order.OrderRequest
import com.kire.market_place_android.data.remote.dto.response.order.OrderResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class OrderApi @Inject constructor(
    private val client: HttpClient
) : IOrderApi {

    // get all orders related to current user
    override suspend fun getOrdersByUser(): List<OrderResponse> {
        return client.get {
            url(HttpRoutes.GET_ORDERS_BY_USER)
            contentType(ContentType.Application.Json)
        }.body<List<OrderResponse>>()
    }

    // create order
    override suspend fun createOrder(orderRequest: OrderRequest) {
        client.post {
            url(HttpRoutes.CREATE_ORDER)
            contentType(ContentType.Application.Json)
            setBody(orderRequest)
        }
    }
}