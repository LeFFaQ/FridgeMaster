package ru.lffq.fmaster.feature_rskrf.domain

import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_rskrf.data.remote.etc.AllowedCategories
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Article
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Details
import ru.lffq.fmaster.feature_rskrf.domain.model.products.Product
import ru.lffq.fmaster.feature_rskrf.domain.model.products.ProductGroups
import ru.lffq.fmaster.feature_rskrf.domain.model.products.Products
import ru.lffq.fmaster.feature_rskrf.domain.model.products.Query
import ru.lffq.fmaster.feature_rskrf.domain.model.products.Subcategory


interface IRskrfRepository {

    suspend fun getSubCategories(category: AllowedCategories): Resource<List<Subcategory>>

    suspend fun getProductGroups(category: AllowedCategories): Resource<ProductGroups>

    suspend fun getProducts(productGroup: Int): Resource<Products>

    suspend fun getProduct(productID: Int): Resource<Product>

    suspend fun queryByProductName(query: String, page: Int): Resource<Query>

    suspend fun getTips(): Resource<List<Article>>

    suspend fun getNews(): Resource<List<Article>>

    suspend fun getDetails(id: Int): Resource<Details>
}