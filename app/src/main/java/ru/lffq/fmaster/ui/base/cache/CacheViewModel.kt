package ru.lffq.fmaster.ui.base.cache

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.lffq.fmaster.feature_rskrf.domain.cache.CachingUseCase

class CacheViewModel(
    private val cachingUseCases: CachingUseCase
) : ViewModel() {
    val loadingState: Flow<CacheLoadingState> = flow {
        emit(CacheLoadingState.Initial)

        delay(1500)

        emit(CacheLoadingState.CachingCategories)
        cachingUseCases.insertToCache()

        delay(1000)
        emit(CacheLoadingState.Success)
    }


}

sealed class CacheLoadingState(val text: String) {
    object Initial : CacheLoadingState("Начинаем подготовку")
    object CachingCategories : CacheLoadingState("Узнаём категории...")

    object Success : CacheLoadingState("Всё готово!")
}