package ru.lffq.fmaster.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EmptyList(
    emptyText: @Composable (() -> Unit),
    content: @Composable (() -> Unit),
    modifier: Modifier
) {

}

@Composable
fun EmptyList(
    content: @Composable () -> Unit,
    modifier: Modifier
) {
    EmptyList(
        emptyText = { Text(text = "There is nothing yet") },
        content = content,
        modifier = modifier
    )
}