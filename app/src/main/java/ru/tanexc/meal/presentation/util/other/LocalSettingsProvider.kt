package ru.tanexc.meal.presentation.util.other

import androidx.compose.runtime.compositionLocalOf

val LocalSettingsProvider = compositionLocalOf<Settings> { error("Settings not presented") }