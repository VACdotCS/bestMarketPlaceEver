package com.kire.market_place_android.domain.use_case.manager

import com.kire.market_place_android.domain.repository.IOrderRepository
import javax.inject.Inject

class ConfirmOrderUseCase @Inject constructor(
    private val orderRepository: IOrderRepository
) {
    suspend operator fun invoke(id: Int, received: List<Int>, returned: List<Int>) =
        orderRepository.confirmOrder(
            id = id,
            received = received,
            returned = returned
        )
}