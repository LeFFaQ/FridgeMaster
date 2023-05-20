package ru.lffq.fmaster.feature_spoonacular.data.remote

import ru.lffq.fmaster.BuildConfig
import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Information
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Instructions
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Random
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Search
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Similar
import ru.lffq.fmaster.feature_spoonacular.domain.ISpoonacularRepository

class SpoonacularRepository(private val api: SpoonacularAPI) : ISpoonacularRepository {

    override suspend fun getRandom(tags: String?, number: Int?): Resource<Random> {
        try {
            val result = api.getRandom(BuildConfig.SPOON_APIKEY, tags = tags, number = number)
            if (!result.isSuccessful) {
                val resultCode = result.code()
                return when (resultCode) {
                    402 -> Resource.Error(message = "Ошибка 402. Исчерпан лимит API Spoonacular")
                    else -> Resource.Error(message = "Ошибка $resultCode")
                }
            }
            return Resource.Success(result.body())
        } catch (e: Exception) {
            return Resource.Error(message = "${e.localizedMessage}")
        }
    }

    override suspend fun searchComplex(
        query: String,
        diet: String?,
        number: Int?,
        offset: Int?
    ): Resource<Search> {
        try {
            val result =
                api.searchComplex(
                    BuildConfig.SPOON_APIKEY,
                    query = query,
                    diet = diet,
                    number = number,
                    offset = offset
                )
            if (!result.isSuccessful) return resultCodeHandler(result.code())
            return Resource.Success(result.body())
        } catch (e: Exception) {
            return Resource.Error(message = "Неизвестная ошибка. Обратитесь к разработчику")
        }
    }

    override suspend fun getInformation(id: Int): Resource<Information> {
        try {
            val result =
                api.getInformation(id, BuildConfig.SPOON_APIKEY)
            if (!result.isSuccessful) return resultCodeHandler(result.code())

            return Resource.Success(result.body())
        } catch (e: Exception) {
            return Resource.Error(message = "${e.message}")
        }
    }

    override suspend fun getSimilar(id: Int): Resource<Similar> {
        try {
            val result =
                api.getSimilar(id, BuildConfig.SPOON_APIKEY)
            if (!result.isSuccessful) return resultCodeHandler(result.code())

            return Resource.Success(result.body())
        } catch (e: Exception) {
            return Resource.Error(message = "Неизвестная ошибка. Обратитесь к разработчику")
        }
    }

    override suspend fun getInstructions(id: Int): Resource<Instructions> {
        try {
            val result =
                api.getInstructions(id, BuildConfig.SPOON_APIKEY)
            if (!result.isSuccessful) return resultCodeHandler(result.code())

            return Resource.Success(result.body())
        } catch (e: Exception) {
            return Resource.Error(message = "Неизвестная ошибка. Обратитесь к разработчику")
        }
    }


    private fun <T> resultCodeHandler(code: Int): Resource.Error<T> {
        return when (code) {
            402 -> Resource.Error(message = "Ошибка 402. Исчерпан лимит API Spoonacular")
            else -> Resource.Error(message = "Ошибка $code. Обратитесь к разработчику")
        }
    }
}