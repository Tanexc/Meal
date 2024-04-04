package ru.tanexc.meal.presentation.activity

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.gigamole.composeshadowsplus.softlayer.softLayerShadow
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.popAll
import dev.olshevski.navigation.reimagined.rememberNavController
import ru.tanexc.meal.R
import ru.tanexc.meal.presentation.screen.cartscreen.CartScreen
import ru.tanexc.meal.presentation.screen.menuscreen.MenuScreen
import ru.tanexc.meal.presentation.screen.profilescreen.ProfileScreen
import ru.tanexc.meal.presentation.util.other.LocalSettingsProvider
import ru.tanexc.meal.presentation.util.other.Screen
import ru.tanexc.meal.presentation.util.ui.theme.MealTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealApp(
    viewModel: MainViewModel
) {
    val navController = rememberNavController(startDestination = viewModel.currentScreen)

    val settings = LocalSettingsProvider.current
    val colorScheme = settings.getColorScheme()

    val screens = listOf(
        Screen.Menu,
        Screen.Profile,
        Screen.Cart
    )

    MealTheme {
        Scaffold(
            bottomBar = {
                BottomAppBar(if (settings.bordersEnabled) {
                    Modifier.drawWithContent {
                        drawContent()
                        drawRect(
                            colorScheme.outline,
                            topLeft = Offset(0f, 0f),
                            size = Size(this.size.width, density)
                        )
                    }
                } else {
                    Modifier.softLayerShadow(spread = 2.dp, offset = DpOffset(2.dp, 0.dp))
                }) {
                    screens.forEach { screen ->
                        val title = when (screen) {
                            Screen.Menu -> stringResource(R.string.menu)
                            Screen.Cart -> stringResource(R.string.сart)
                            Screen.Profile -> stringResource(R.string.profile)
                        }
                        NavigationBarItem(
                            selected = viewModel.currentScreen == screen,
                            onClick = {
                                viewModel.updateCurrentScreen(screen)
                                navController.popAll()
                                navController.navigate(screen)
                            },
                            icon = {
                                Icon(
                                    imageVector = when (screen) {
                                        Screen.Menu -> Icons.Outlined.Fastfood
                                        Screen.Cart -> Icons.Outlined.ShoppingCart
                                        Screen.Profile -> Icons.Outlined.Person
                                    },
                                    contentDescription = title
                                )
                            },
                            label = { Text(title) },
                            alwaysShowLabel = false
                        )
                    }
                }
            }
        ) { innerPaddings ->
            NavBackHandler(navController)
            NavHost(navController) { screen ->
                when (screen) {
                    Screen.Menu -> MenuScreen(Modifier.padding(innerPaddings))
                    Screen.Cart -> CartScreen(Modifier.padding(innerPaddings))
                    Screen.Profile -> ProfileScreen(Modifier.padding(innerPaddings))
                }

            }
        }
    }
}
