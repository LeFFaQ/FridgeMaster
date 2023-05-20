package ru.lffq.fmaster.feature_spoonacular.domain.use_case

import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Random
import ru.lffq.fmaster.feature_spoonacular.domain.ISpoonacularRepository

class RandomRecipeUseCase(
    private val repository: ISpoonacularRepository,
    //private val dailyCache: DailyCache
) {

    suspend operator fun invoke(
        onSuccess: suspend (Resource.Success<Random.Recipe>) -> Unit,
        onError: suspend (Resource.Error<Any>) -> Unit,
        tags: String = "", number: Int?
    ) {


//        if (dailyCache.cachePresented()) {
//            val cached = dailyCache.readFromCache()
//            Log.d("DAILY_CACHE", "invoke: get cached")
//            return onSuccess(cached)
//        }
        val result = repository.getRandom(tags, number)

        if (result is Resource.Error) {
            return onError(Resource.Error(message = result.message!!))
        }

        //dailyCache.writeToCache(result.data?.recipes?.get(0)!!)
        return onSuccess(Resource.Success(data = result.data?.recipes?.get(0)!!))

    }
}