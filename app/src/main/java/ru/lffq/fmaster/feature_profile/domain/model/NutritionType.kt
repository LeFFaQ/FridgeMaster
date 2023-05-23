package ru.lffq.fmaster.feature_profile.domain.model

import androidx.annotation.DrawableRes

data class NutritionType(
    val id: Int,
    val name: String,
    val description: String,
    val spoonacularDietString: String,
    @DrawableRes val image: Int,
)