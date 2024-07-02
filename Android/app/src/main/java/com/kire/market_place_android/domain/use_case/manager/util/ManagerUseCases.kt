package com.kire.market_place_android.domain.use_case.manager.util

import com.kire.market_place_android.domain.use_case.manager.ConfirmOrderUseCase
import com.kire.market_place_android.domain.use_case.manager.GetOrderedProductsByOrderIdUseCase
import com.kire.market_place_android.domain.use_case.manager.GetPickUpPointByManagerId
import com.kire.market_place_android.domain.use_case.manager.GetPickUpPointStatsUseCase
import com.kire.market_place_android.domain.use_case.manager.UpdateOrderStatusUseCase
import javax.inject.Inject

/**
 * @author Aleksey Timko (de4ltt)*/
data class ManagerUseCases @Inject constructor(
    override val getPickUpPointStatsUseCase: GetPickUpPointStatsUseCase,
    override val updateOrderStatusUseCase: UpdateOrderStatusUseCase,
    override val confirmOrderUseCase: ConfirmOrderUseCase,
    override val getOrderedProductsByOrderIdUseCase: GetOrderedProductsByOrderIdUseCase,
    override val getPickUpPointByManagerId: GetPickUpPointByManagerId
): IManagerUseCases
