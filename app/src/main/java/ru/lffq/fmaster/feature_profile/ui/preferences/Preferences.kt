package ru.lffq.fmaster.feature_profile.ui.preferences

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.lffq.fmaster.feature_profile.domain.model.NutritionType

//https://cdn.dribbble.com/users/2530605/screenshots/9004158/mockup-diet_2x_4x.png
@Composable
fun Preferences() {


    val isLoading by remember { mutableStateOf(false) }
    val availableTypes by remember {
        mutableStateOf(
            listOf(
                NutritionType(
                    id = 0,
                    "Традиционный",
                    "Простое питание, не предполагающее каких-либо ограничений. Человек употребляет всё, что доступно и что нравиться.",
                    ""
                ),
                NutritionType(
                    id = 1,
                    "Рациональный",
                    "Сбалансированное и правильное питание. Данный вид предполагает получение организмом полного набора полезных веществ и сохранения здоровья.",
                    ""
                ),
                NutritionType(
                    id = 2,
                    "Диетическое ",
                    "Регламентированное употребление пищи для уменьшения, поддержания или увеличения массы тела, а также для предотвращения заболеваний.",
                    ""
                ),
                NutritionType(
                    id = 3,
                    "Вегетарианский",
                    "Полное или преимущественное исключение продуктов животного происхождения.",
                    ""
                )
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
                addTitle()
                items(availableTypes) {
                    TypeItem(item = it, isSelected = it == selectedType, onItemClick = { item ->
                        selectedType = if (selectedType != item) item else null
                    })
                }
            }
        }

        if (selectedType != null) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.BottomCenter)) {
                Text(text = "Вы выбрали: ${selectedType!!.name}. \n Продолжить?")
            }
        }
    }
}