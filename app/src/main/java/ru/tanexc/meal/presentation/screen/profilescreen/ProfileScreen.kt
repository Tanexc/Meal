package ru.tanexc.meal.presentation.screen.profilescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import ru.tanexc.meal.presentation.util.other.LocalSettingsProvider
import ru.tanexc.meal.R
import ru.tanexc.meal.presentation.util.ui.firstPreferenceShape
import ru.tanexc.meal.presentation.util.ui.lastPreferenceShape
import ru.tanexc.meal.presentation.util.ui.theme.Typography
import ru.tanexc.meal.presentation.util.widgets.PreferenceCard

@Composable
fun ProfileScreen(
    modifier: Modifier
) {
    val settings = LocalSettingsProvider.current
    val colorScheme = settings.getColorScheme()
    val viewModel: ProfileViewModel = hiltViewModel()

    LazyColumn(modifier, contentPadding = PaddingValues(22.dp)) {
        item {
            PreferenceCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.secondaryContainer),
                borderEnabled = settings.bordersEnabled,
                borderColor = colorScheme.outline,
                shape = firstPreferenceShape(),
                header = {
                    Icon(
                        imageVector = Icons.Filled.Style,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterVertically)

                    )
                    Spacer(modifier = Modifier.size(22.dp))
                    Text(
                        text = stringResource(R.string.style),
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        style = Typography.bodyLarge.copy(fontWeight = FontWeight.ExtraBold)
                    )
                }
            ) {
                Row {
                    Text(
                        stringResource(R.string.amoled_mode),
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                    Switch(
                        checked = settings.amoledMode,
                        onCheckedChange = { viewModel.updateAmoledMode(!settings.amoledMode) },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(colorScheme.outline.copy(0.2f), CircleShape)
                )

                Row {
                    Text(
                        stringResource(R.string.dynamic_colors),
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                    Switch(
                        checked = settings.useDynamicColor,
                        onCheckedChange = { viewModel.updateUseDynamicColors(!settings.useDynamicColor) },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(colorScheme.outline.copy(0.2f), CircleShape)
                )

                Row {
                    Text(
                        text = stringResource(R.string.use_dark_mode), modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                    Switch(
                        checked = settings.isDarkMode,
                        onCheckedChange = { viewModel.updateUseDarkMode(!settings.isDarkMode) },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(colorScheme.outline.copy(0.2f), CircleShape)
                )

                Row {
                    Text(
                        stringResource(R.string.outline),
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                    Switch(
                        checked = settings.bordersEnabled,
                        onCheckedChange = { viewModel.updateUseBorders(!settings.bordersEnabled) },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(colorScheme.outline.copy(0.2f), CircleShape)
                )

            }
            Spacer(modifier = Modifier.size(4.dp))
        }
        item {
            PreferenceCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.secondaryContainer),
                borderEnabled = settings.bordersEnabled,
                borderColor = colorScheme.outline,
                shape = lastPreferenceShape(),
                header = {
                    Icon(
                        imageVector = Icons.Outlined.Code,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterVertically)

                    )
                    Spacer(modifier = Modifier.size(22.dp))
                    Text(
                        stringResource(R.string.about_app),
                        Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                }
            ) {

            }
        }
    }
}