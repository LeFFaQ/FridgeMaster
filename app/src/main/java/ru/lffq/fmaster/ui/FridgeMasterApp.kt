package ru.lffq.fmaster

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import ru.lffq.fmaster.ui.FridgeMasterNavGraph
import ru.lffq.fmaster.ui.theme.FridgeMasterTheme

@Composable
fun FridgeMasterApp(
    widthSizeClass: WindowWidthSizeClass
) {
    FridgeMasterTheme {

    val navController = rememberNavController()

        val isExpanded = widthSizeClass == WindowWidthSizeClass.Expanded

        Row {
            //TODO: implement
            if (isExpanded) {
                NavigationRail {}
            }

            FridgeMasterNavGraph(
                navController = navController,
                isExpanded = isExpanded,
                //startDestination = currentRoute
            )
        }
    }
}