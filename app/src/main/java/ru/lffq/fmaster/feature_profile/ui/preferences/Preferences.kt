package ru.lffq.fmaster.feature_profile.ui.preferences

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.navigate
import kotlinx.coroutines.launch
import ru.lffq.fmaster.R
import ru.lffq.fmaster.dataStore
import ru.lffq.fmaster.feature_profile.domain.model.NutritionType
import ru.lffq.fmaster.ui.base.Screen

val DS_NUTRITION_TYPE_ID_KEY = intPreferencesKey("NUTRITION_TYPE_ID")
val DS_NUTRITION_TYPE_STRING_KEY = stringPreferencesKey("NUTRITION_TYPE_STRING")
val OPENED_EARLIER_KEY = booleanPreferencesKey("OPENED_EARLIER")

//https://cdn.dribbble.com/users/2530605/screenshots/9004158/mockup-diet_2x_4x.png
@Composable
fun Preferences(navController: NavController<Screen>) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val isLoading by remember { mutableStateOf(false) }
    val availableTypes = remember {
        listOf(
            NutritionType(
                id = 0,
                name = "Традиционный",
                description = "Простое питание, не предполагающее каких-либо ограничений.",
                spoonacularDietString = "",
                image = R.drawable.preference_traditional
            ),
            NutritionType(
                id = 1,
                name = "Рациональный",
                description = "Сбалансированное питание. Данный вид предполагает получение организмом полного набора полезных веществ.",
                spoonacularDietString = "whole30",
                image = R.drawable.preference_rational
            ),
            NutritionType(
                id = 2,
                name = "Диетический",
                description = "Регламентированное употребление пищи для изменения массы тела, а также для предотвращения заболеваний.",
                spoonacularDietString = "ketogenic",
                image = R.drawable.preference_kcal
            ),
            NutritionType(
                id = 3,
                name = "Вегетарианский",
                description = "Полное или преимущественное исключение продуктов животного происхождения.",
                spoonacularDietString = "vegetarian",
                image = R.drawable.preference_vegan
            )
        )
    }
    var selectedType by remember {
        mutableStateOf<NutritionType?>(null)
    }

    Box(Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn() {
                addTitle(Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                items(availableTypes) {
                    TypeItem(item = it, isSelected = it == selectedType, onItemClick = { item ->
                        selectedType = if (selectedType != item) item else null
                    }, Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                }
            }
        }

        if (selectedType != null) {
            Button(onClick = {
                coroutineScope.launch {
                    setNutritionType(
                        context,
                        selectedType!!.id,
                        selectedType!!.spoonacularDietString
                    )
                }
                navController.navigate(Screen.Main)

            }, modifier = Modifier.align(Alignment.BottomCenter)) {
                Text(text = "Вы выбрали: ${selectedType!!.name}. \n Продолжить?")
            }
        }
    }
}

private suspend fun setNutritionType(context: Context, id: Int, string: String) {
    context.dataStore.edit {
        Log.d("Preference", "setNutritionType: setting type")
        it[OPENED_EARLIER_KEY] = true
        it[DS_NUTRITION_TYPE_ID_KEY] = id
        it[DS_NUTRITION_TYPE_STRING_KEY] = string
    }
}