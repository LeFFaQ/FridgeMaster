package ru.lffq.fmaster.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.lffq.fmaster.feature_feed.ui.Feed
import ru.lffq.fmaster.feature_feed.ui.FeedViewModel
import ru.lffq.fmaster.feature_inventory.ui.Inventory
import ru.lffq.fmaster.ui.profile.Profile
import ru.lffq.fmaster.ui.search.Search


sealed class Destination(val route: String, val label: String, val icon: Int? = null) {


    sealed class Feed(val route: String, val label: String? = null) {
        // Ничего лучше для вложенной навигации я не придумал
        object Main : Feed("feed.main")
        object Article : Feed("feed.article", "Article")
        companion object : Destination("feed", "Feed")
    }

    object Search : Destination("search", "Search")
    object Inventory : Destination("inventory", "Inventory")

    object Profile : Destination("profile", "Profile")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FridgeMasterNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    isExpanded: Boolean,
    startDestination: String = Destination.Feed.route
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination =
        navBackStackEntry?.destination

    val views: List<Destination> = listOf(
        Destination.Feed,
        Destination.Search,
        Destination.Inventory,
        Destination.Profile
    )

    Scaffold(
        bottomBar = {
            if (!isExpanded) {
                NavigationBar {
                    views.forEach { destination ->
                        AddItem(
                            navController = navController,
                            currentDestination = currentDestination,
                            destination = destination
                        )
                    }
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ) {
            composable(Destination.Feed.route) {
                val vm: FeedViewModel = hiltViewModel()
                Log.d(TAG, "FridgeMasterNavGraph: $vm")
                Feed(vm)
            }
            composable(Destination.Search.route) {
                Search()
            }
            composable(Destination.Inventory.route) {
                Inventory(hiltViewModel())
            }
            composable(Destination.Profile.route) {
                Profile()
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    navController: NavHostController,
    currentDestination: NavDestination?,
    destination: Destination
) {
    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
        onClick = {
            navController.navigate(destination.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

        },
        label = { Text(text = destination.label) },
        icon = { })
}