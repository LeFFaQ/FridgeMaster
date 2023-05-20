package ru.lffq.fmaster.feature_profile.ui.preferences

import androidx.lifecycle.ViewModel
import ru.lffq.fmaster.feature_profile.domain.preferences.INutritionTypeRepository
import javax.inject.Inject

class PreferencesViewModel @Inject constructor(
    private val nutritionTypeRepository: INutritionTypeRepository
) : ViewModel() {


}