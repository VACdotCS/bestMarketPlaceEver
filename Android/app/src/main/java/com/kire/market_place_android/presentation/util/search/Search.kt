package com.kire.market_place_android.presentation.util.search

import com.kire.market_place_android.presentation.model.product.Product
import java.math.BigDecimal

/**
 * Filters a list of products based on a search string and a list of categories.
 *
 * @param products The list of products to filter.
 * @param searchString The search string to match against product titles.
 * @param categoryList The list of categories to filter by. If empty, all categories are included.
 * @param priceRange The price range to filter by.
 * @return A filtered list of products that match the search criteria and belong to the specified categories.
 * @author Aleksey Timko (de4ltt)
 */
fun onSearchRequestChange(
    products: List<Product>,
    searchString: String,
    categoryList: List<String>,
    priceRange: Pair<BigDecimal, BigDecimal>
): List<Product> = products.filter { product ->
            (searchString.isEmpty() || levenshteinRatio(product.title, searchString) > 0.7 || product.title.contains(searchString)) &&
            (categoryList.isEmpty() || categoryList.contains(product.category)) &&
            priceRange.first <= product.price && product.price <= priceRange.second
}