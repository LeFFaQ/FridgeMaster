package ru.lffq.fmaster.feature_spoonacular.domain.use_case

import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Information
import ru.lffq.fmaster.feature_spoonacular.domain.ISpoonacularRepository

class GetRecipeInformationUseCase(private val repository: ISpoonacularRepository) {

    suspend operator fun invoke(
        onSuccess: suspend (Information) -> Unit,
        onError: suspend (message: String) -> Unit,
        id: Int
    ) {
        val result = repository.getInformation(id)

        if (result is Resource.Error) {
            return onError(result.message!!)
        }

        return onSuccess(result.data!!)
    }
}