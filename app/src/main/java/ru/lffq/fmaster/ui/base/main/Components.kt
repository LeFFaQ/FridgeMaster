package ru.lffq.fmaster.ui.base.main

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.lffq.fmaster.R
import ru.lffq.fmaster.common.connection.ConnectionObserver
import ru.lffq.fmaster.feature_inventory.ui.InventoryFAB
import ru.lffq.fmaster.feature_inventory.ui.InventoryTopBar
import ru.lffq.fmaster.ui.components.WidthClass
import ru.lffq.fmaster.ui.theme.elevation

val barDestinations: List<MainSubScreen> = listOf(
    MainSubScreen.Feed,
    MainSubScreen.Inventory, MainSubScreen.Profile
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScaffold(
    bottomNav: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
    navController: NavHostController,
    pagerState: PagerState,
    snackBarState: SnackbarHostState,
    networkConnection: Flow<ConnectionObserver.Status>,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: MainSubScreen.Feed.destination
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val errorChannel: Channel<String> = Channel()

    val networkConnectionState by
    networkConnection.collectAsState(initial = ConnectionObserver.Status.Unavailable)
    Log.d("net", "MainScaffold: $networkConnectionState")



    LaunchedEffect(snackBarState, networkConnectionState) {
        launch {
            errorChannel.consumeEach {
                snackBarState.showSnackbar(it, duration = SnackbarDuration.Short)
            }
        }
        launch {
            when (networkConnectionState) {
                ConnectionObserver.Status.Lost -> {
                    snackBarState.showSnackbar(context.getString(R.string.network_status_lost))
                }

                ConnectionObserver.Status.Unavailable -> {
                    snackBarState.showSnackbar(context.getString(R.string.network_status_unavaliable))
                }

                else -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            when (currentRoute) {

                MainSubScreen.Inventory.destination -> {
                    InventoryTopBar(pagerState = pagerState, onTabClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(it)
                        }
                    })
                }

//                MainSubScreen.Feed.destination -> {
//                    val (value, onChange) = remember { mutableStateOf("") }
//                    FeedTopBar(value = value, onValueChange = onChange, onSearch = {}, onActiveChange = {}) {
//                        Text(text = value)
//                    }
//                }
            }
        },
        bottomBar = bottomNav ?: {},
        floatingActionButton = {
            when (currentRoute) {
                MainSubScreen.Inventory.destination -> {
                    InventoryFAB(
                        pagerState = pagerState,
                        onAddClick = { /*TODO*/ },
                        onGoCatalogClick = { /*TODO*/ })
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackBarState) }
    ) {
        Surface(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            content()
        }
    }
}

@Composable
fun MainBottomNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination =
        navBackStackEntry?.destination
    val context = LocalContext.current




    NavigationBar {
        //Log.d("RC_TEST", "MainBottomNavigation: recomposed")
        barDestinations.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.destination } == true,
                onClick =
                {
                    navController.navigate(screen.destination) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(screen.icon!!),
                        contentDescription = null
                    )
                },
                label = { Text(text = stringResource(screen.title)) }
            )
        }
    }
}

@Composable
fun MainNavigationRail(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val context = LocalContext.current

    NavigationRail() {

        Box(Modifier.fillMaxHeight()) {

            Column(
                Modifier.padding(top = 64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                FloatingActionButton(
                    onClick = {
                        Toast.makeText(
                            context,
                            context.getText(R.string.not_working),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    elevation = FloatingActionButtonDefaults.elevation(MaterialTheme.elevation.level1),
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary,
                    content = { Icon(Icons.Default.Add, contentDescription = null) }
                )
                Spacer(modifier = Modifier.height(12.dp))
                FloatingActionButton(
                    onClick = {
                        Toast.makeText(
                            context,
                            context.getText(R.string.not_working),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    elevation = FloatingActionButtonDefaults.elevation(MaterialTheme.elevation.level1),
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary,
                    content = {
                        Icon(
                            painterResource(id = R.drawable.ic_scan_24),
                            contentDescription = null
                        )
                    }
                )

                Spacer(modifier = Modifier.height(48.dp))

                barDestinations.forEach { screen ->
                    NavigationRailItem(
                        label = { Text(text = stringResource(id = screen.title)) },
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon!!),
                                contentDescription = null
                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.destination } == true,
                        onClick = {
                            navController.navigate(screen.destination) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

    }
}

@Composable
fun MainPermanentDrawerContent(
    navController: NavHostController,
    widthClass: WidthClass.Expanded
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val context = LocalContext.current

    Box(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .padding(top = 64.dp)

        ) {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(id = R.string.navigation_add_to_inventory)) },
                icon = { Icon(Icons.Default.Add, null) },
                elevation = FloatingActionButtonDefaults.elevation(MaterialTheme.elevation.level1),
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary,
                onClick = {
                    Toast.makeText(
                        context,
                        context.getText(R.string.not_working),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(id = R.string.navigation_scan_barcode)) },
                icon = { Icon(painterResource(id = R.drawable.ic_scan_24), null) },
                elevation = FloatingActionButtonDefaults.elevation(MaterialTheme.elevation.level1),
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary,
                onClick = {
                    Toast.makeText(
                        context,
                        context.getText(R.string.not_working),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(32.dp))
            barDestinations.forEach { screen ->

                NavigationDrawerItem(
                    label = { Text(text = stringResource(id = screen.title)) },
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icon!!),
                            contentDescription = null
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.destination } == true,
                    onClick = {
                        navController.navigate(screen.destination) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })

            }
        }

        NavigationProfileItem(
            widthClass = widthClass,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


@Composable
fun NavigationProfileItem(widthClass: WidthClass.Expanded, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_placeholder),
            contentDescription = null,
            modifier = Modifier.size(42.dp)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .height(72.dp), verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Анонимный пользователь")
        }
    }
}

@Composable
fun NavigationProfileItem(widthClass: WidthClass.Medium) {

}