package ru.tanexc.meal.data.repository

import androidx.compose.ui.graphics.Color
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.t8rin.dynamic.theme.ColorTuple
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ru.tanexc.meal.data.util.Keys.AMOLED_MODE
import ru.tanexc.meal.data.util.Keys.BORDERS_ENABLED
import ru.tanexc.meal.data.util.Keys.COLOR_TUPLE
import ru.tanexc.meal.data.util.Keys.IS_DARK_MODE
import ru.tanexc.meal.data.util.Keys.OVERFLOW_BEHAVIOUR
import ru.tanexc.meal.data.util.Keys.USE_DYNAMIC_COLORS
import ru.tanexc.meal.domain.repository.SettingsRepository
import ru.tanexc.meal.presentation.util.other.HeadlineOverflowBehaviour
import ru.tanexc.meal.presentation.util.other.Settings
import ru.tanexc.meal.presentation.util.ui.theme.defaultDarkColorTuple
import ru.tanexc.meal.presentation.util.ui.theme.defaultLightColorTuple
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {
    override fun getSettingsAsFlow(): Flow<Settings> = dataStore.data.map { pref ->
        Settings(
            amoledMode = pref[AMOLED_MODE] ?: false,
            isDarkMode = pref[IS_DARK_MODE] ?: true,
            useDynamicColor = pref[USE_DYNAMIC_COLORS] ?: true,
            bordersEnabled = pref[BORDERS_ENABLED] ?: true,
            colorTuple = pref[COLOR_TUPLE]?.let { colorTuple ->
                val colors = colorTuple.split(" ").map { Color(it.toInt()) }
                ColorTuple(
                    primary = colors[0],
                    secondary = colors.getOrNull(1),
                    tertiary = colors.getOrNull(2),
                    surface = colors.getOrNull(3)
                )
            } ?: defaultLightColorTuple,
            headlineOverflowBehaviour = when (pref[OVERFLOW_BEHAVIOUR]) {
                1 -> HeadlineOverflowBehaviour.MARQUEE
                2 -> HeadlineOverflowBehaviour.IGNORE
                else -> HeadlineOverflowBehaviour.ELLIPSIS
            }

        )
    }

    override suspend fun getSettings(): Settings {
        val pref = dataStore.data.first()
        return Settings(
            amoledMode = pref[AMOLED_MODE] ?: false,
            isDarkMode = pref[IS_DARK_MODE] ?: true,
            useDynamicColor = pref[USE_DYNAMIC_COLORS] ?: true,
            bordersEnabled = pref[BORDERS_ENABLED] ?: true,
            colorTuple = pref[COLOR_TUPLE]?.let { colorTuple ->
                val colors = colorTuple.split(" ").map { Color(it.toInt()) }
                ColorTuple(
                    colors[0],
                    colors.getOrNull(1),
                    colors.getOrNull(2),
                    colors.getOrNull(3)
                )
            } ?: defaultDarkColorTuple,
            headlineOverflowBehaviour = when (pref[OVERFLOW_BEHAVIOUR]) {
                1 -> HeadlineOverflowBehaviour.MARQUEE
                2 -> HeadlineOverflowBehaviour.IGNORE
                else -> HeadlineOverflowBehaviour.ELLIPSIS
            }
        )
    }

    override suspend fun updateAmoledMode(value: Boolean) {
        dataStore.edit {
            it[AMOLED_MODE] = !(it[AMOLED_MODE] ?: false)
        }
    }

    override suspend fun updateIsDarkMode(value: Boolean) {
        dataStore.edit {
            it[IS_DARK_MODE] = !(it[IS_DARK_MODE] ?: false)
        }
    }

    override suspend fun updateUseDynamicColors(value: Boolean) {
        dataStore.edit {
            it[USE_DYNAMIC_COLORS] = !(it[USE_DYNAMIC_COLORS] ?: true)
        }
    }

    override suspend fun updateBordersEnabled(value: Boolean) {
        dataStore.edit {
            it[BORDERS_ENABLED] = !(it[BORDERS_ENABLED] ?: true)
        }
    }

    override suspend fun updateColorTuple(value: ColorTuple) {
        dataStore.edit {
            it[COLOR_TUPLE] =
                "${value.primary} ${value.secondary} ${value.tertiary} ${value.surface}"
        }
    }

    override suspend fun updateHeadlineOverflowBehaviour(value: HeadlineOverflowBehaviour) {
        dataStore.edit {
            it[OVERFLOW_BEHAVIOUR] = when (value) {
                HeadlineOverflowBehaviour.ELLIPSIS -> 0
                HeadlineOverflowBehaviour.MARQUEE -> 1
                HeadlineOverflowBehaviour.IGNORE -> 2
            }
        }
    }

}