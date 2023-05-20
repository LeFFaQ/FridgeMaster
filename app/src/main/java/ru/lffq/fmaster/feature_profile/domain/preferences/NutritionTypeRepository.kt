package ru.lffq.fmaster.feature_profile.domain.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.lffq.fmaster.dataStore
import javax.inject.Inject

class NutritionTypeRepository @Inject constructor(
    @ApplicationContext val context: Context
) : INutritionTypeRepository {
    override suspend fun setNutritionType(id: Int) {
        context.dataStore.edit {
            it[NUTRITION_TYPE_KEY] = id
        }
    }

    override suspend fun getNutritionType(): Flow<Int?> {
        return context.dataStore.data.map {
            it[NUTRITION_TYPE_KEY]
        }
    }

    val NUTRITION_TYPE_KEY = intPreferencesKey("NUTRITION_TYPE")
}
