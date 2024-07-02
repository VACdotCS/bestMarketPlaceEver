package com.kire.market_place_android.domain.use_case.common.product

import com.kire.market_place_android.domain.repository.IProductRepository
import javax.inject.Inject

class GetAllAvailableCategoriesUseCase @Inject constructor(
    private val productRepository: IProductRepository
) {
    suspend operator fun invoke() =
        productRepository.getAllAvailableCategories()
}