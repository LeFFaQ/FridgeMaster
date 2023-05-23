package ru.lffq.fmaster.ui.base

import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.window.layout.DisplayFeature
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.rememberNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.parcelize.Parcelize
import ru.lffq.fmaster.common.connection.ConnectionObserver
import ru.lffq.fmaster.feature_profile.ui.preferences.Preferences
import ru.lffq.fmaster.ui.components.WidthClass
import androidx.navigation.compose.rememberNavController as rememberOldNavController

sealed class Screen : Parcelable {

    @Parcelize
    object SingUp : Screen()

    @Parcelize
    object SingIn : Screen()

    @Parcelize
    object Caching : Screen()

    @Parcelize
    object Main : Screen()

    @Parcelize
    object Preferences : Screen()

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppNavigation(
    widthClass: WidthClass,
    isOpenedEarlier: Boolean,
    networkConnection: Flow<ConnectionObserver.Status>,
    displayFeatures: List<DisplayFeature>
) {
    val navController =
        rememberNavController(startDestination = if (isOpenedEarlier) Screen.Main else Screen.Preferences)

    Surface(Modifier.fillMaxSize()) {
        NavHost(navController) {
            when (it) {
                is Screen.SingIn -> {}
                is Screen.SingUp -> {}
                is Screen.Preferences -> {
                    //val vm = hiltViewModel<>()
                    Preferences(navController)
                }

                is Screen.Caching -> {}
                is Screen.Main -> {


                    Log.d("WTF", "AppNavigation: MAIN")
                    val mainNavController = rememberOldNavController()
                    val pagerState = rememberPagerState(0)
                    val snackbarHostState = remember {
                        SnackbarHostState()
                    }

                    when (widthClass) {
                        is WidthClass.Compact -> MainScreen(
                            widthClass,
                            mainNavController,
                            pagerState,
                            snackbarHostState,
                            networkConnection,
                            displayFeatures
                        )

                        is WidthClass.Expanded -> MainScreen(
                            widthClass,
                            mainNavController,
                            pagerState,
                            snackbarHostState,
                            networkConnection,
                            displayFeatures
                        )

                        is WidthClass.Medium -> MainScreen(
                            widthClass,
                            mainNavController,
                            pagerState,
                            snackbarHostState,
                            networkConnection,
                            displayFeatures
                        )
                    }
                }
            }
        }
    }

}