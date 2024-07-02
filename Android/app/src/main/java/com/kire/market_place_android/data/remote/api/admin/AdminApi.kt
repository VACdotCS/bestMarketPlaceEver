package com.kire.market_place_android.data.remote.api.admin

import com.kire.market_place_android.data.remote.dto.HttpRoutes
import com.kire.market_place_android.data.remote.dto.request.admin.PickUpPointRequest
import com.kire.market_place_android.data.remote.dto.response.admin.PickUpPointResponse

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url

import io.ktor.http.ContentType
import io.ktor.http.contentType

import javax.inject.Inject

class AdminApi @Inject constructor(
    private val client: HttpClient
): IAdminApi {

    // receive a list of all existing KubMarket's pick-up points
    override suspend fun getAllPickUpPoints(): List<PickUpPointResponse> {
        return client.get {
            url(HttpRoutes.GET_ALL_PICK_UP_POINTS)
            contentType(ContentType.Application.Json)
        }.body<List<PickUpPointResponse>>()
    }

    // create new pick-up point
    override suspend fun createPickUpPoint(pickUpPointRequest: PickUpPointRequest) {
        client.post {
            url(HttpRoutes.CREATE_PICK_UP_POINT)
            contentType(ContentType.Application.Json)
            setBody(pickUpPointRequest)
        }
    }

    // update existing pick-up point
    override suspend fun updatePickUpPoint(id: Int, pickUpPointRequest: PickUpPointRequest) {
        client.patch {
            url(HttpRoutes.UPDATE_PICK_UP_POINT_BY_ID + id.toString())
            contentType(ContentType.Application.Json)
            setBody(pickUpPointRequest)
        }
    }

    // delete existing pick-up point
    override suspend fun deletePickUpPoint(id: Int) {
        client.delete {
            url(HttpRoutes.DELETE_PICK_UP_POINT_BY_ID + id.toString())
            contentType(ContentType.Application.Json)
        }
    }
}