package ru.lffq.fmaster.feature_spoonacular.domain

import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Information
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Instructions
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Random
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Search
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Similar

interface ISpoonacularRepository {
    suspend fun getRandom(tags: String? = null, number: Int? = null): Resource<Random>

    suspend fun searchComplex(
        query: String,
        diet: String? = null,
        number: Int? = null,
        offset: Int? = null
    ): Resource<Search>

    suspend fun getInformation(id: Int): Resource<Information>

    suspend fun getSimilar(id: Int): Resource<Similar>

    suspend fun getInstructions(id: Int): Resource<Instructions>
}