package ru.lffq.fmaster.feature_rskrf.domain

import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_rskrf.data.remote.etc.AllowedCategories
import ru.lffq.fmaster.feature_rskrf.domain.model.*

interface IRskrfRepository {

    suspend fun getSubCategories(category: AllowedCategories): Resource<Subcategories>

    suspend fun getProductGroups(category: AllowedCategories): Resource<ProductGroups>

    suspend fun getProducts(productGroup: Int): Resource<Products>

    suspend fun getProduct(productID: Int): Resource<Product>

    suspend fun queryByProductName(query: String, page: Int): Resource<Query>
}