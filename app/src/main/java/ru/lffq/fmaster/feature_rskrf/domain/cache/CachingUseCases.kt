package ru.lffq.fmaster.feature_rskrf.domain.cache

import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_rskrf.data.remote.etc.AllowedCategories
import ru.lffq.fmaster.feature_rskrf.domain.IRskrfRepository

class CachingUseCase(
    private val repository: IRskrfRepository,
    private val cachingDao: CategoryDao,
) {

    suspend fun insertToCache() {
        val categories = repository.getSubCategories(AllowedCategories.NUTRITION)

        if (categories is Resource.Success) {

            categories.data?.forEach {
                cachingDao.insertCategoryToCache(
                    CategoryEntity(it.id, it.title, it.thumbnail, it.icon)
                )
            }

        }

    }
}
