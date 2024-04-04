package ru.tanexc.meal.presentation.util.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.t8rin.dynamic.theme.DynamicTheme
import com.t8rin.dynamic.theme.getAppColorTuple
import com.t8rin.dynamic.theme.rememberDynamicThemeState
import ru.tanexc.meal.presentation.util.other.LocalSettingsProvider

private val LightColors = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    errorContainer = errorContainerLight,
    onError = onErrorLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inverseSurface = inverseSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceTint = surfaceBrightLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
)


private val DarkColors = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    errorContainer = errorContainerDark,
    onError = onErrorDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inverseSurface = inverseSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceTint = surfaceBrightDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
)

@Composable
fun MealTheme(
    content: @Composable () -> Unit
) {
    val settings = LocalSettingsProvider.current

    DynamicTheme(
        state = rememberDynamicThemeState(
            getAppColorTuple(
                defaultColorTuple = settings.colorTuple,
                dynamicColor = settings.useDynamicColor,
                darkTheme = settings.isDarkMode,
            )
        ),
        defaultColorTuple = settings.colorTuple,
        isDarkTheme = settings.isDarkMode,
        dynamicColor = settings.useDynamicColor,
        amoledMode = settings.amoledMode
    ) {
        content()
    }
}