package ru.lffq.fmaster.feature_profile.domain.preferences

import kotlinx.coroutines.flow.Flow

interface INutritionTypeRepository {

    suspend fun getNutritionType(): Flow<Int?>

    suspend fun setNutritionType(id: Int)
}