package ru.lffq.fmaster.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.lffq.fmaster.ui.feed.Feed
import ru.lffq.fmaster.ui.inventory.Inventory
import ru.lffq.fmaster.ui.profile.Profile
import ru.lffq.fmaster.ui.search.Search

object Destinations {
    const val FEED_ROUTE = "feed"
    const val SEARCH_ROUTE = "search"
    const val INVENTORY_ROUTE = "inventory"
    const val PROFILE_ROUTE = "profile"
}

@Composable
fun FridgeMasterNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    isExpanded: Boolean,
    startDestination: String = Destinations.FEED_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Destinations.FEED_ROUTE) {
            Feed()
        }
        composable(Destinations.SEARCH_ROUTE) {
            Search()
        }
        composable(Destinations.INVENTORY_ROUTE) {
            Inventory()
        }
        composable(Destinations.PROFILE_ROUTE) {
            Profile()
        }
    }
}