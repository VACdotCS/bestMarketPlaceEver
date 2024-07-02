package com.kire.market_place_android.domain.use_case.admin

import com.kire.market_place_android.domain.model.product.ProductDomain
import com.kire.market_place_android.domain.repository.IProductRepository
import javax.inject.Inject

/**
 * by Aleksey Timko (de4ltt)*/
class AddProductUseCase @Inject constructor(
    private val productRepository: IProductRepository
) {
    suspend operator fun invoke(
        image: ByteArray,
        product: ProductDomain
    ) = productRepository.addProduct(
        image = image,
        product = product
    )

}