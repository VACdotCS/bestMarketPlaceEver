package com.kire.market_place_android.domain.use_case.common.product

import com.kire.market_place_android.domain.repository.IOrderRepository
import javax.inject.Inject

/**
 * By Michael Gontarev (KiREHwYE)*/
class ProductDeleteFromCartUseCase @Inject constructor(
    private val orderRepository: IOrderRepository
) {
    operator fun invoke() = { /*TODO*/ }
}