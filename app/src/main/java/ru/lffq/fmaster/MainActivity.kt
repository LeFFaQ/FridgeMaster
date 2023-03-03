package ru.lffq.fmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import dagger.hilt.android.AndroidEntryPoint
import ru.lffq.fmaster.ui.theme.FridgeMasterTheme

@AndroidEntryPoint
@ExperimentalMaterial3WindowSizeClassApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FridgeMasterTheme {
                val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
                FridgeMasterApp(widthSizeClass)
            }
        }
    }
}