package ru.lffq.fmaster

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.lffq.fmaster.feature_profile.ui.preferences.DS_NUTRITION_TYPE_ID_KEY
import ru.lffq.fmaster.feature_profile.ui.preferences.DS_NUTRITION_TYPE_STRING_KEY
import ru.lffq.fmaster.feature_profile.ui.preferences.OPENED_EARLIER_KEY
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {

    private val _isFirstStart: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isFirstStart = _isFirstStart.asStateFlow()

    init {
        viewModelScope.launch {
            delay(300)
            context.dataStore.data.map {

            }
            _isFirstStart.value = context.dataStore.data.map {
                Log.d(
                    "MAIN_VIEW_MODEL",
                    "OPENED: ${it[OPENED_EARLIER_KEY]}; NUTRITION: ${it[DS_NUTRITION_TYPE_ID_KEY]} + ${it[DS_NUTRITION_TYPE_STRING_KEY]}"
                )
                it[OPENED_EARLIER_KEY] ?: false
            }.stateIn(viewModelScope).value
        }
    }
}