package ru.lffq.fmaster.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade


@Composable
fun rememberPlaceholder(): PlaceholderHighlight {
    return PlaceholderHighlight.fade(highlightColor = MaterialTheme.colorScheme.surfaceVariant)
}