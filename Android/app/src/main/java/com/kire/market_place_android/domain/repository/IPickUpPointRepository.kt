package com.kire.market_place_android.domain.repository

import com.kire.market_place_android.domain.model.IRequestResultDomain

/**
 * By Michael Gontarev (KiREHwYE)*/
interface IPickUpPointRepository {
    suspend fun getAllPickUpPoints(): IRequestResultDomain
    suspend fun createPickUpPoint(managerId: Int, address: String): IRequestResultDomain
    suspend fun updatePickUpPoint(id: Int, managerId: Int, address: String): IRequestResultDomain
    suspend fun deletePickUpPoint(id: Int): IRequestResultDomain
    suspend fun getPickUpPointByManagerId(id: Int): IRequestResultDomain
}