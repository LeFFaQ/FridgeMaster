package ru.lffq.fmaster.feature_profile.ui.preferences

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.lffq.fmaster.feature_profile.domain.model.NutritionType
import ru.lffq.fmaster.ui.components.provideSelectedCardColors
import ru.lffq.fmaster.ui.components.provideUnselectedCardBorderStroke
import ru.lffq.fmaster.ui.components.provideUnselectedCardColors

fun LazyListScope.addTitle(modifier: Modifier) {
    item {
        Text(
            text = "Укажите тип вашего питания",
            style = MaterialTheme.typography.displaySmall,
            modifier = modifier.fillMaxWidth()
        )
    }
}


@Composable
fun TypeItem(
    item: NutritionType,
    isSelected: Boolean,
    onItemClick: (NutritionType) -> Unit,
    modifier: Modifier
) {

    val colors = if (!isSelected) provideUnselectedCardColors() else provideSelectedCardColors()
    val border = if (!isSelected) provideUnselectedCardBorderStroke() else null

    val interactionSource = remember {
        MutableInteractionSource()
    }

    Card(
        colors = colors,
        border = border,
        modifier = modifier
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
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = null,
                Modifier.size(72.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.headlineSmall
                )
                //Text(text = item.description)
            }

        }
    }
}