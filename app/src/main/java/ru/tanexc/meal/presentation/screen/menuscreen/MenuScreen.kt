package ru.tanexc.meal.presentation.screen.menuscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import kotlinx.coroutines.launch
import ru.tanexc.meal.presentation.util.other.LocalSettingsProvider
import ru.tanexc.meal.core.util.State
import ru.tanexc.meal.presentation.util.other.isScrollingUp
import ru.tanexc.meal.presentation.util.widgets.Chip
import ru.tanexc.meal.presentation.util.widgets.MealCard

@Composable
fun MenuScreen(modifier: Modifier) {
    val context = LocalContext.current
    val viewModel: MenuViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()
    val settings = LocalSettingsProvider.current
    val colorScheme = settings.getColorScheme()
    val scrollState = rememberLazyListState()
    val selectedCategory = remember { mutableIntStateOf(-1) }
    val toolbarProgress = remember { mutableFloatStateOf(0f) }
    val page = remember { mutableIntStateOf(1) }



    val nestedScrollConnection = object : NestedScrollConnection {

        override fun onPostScroll(
            consumed: Offset,
            available: Offset,
            source: NestedScrollSource
        ): Offset {

            if (scrollState.firstVisibleItemIndex == 0) {
                toolbarProgress.floatValue =
                    (scrollState.firstVisibleItemScrollOffset / 100f).coerceIn(0f, 1f)
            } else {
                toolbarProgress.floatValue = 1f
            }
            return Offset.Zero
        }

    }

    LaunchedEffect(null) {
        viewModel.init()
    }

    Box(modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection)
        ) {
            Spacer(Modifier.size(64.dp))
            when (viewModel.loadingState) {
                is State.Success -> LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 8.dp, end = 8.dp),
                    state = scrollState,
                ) {
                    item {
                        Spacer(modifier = Modifier.size(104.dp))
                    }
                    items(viewModel.meals) { meal ->
                        MealCard(
                            title = meal.title ?: "",
                            imageURL = meal.thumb ?: "",
                            description = meal.instructions ?: "",
                            modifier = Modifier
                                .background(
                                    if (settings.isDarkMode) colorScheme.secondary.copy(0.2f)
                                    else if (!settings.bordersEnabled) colorScheme.secondary.copy(
                                        0.1f
                                    )
                                    else Color.Transparent
                                ),
                            borderEnabled = settings.bordersEnabled,
                            borderColor = colorScheme.outline
                        )
                    }

                    item {
                        LaunchedEffect(true) {
                            if (selectedCategory.intValue == -1 && scrollState.firstVisibleItemIndex != 1) {
                                page.intValue++
                                viewModel.getMealPage(page.intValue)
                            }
                        }

                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(8.dp)) {
                            when (viewModel.pageLoadingState) {
                                is State.Loading -> CircularProgressIndicator(
                                    Modifier.align(
                                        Alignment.Center
                                    )
                                )

                                else -> {}
                            }
                        }


                    }
                }

                is State.Loading -> Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        Modifier
                            .align(Alignment.Center)
                    )
                }

                is State.Error -> Box(Modifier.fillMaxSize()) {
                    Icon(
                        imageVector = Icons.Outlined.Error,
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.Center)
                    )
                }

                else -> {}
            }
        }

        AnimatedVisibility(
            visible = scrollState.isScrollingUp() && remember { derivedStateOf { scrollState.firstVisibleItemIndex } }.value != 0,
            modifier = Modifier.align(Alignment.BottomEnd),
            enter = slideInVertically { it },
            exit = slideOutVertically { it }
        ) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(0)
                    }
                },
                containerColor = colorScheme.secondaryContainer,
                contentColor = contentColorFor(backgroundColor = colorScheme.secondaryContainer),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .height(56.dp)
            ) {
                Icon(
                    Icons.Outlined.ArrowUpward,
                    contentDescription = null,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }

        Column {
            MenuTopAppBar(scrollState = scrollState) {
                when (viewModel.categoryLoading) {
                    is State.Success -> LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        item {
                            Spacer(Modifier.size(4.dp))
                        }
                        items(items = viewModel.category) { category ->
                            Chip(
                                modifier = Modifier
                                    .padding(4.dp),
                                borderEnabled = settings.bordersEnabled,
                                borderColor = colorScheme.outline,
                                shape = RoundedCornerShape(8.dp),
                                isSelected = selectedCategory.intValue == category.id,
                                selectedBackground = colorScheme.secondaryContainer,
                                background = colorScheme.secondaryContainer.copy(0.3f),
                                onClick = {
                                    if (selectedCategory.intValue == category.id) {
                                        selectedCategory.intValue = -1
                                        viewModel.mealInit()
                                    } else {
                                        selectedCategory.intValue = category.id
                                        viewModel.getMealByCategory(category.title)
                                    }

                                }) {
                                Text(text = category.title)
                            }
                        }
                        item {
                            Spacer(Modifier.size(4.dp))
                        }
                    }

                    else -> {
                        Box(Modifier.height(32.dp)) {
                            LinearProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                                    .align(Alignment.Center)
                                    .clip(RoundedCornerShape(50))
                            )
                        }

                    }
                }
            }
        }

    }
}