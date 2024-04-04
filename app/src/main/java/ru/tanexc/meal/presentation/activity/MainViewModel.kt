package ru.tanexc.meal.presentation.activity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import ru.tanexc.meal.domain.repository.SettingsRepository
import ru.tanexc.meal.presentation.util.other.Screen
import ru.tanexc.meal.presentation.util.other.Settings
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.Menu)
    val currentScreen by _currentScreen

    private val _settings: MutableState<Settings> = mutableStateOf(Settings.Default())
    val settings: Settings by _settings

    init {
        runBlocking {
            _settings.value = settingsRepository.getSettings()
            _currentScreen.value = Screen.Menu
        }

        settingsRepository.getSettingsAsFlow().onEach {
            _settings.value = it
        }.launchIn(viewModelScope)

    }

    fun updateCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }
}