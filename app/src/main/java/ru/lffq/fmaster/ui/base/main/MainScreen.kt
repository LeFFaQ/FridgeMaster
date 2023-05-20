package ru.lffq.fmaster.ui.base

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.window.layout.DisplayFeature
import kotlinx.coroutines.flow.Flow
import ru.lffq.fmaster.common.connection.ConnectionObserver
import ru.lffq.fmaster.ui.base.main.*
import ru.lffq.fmaster.ui.components.WidthClass


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    widthClass: WidthClass.Compact,
    navController: NavHostController,
    pagerState: PagerState,
    snackBarState: SnackbarHostState,
    networkConnection: Flow<ConnectionObserver.Status>,
    displayFeatures: List<DisplayFeature>
) {

    MainScaffold(
        bottomNav = {
            MainBottomNavigation(
                navController = navController
            )
        },
        pagerState = pagerState,
        navController = navController,
        content = {
            MainNavigation(
                navController = navController,
                widthClass,
                pagerState,
                snackBarState,
                displayFeatures
            )
        },
        snackBarState = snackBarState,
        networkConnection = networkConnection
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    widthClass: WidthClass.Medium,
    navController: NavHostController,
    pagerState: PagerState,
    snackBarState: SnackbarHostState,
    networkConnection: Flow<ConnectionObserver.Status>,
    displayFeatures: List<DisplayFeature>
) {
    Row {
        MainNavigationRail(navController)
        MainScaffold(
            content = {
                MainNavigation(
                    navController = navController,
                    widthClass,
                    pagerState,
                    snackBarState,
                    displayFeatures
                )
            },
            navController = navController,
            pagerState = pagerState,
            snackBarState = snackBarState,
            networkConnection = networkConnection,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    widthClass: WidthClass.Expanded,
    navController: NavHostController,
    pagerState: PagerState,
    snackBarState: SnackbarHostState,
    networkConnection: Flow<ConnectionObserver.Status>,
    displayFeatures: List<DisplayFeature>
) {

    Row {
        PermanentDrawerSheet() {
            MainPermanentDrawerContent(
                navController = navController,
                widthClass = widthClass
            )
        }
        MainScaffold(
            content = {
                MainNavigation(
                    navController = navController,
                    widthClass,
                    pagerState,
                    snackBarState,
                    displayFeatures
                )
            },
            navController = navController,
            pagerState = pagerState,
            snackBarState = snackBarState,
            networkConnection = networkConnection,
        )
    }
}
