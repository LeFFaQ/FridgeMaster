package ru.lffq.fmaster.feature_rskrf.domain

import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_rskrf.data.remote.etc.AllowedCategories
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Details
import ru.lffq.fmaster.feature_rskrf.domain.model.news.News
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Tips
import ru.lffq.fmaster.feature_rskrf.domain.model.products.*

interface IRskrfRepository {

    suspend fun getSubCategories(category: AllowedCategories): Resource<Subcategories>

    suspend fun getProductGroups(category: AllowedCategories): Resource<ProductGroups>

    suspend fun getProducts(productGroup: Int): Resource<Products>

    suspend fun getProduct(productID: Int): Resource<Product>

    suspend fun queryByProductName(query: String, page: Int): Resource<Query>

    suspend fun getTips(): Resource<Tips>

    suspend fun getNews(): Resource<News>

    suspend fun getDetails(id: Int): Resource<Details>
}