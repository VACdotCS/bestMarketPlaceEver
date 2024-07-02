package com.kire.market_place_android.data.remote.api.manager

import com.kire.market_place_android.data.remote.api.order.IOrderApi
import com.kire.market_place_android.data.remote.dto.HttpRoutes
import com.kire.market_place_android.data.remote.dto.request.order.ConfirmOrderRequest
import com.kire.market_place_android.data.remote.dto.request.order.OrderRequest
import com.kire.market_place_android.data.remote.dto.response.admin.PickUpPointResponse
import com.kire.market_place_android.data.remote.dto.response.order.OrderResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class ManagerApi @Inject constructor(
    private val client: HttpClient
) : IManagerApi {

    // get all products by order's id
    override suspend fun getOrderedProductsByOrderId(id: Int): OrderResponse {
        return client.get {
            url(HttpRoutes.GET_ORDERED_PRODUCTS_BY_ORDER_ID + id.toString())
            contentType(ContentType.Application.Json)
        }.body<OrderResponse>()
    }

    // confirm order
    override suspend fun confirmOrder(id: Int, confirmOrderRequest: ConfirmOrderRequest) {
        client.put {
            url(HttpRoutes.CONFIRM_ORDER_BY_ID + id.toString())
            contentType(ContentType.Application.Json)
            setBody(confirmOrderRequest)
        }
    }

    // get pick up point by manager's id
    override suspend fun getPickUpPointByManagerId(id: Int): PickUpPointResponse {
        return client.get {
            url(HttpRoutes.GET_PICK_UP_POINT_BY_MANAGER_ID + id.toString())
            contentType(ContentType.Application.Json)
        }.body<PickUpPointResponse>()
    }
}