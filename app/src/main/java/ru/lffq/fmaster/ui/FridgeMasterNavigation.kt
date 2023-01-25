package ru.lffq.fmaster.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.lffq.fmaster.ui.feed.Feed
import ru.lffq.fmaster.ui.inventory.Inventory
import ru.lffq.fmaster.ui.profile.Profile
import ru.lffq.fmaster.ui.search.Search


sealed class Destination(val route: String, val label: String, val icon: Int? = null) {
    object Feed : Destination("feed", "Feed")
    object Search : Destination("search", "Search")
    object Inventory : Destination("inventory", "Inventory")
    object Profile : Destination("profile", "Profile")

    companion object {
        val views = arrayOf(
            Feed,
            Search,
            Inventory,
            Profile,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FridgeMasterNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    isExpanded: Boolean,
    startDestination: String = Destination.Feed.route
) {
    Scaffold(
        bottomBar = {
            if (!isExpanded) {
                NavigationBar {
                    Destination.views.forEach {
                        this.AddItem(
                            navController = navController,
                            destination = it,
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
                Feed()
            }
            composable(Destination.Search.route) {
                Search()
            }
            composable(Destination.Inventory.route) {
                Inventory()
            }
            composable(Destination.Profile.route) {
                Profile()
            }
        }
    }
    Row(modifier.fillMaxSize()) {


        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier.fillMaxSize()
        ) {
            composable(Destination.Feed.route) {
                Feed()
            }
            composable(Destination.Search.route) {
                Search()
            }
            composable(Destination.Inventory.route) {
                Inventory()
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
    destination: Destination,
    //onDestinationChanged: (String) -> Unit
) {
    val currentDestination = navController.currentDestination?.route ?: Destination.Feed

    NavigationBarItem(
        selected = currentDestination == destination.route,
        onClick = {
            //onDestinationChanged(destination.route)
            navController.navigate(destination.route) {
                launchSingleTop = true
            }
        },
        label = { Text(text = destination.label) },
        icon = { })
}