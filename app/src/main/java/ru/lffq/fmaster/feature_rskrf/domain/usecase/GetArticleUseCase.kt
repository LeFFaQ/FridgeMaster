package ru.lffq.fmaster.feature_rskrf.domain.usecase

import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_rskrf.domain.IRskrfRepository
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Details

class GetArticleUseCase(
    private val repository: IRskrfRepository
) {

    suspend operator fun invoke(
        id: Int,
        onSuccess: suspend (Details) -> Unit,
        onError: suspend (String) -> Unit
    ) {

        val result = repository.getDetails(id)
        if (result is Resource.Error) {
            return onError(result.message!!)
        }
        return onSuccess(result.data!!)
    }
}