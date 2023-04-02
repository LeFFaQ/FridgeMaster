package ru.lffq.fmaster.feature_rskrf.domain

import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_rskrf.data.remote.etc.AllowedCategories
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Article
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Details
import ru.lffq.fmaster.feature_rskrf.domain.model.products.*


interface IRskrfRepository {

    suspend fun getSubCategories(category: AllowedCategories): Resource<Subcategories>

    suspend fun getProductGroups(category: AllowedCategories): Resource<ProductGroups>

    suspend fun getProducts(productGroup: Int): Resource<Products>

    suspend fun getProduct(productID: Int): Resource<Product>

    suspend fun queryByProductName(query: String, page: Int): Resource<Query>

    suspend fun getTips(): Resource<List<Article>>

    suspend fun getNews(): Resource<List<Article>>

    suspend fun getDetails(id: Int): Resource<Details>
}