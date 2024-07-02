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
import javax.inject.Inject

/**
 * By Michael Gontarev (KiREHwYE)*/
data class AdminUseCases @Inject constructor(
    override val deletePickUpPointUseCase: DeletePickUpPointUseCase,
    override val deleteProductUseCase: DeleteProductUseCase,
    override val getAllPickUpPointsUseCase: GetAllPickUpPointsUseCase,
    override val getUsersStatusUseCase: GetUsersStatsUseCase,
    override val updatePickUpPointUseCase: UpdatePickUpPointUseCase,
    override val updateProductUseCase: UpdateProductUseCase,
    override val getAllUsersUseCase: GetAllUsersUseCase,
    override val createPickUpPointUseCase: CreatePickUpPointUseCase,
    override val addProductUseCase: AddProductUseCase
): IAdminUseCases