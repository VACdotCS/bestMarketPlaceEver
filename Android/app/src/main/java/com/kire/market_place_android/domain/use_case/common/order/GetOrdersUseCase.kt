package com.kire.market_place_android.domain.use_case.common.order

import com.kire.market_place_android.domain.repository.IOrderRepository
import javax.inject.Inject

/**
 * By Michael Gontarev (KiREHwYE)*/
class GetOrdersUseCase @Inject constructor(
    private val orderRepository: IOrderRepository
) {
    suspend operator fun invoke() =
        orderRepository.getOrdersByUser()
}