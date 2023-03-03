package ru.lffq.fmaster.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VSpacer(height: Int) {
    Spacer(modifier = Modifier.height(height.dp))
}
