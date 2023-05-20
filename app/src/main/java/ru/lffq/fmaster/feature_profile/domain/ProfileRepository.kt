package ru.lffq.fmaster.feature_profile.domain

import ru.lffq.fmaster.feature_profile.domain.model.NutritionType

interface ProfileRepository {


    fun getAvailableNutritionTypes(): List<NutritionType>
}