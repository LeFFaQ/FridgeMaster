package ru.lffq.fmaster.feature_rskrf.data.remote

import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_rskrf.data.remote.etc.AllowedCategories
import ru.lffq.fmaster.feature_rskrf.domain.IRskrfRepository
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Details
import ru.lffq.fmaster.feature_rskrf.domain.model.news.News
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Tips
import ru.lffq.fmaster.feature_rskrf.domain.model.products.*


class RskrfRepository(
    private val api: RskrfApi
) : IRskrfRepository {
    override suspend fun getSubCategories(category: AllowedCategories): Resource<Subcategories> {
        return try {
            Resource.Success(
                data = api.getSubcategory(category.categoryId).toSubcategories()
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getProductGroups(category: AllowedCategories): Resource<ProductGroups> {
        return try {
            Resource.Success(
                data = api.getProductGroups(category.categoryId).toProductGroups()
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getProducts(productGroup: Int): Resource<Products> {
        return try {
            Resource.Success(
                data = api.getProducts(productGroup).toProducts()
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getProduct(productID: Int): Resource<Product> {
        return try {
            Resource.Success(
                data = api.getProduct(productID).toProduct()
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun queryByProductName(query: String, page: Int): Resource<Query> {
        return try {
            Resource.Success(
                data = api.queryByProductName(query, page).toQuery()
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getTips(): Resource<Tips> {
        return try {
            Resource.Success(
                data = api.queryByProductName(query, page).toQuery()
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getNews(): Resource<News> {
        return try {
            Resource.Success(
                data = api.queryByProductName(query, page).toQuery()
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getDetails(id: Int): Resource<Details> {
        return try {
            Resource.Success(
                data = api.queryByProductName(query, page).toQuery()
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }


}