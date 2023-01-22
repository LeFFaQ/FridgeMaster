package ru.lffq.fmaster

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.lffq.fmaster.ui.Destinations
import ru.lffq.fmaster.ui.FridgeMasterNavGraph
import ru.lffq.fmaster.ui.theme.FridgeMasterTheme

@Composable
fun FridgeMasterApp(
    widthSizeClass: WindowWidthSizeClass
) {
    FridgeMasterTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: Destinations.FEED_ROUTE

        val isExpanded = widthSizeClass == WindowWidthSizeClass.Expanded

        Row {
            //TODO: implement
            if (isExpanded) {
                NavigationRail {}
            }

            FridgeMasterNavGraph(
                navController = navController,
                isExpanded = isExpanded
            )
        }
    }
}