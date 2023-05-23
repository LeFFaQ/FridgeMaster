package ru.lffq.fmaster.feature_inventory.ui.add

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.lffq.fmaster.R
import ru.lffq.fmaster.feature_fakedata.Food


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLayout(
    onAddClick: (Food) -> Unit,
    onBackClick: () -> Unit,
) {
    val context = LocalContext.current

    val (name, onNameChange) = remember { mutableStateOf("") }
    val (foodType, onFoodTypeChange) = remember { mutableStateOf(Food.FoodType.VEGETABLE) }
    val (amount, onAmountChange) = remember { mutableStateOf("100") }
    val (measure, onMeasureChange) = remember { mutableStateOf(Food.MeasureType.GRAM) }

    val (foodTypeExpanded, onFoodTypeExpandChange) = remember { mutableStateOf(false) }
    val (measureExpanded, onMeasureExpandChange) = remember { mutableStateOf(false) }

    BackHandler(onBack = onBackClick)

    Surface(Modifier.fillMaxSize()) {

        Box {
            Column(
                Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = onNameChange,
                    label = { Text(text = stringResource(id = R.string.inventory_add_name_title)) },
                    placeholder = { Text(text = stringResource(id = R.string.inventory_add_name_title)) },
                    maxLines = 1
                )

                SelectorMenu(
                    selectedType = foodType,
                    onSelectedTypeChange = onFoodTypeChange,
                    itemTitle = { Text(stringResource(id = it.title)) },
                    selectedTypeLabel = stringResource(foodType.title),
                    expanded = foodTypeExpanded,
                    onExpandChange = onFoodTypeExpandChange,
                    items = Food.FoodType.values().toList(),
                )

                Row(Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = amount,
                        label = { Text(text = stringResource(id = R.string.inventory_add_amount_title)) },
                        onValueChange = onAmountChange,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.5f),
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    SelectorMenu(
                        selectedType = measure,
                        onSelectedTypeChange = onMeasureChange,
                        itemTitle = { Text(stringResource(id = it.title)) },
                        selectedTypeLabel = stringResource(id = measure.title),
                        expanded = measureExpanded,
                        onExpandChange = onMeasureExpandChange,
                        items = Food.MeasureType.values().toList(),
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(64.dp))
                Text(
                    text = "* - Выбор изображения недоступен. Задумка в том, что это должно добавляется по API. \n** - Интерфейс ужасен, я знаю. \n*** - При переключении сюда TopBar должен убираться, но это проблема архитектуры",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp),
                onClick = {
                    if (name.isNotEmpty()) {
                        onAddClick(
                            Food(
                                type = foodType,
                                name = name,
                                stringForSpoonApi = "",
                                image = "",
                                amount = amount.toInt(),
                                amountMeasure = measure
                            )
                        )
                    } else {
                        Toast.makeText(
                            context,
                            "Введите имя продукта",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                content = { Text(text = "Добавить") })
        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SelectorMenu(
    selectedType: T,
    onSelectedTypeChange: (T) -> Unit,
    itemTitle: @Composable (T) -> Unit,
    selectedTypeLabel: String,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    items: List<T>,
    modifier: Modifier = Modifier
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandChange,
        modifier = modifier,
        content = {
            OutlinedTextField(
                value = selectedTypeLabel,
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(id = R.string.inventory_foodtype_title)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                modifier = Modifier.menuAnchor(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { onExpandChange(false) }) {

                items.forEach {
                    DropdownMenuItem(
                        text = { itemTitle(it) },
                        onClick = { onSelectedTypeChange(it); onExpandChange(false) },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    )
}