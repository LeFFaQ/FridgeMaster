package ru.lffq.fmaster.feature_rskrf.data.remote

import androidx.annotation.Keep
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.lffq.fmaster.feature_rskrf.data.remote.dto.*
import ru.lffq.fmaster.feature_rskrf.data.remote.dto.news.DetailsModel
import ru.lffq.fmaster.feature_rskrf.data.remote.dto.news.NewsModel
import ru.lffq.fmaster.feature_rskrf.data.remote.dto.news.TipsModel
import ru.lffq.fmaster.feature_rskrf.data.remote.dto.products.*

@Keep
interface RskrfApi {

    // How to:
    // getSubcategories() ->
    // getProductGroups() ->
    // getProducts() ->
    // getProduct()
    // или
    // queryByProductName() ->
    // getProduct()


    /**
     * Получение списка подкатегорий.
     * Важно использовать AllowedCategories.categoryId для запросов
     * (сортировка от лишнего)
     *
     * @param id id категории
     */
    @GET("/rest/1/catalog/categories/{id}/")
    suspend fun getSubcategory(
        @Path("id") id: Int
    ): SubcategoriesModel


    /**
     * Получение списка товарных групп
     * @param id id подкатегории
     */
    @GET("/rest/1/catalog/categories/{categoryID}/productGroups/")
    suspend fun getProductGroups(
        @Path("categoryID") categoryID: Int,
    ): ProductGroupsModel


    /**
     * Получение списка товаров
     * @param id id товарной группы
     */
    @GET("/rest/1/catalog/products/{productGroupID}/")
    suspend fun getProducts(
        @Path("productGroupID") productGroupID: Int,
    ): ProductsModel

    /**
     * Получение карточки товара
     * @param id id товара
     */
    @GET("/rest/1/product/{productID}/")
    suspend fun getProduct(
        @Path("productID") productID: Int,
    ): ProductModel


    /**
     * Поиск по названиям товаров.
     * [Подробнее](https://rskrf.ru/about/dev/)
     * @param query строка поиска
     * @param page номер страницы
     */
    @GET("/rest/1/search/product")
    suspend fun queryByProductName(
        @Query("query") query: String,
        @Query("page") page: Int
    ): QueryModel


    /**
     * Получить список советов.
     *
     * [Подробнее](https://rskrf.ru/about/dev/)
     */
    @GET("/rest/1/article/tips/")
    suspend fun getTips(): TipsModel

    /**
     * Получить список новостей.
     *
     * [Подробнее](https://rskrf.ru/about/dev/)
     */
    @GET("/rest/1/article/news/")
    suspend fun getNews(): NewsModel

    /**
     * Получить конкретную новость/совет. (универсально).
     *
     * [Подробнее](https://rskrf.ru/about/dev/)
     * @param id ID новости/совета
     */
    @GET("/rest/1/article/{id}/")
    suspend fun getDetails(
        @Path("id") id: Int,
    ): DetailsModel

}