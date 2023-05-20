package ru.lffq.fmaster.feature_profile.ui.preferences

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ru.lffq.fmaster.feature_profile.domain.model.NutritionType
import ru.lffq.fmaster.ui.components.provideSelectedCardColors
import ru.lffq.fmaster.ui.components.provideUnselectedCardBorderStroke
import ru.lffq.fmaster.ui.components.provideUnselectedCardColors

fun LazyListScope.addTitle() {
    item {
        Text(text = "Укажите ваш тип питания")
    }
}


@Composable
fun TypeItem(
    item: NutritionType,
    isSelected: Boolean,
    onItemClick: (NutritionType) -> Unit
) {

    val colors = if (!isSelected) provideUnselectedCardColors() else provideSelectedCardColors()
    val border = if (!isSelected) provideUnselectedCardBorderStroke() else null

    val interactionSource = remember {
        MutableInteractionSource()
    }
    Log.d("WTF2", "TypeItem: RECOMPOSED")

    Card(
        colors = colors,
        border = border,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .selectable(
                    selected = isSelected,
                    interactionSource = interactionSource,
                    indication = rememberRipple(),
                    enabled = true,
                    onClick = {
                        onItemClick(item)
                    })
        ) {
            Image(
                painter = rememberAsyncImagePainter(""),
                contentDescription = null,
                Modifier.size(98.dp)
            )
            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Text(text = item.name)
                Text(text = item.description)
            }

        }
    }
}