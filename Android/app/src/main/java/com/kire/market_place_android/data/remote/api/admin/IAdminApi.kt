package com.kire.market_place_android.data.remote.api.admin

import com.kire.market_place_android.data.remote.dto.request.admin.PickUpPointRequest
import com.kire.market_place_android.data.remote.dto.response.admin.PickUpPointResponse

interface IAdminApi {

    suspend fun getAllPickUpPoints(): List<PickUpPointResponse>
    suspend fun createPickUpPoint(pickUpPointRequest: PickUpPointRequest)
    suspend fun updatePickUpPoint(id: Int, pickUpPointRequest: PickUpPointRequest)
    suspend fun deletePickUpPoint(id: Int)
}