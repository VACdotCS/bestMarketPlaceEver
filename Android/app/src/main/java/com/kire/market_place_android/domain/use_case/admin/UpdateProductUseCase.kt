package com.kire.market_place_android.domain.use_case.admin

import com.kire.market_place_android.domain.model.product.ProductDomain
import com.kire.market_place_android.domain.repository.IProductRepository
import javax.inject.Inject

/**
 * By Aleksey Timko (de4ltt)*/
class UpdateProductUseCase @Inject constructor(
    private val productRepository: IProductRepository
) {
    suspend operator fun invoke(
        id: Int,
        image: ByteArray,
        product: ProductDomain
    ) = productRepository.updateProduct(
        id = id,
        image = image,
        product = product
    )
}