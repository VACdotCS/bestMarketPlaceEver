package com.kire.market_place_android.domain.use_case.admin.util

import com.kire.market_place_android.domain.use_case.admin.AddProductUseCase
import com.kire.market_place_android.domain.use_case.admin.CreatePickUpPointUseCase
import com.kire.market_place_android.domain.use_case.admin.DeletePickUpPointUseCase
import com.kire.market_place_android.domain.use_case.admin.DeleteProductUseCase
import com.kire.market_place_android.domain.use_case.admin.GetAllUsersUseCase
import com.kire.market_place_android.domain.use_case.admin.GetAllPickUpPointsUseCase
import com.kire.market_place_android.domain.use_case.admin.GetUsersStatsUseCase
import com.kire.market_place_android.domain.use_case.admin.UpdatePickUpPointUseCase
import com.kire.market_place_android.domain.use_case.admin.UpdateProductUseCase

/**
 * By Michael Gontarev (KiREHwYE)*/
sealed interface IAdminUseCases {
    val deletePickUpPointUseCase: DeletePickUpPointUseCase
    val deleteProductUseCase: DeleteProductUseCase
    val getAllPickUpPointsUseCase: GetAllPickUpPointsUseCase
    val getUsersStatusUseCase: GetUsersStatsUseCase
    val addProductUseCase: AddProductUseCase
    val updateProductUseCase: UpdateProductUseCase
    val updatePickUpPointUseCase: UpdatePickUpPointUseCase
    val getAllUsersUseCase: GetAllUsersUseCase
    val createPickUpPointUseCase: CreatePickUpPointUseCase
}