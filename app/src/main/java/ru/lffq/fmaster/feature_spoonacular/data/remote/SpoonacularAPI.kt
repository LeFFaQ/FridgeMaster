@file:Suppress("SpellCheckingInspection")

package ru.lffq.fmaster.feature_spoonacular.data.remote

import androidx.annotation.Keep
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Information
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Instructions
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Random
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Search
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Similar

@Keep
interface SpoonacularAPI {

    @GET("/recipes/random")
    suspend fun getRandom(
        @Query("apiKey") apiKey: String,
        @Query("tags") tags: String?,
        @Query("number") number: Int?,
    ): Response<Random>

    @GET("/recipes/complexSearch")
    suspend fun searchComplex(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String,
        @Query("diet") diet: String?,
        @Query("number") number: Int?,
        @Query("offset") offset: Int?
    ): Response<Search>


    @GET("/recipes/{id}/information")
    suspend fun getInformation(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String,
    ): Response<Information>

    @GET("/recipes/{id}/similar")
    suspend fun getSimilar(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): Response<Similar>


    @GET("/recipes/{id}/analyzedInstructions")
    suspend fun getInstructions(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String,
        @Query("stepBreakdown") extended: Boolean = false
    ): Response<Instructions>
}