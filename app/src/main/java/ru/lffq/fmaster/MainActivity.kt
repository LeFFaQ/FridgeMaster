package ru.lffq.fmaster

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.accompanist.adaptive.calculateDisplayFeatures
import dagger.hilt.android.AndroidEntryPoint
import ru.lffq.fmaster.common.connection.ConnectionObserver
import ru.lffq.fmaster.ui.base.AppNavigation
import ru.lffq.fmaster.ui.components.WidthClass
import ru.lffq.fmaster.ui.theme.FridgeMasterTheme
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalMaterial3WindowSizeClassApi
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkConnectionObserver: ConnectionObserver
    private val mainViewModel: MainViewModel by viewModels()

    private val openedEarlier by lazy {
        mainViewModel.isFirstStart
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition { openedEarlier.value == null }
        }
        setContent {
            FridgeMasterTheme {
                val widthClass = rememberWidthClass()
                val displayFeatures = calculateDisplayFeatures(this)

                val networkConnection = networkConnectionObserver.observe()

                val isFirstStart by openedEarlier.collectAsState()
                Log.d("ACTIVITY", "onCreate: $isFirstStart")


                Log.d("WTF", "onCreate: $widthClass + ${LocalConfiguration.current.screenWidthDp}")
                isFirstStart?.let { _openedEarlier ->
                    AppNavigation(
                        widthClass,
                        isOpenedEarlier = _openedEarlier,
                        networkConnection,
                        displayFeatures = displayFeatures
                    )
                }
            }
        }
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "FRIDGE_MASTER_APP_DS")

@Composable
fun rememberWidthClass(): WidthClass {
    val configuration = LocalConfiguration.current
    return if (configuration.screenWidthDp < 600) {
        WidthClass.Compact
    } else if (configuration.screenWidthDp < 860) {
        WidthClass.Medium
    } else {
        WidthClass.Expanded
    }
}