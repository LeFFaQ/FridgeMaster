package ru.lffq.fmaster

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FridgeMasterTheme {
                val widthClass = rememberWidthClass()
                val displayFeatures = calculateDisplayFeatures(this)

                val networkConnection = networkConnectionObserver.observe()


                Log.d("WTF", "onCreate: $widthClass + ${LocalConfiguration.current.screenWidthDp}")
                AppNavigation(
                    widthClass,
                    isOpenedEarlier = true,
                    networkConnection,
                    displayFeatures = displayFeatures
                )
            }
        }
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

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