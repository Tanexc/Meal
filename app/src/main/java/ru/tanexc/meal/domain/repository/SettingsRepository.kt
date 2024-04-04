package ru.tanexc.meal.domain.repository

import com.t8rin.dynamic.theme.ColorTuple
import kotlinx.coroutines.flow.Flow
import ru.tanexc.meal.presentation.util.other.HeadlineOverflowBehaviour
import ru.tanexc.meal.presentation.util.other.Settings

interface SettingsRepository {

    fun getSettingsAsFlow(): Flow<Settings>

    suspend fun getSettings(): Settings

    suspend fun updateAmoledMode(value: Boolean)

    suspend fun updateIsDarkMode(value: Boolean)

    suspend fun updateUseDynamicColors(value: Boolean)

    suspend fun updateBordersEnabled(value: Boolean)

    suspend fun updateColorTuple(value: ColorTuple)

    suspend fun updateHeadlineOverflowBehaviour(value: HeadlineOverflowBehaviour)
}