package com.kire.market_place_android.domain.use_case.manager

import com.kire.market_place_android.domain.repository.IPickUpPointRepository

import javax.inject.Inject

class GetPickUpPointByManagerId @Inject constructor(
    private val pickUpPointRepository: IPickUpPointRepository
) {
    suspend operator fun invoke(id: Int) =
        pickUpPointRepository.getPickUpPointByManagerId(id)
}