package ru.tanexc.meal.presentation.screen.profilescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.t8rin.dynamic.theme.ColorTuple
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.tanexc.meal.domain.repository.SettingsRepository
import ru.tanexc.meal.presentation.util.other.HeadlineOverflowBehaviour
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    fun updateAmoledMode(enabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            settingsRepository.updateAmoledMode(enabled)
        }
    }

    fun updateUseDarkMode(enabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            settingsRepository.updateIsDarkMode(enabled)
        }
    }

    fun updateUseBorders(enabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            settingsRepository.updateBordersEnabled(enabled)
        }
    }

    fun updateUseDynamicColors(enabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            settingsRepository.updateUseDynamicColors(enabled)
        }
    }

    fun updateColorTuple(colors: ColorTuple) {
        viewModelScope.launch(Dispatchers.IO) {
            settingsRepository.updateColorTuple(colors)
        }
    }

    fun updateOverflowBehaviour(value: HeadlineOverflowBehaviour) {
        viewModelScope.launch(Dispatchers.IO) {
            settingsRepository.updateHeadlineOverflowBehaviour(value)
        }
    }
}