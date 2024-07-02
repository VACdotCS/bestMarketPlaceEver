package com.kire.market_place_android.domain.use_case.manager.util

import com.kire.market_place_android.domain.use_case.manager.ConfirmOrderUseCase
import com.kire.market_place_android.domain.use_case.manager.GetOrderedProductsByOrderIdUseCase
import com.kire.market_place_android.domain.use_case.manager.GetPickUpPointByManagerId
import com.kire.market_place_android.domain.use_case.manager.GetPickUpPointStatsUseCase
import com.kire.market_place_android.domain.use_case.manager.UpdateOrderStatusUseCase

/**
 * @author Aleksey Timko (de4ltt)*/
sealed interface IManagerUseCases {
    val getPickUpPointStatsUseCase: GetPickUpPointStatsUseCase
    val updateOrderStatusUseCase: UpdateOrderStatusUseCase
    val confirmOrderUseCase: ConfirmOrderUseCase
    val getOrderedProductsByOrderIdUseCase: GetOrderedProductsByOrderIdUseCase
    val getPickUpPointByManagerId: GetPickUpPointByManagerId
}