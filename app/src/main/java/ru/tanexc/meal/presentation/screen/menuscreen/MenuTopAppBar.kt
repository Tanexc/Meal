package ru.tanexc.meal.presentation.screen.menuscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import ru.tanexc.meal.R

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MenuTopAppBar(
    scrollState: LazyListState,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current

    val motionString = remember {
        context.resources.openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }
    val toolbarProgress = remember { mutableFloatStateOf(0f) }

    LaunchedEffect(scrollState.firstVisibleItemScrollOffset) {
        if (scrollState.firstVisibleItemIndex == 0) {
            toolbarProgress.floatValue =
                (scrollState.firstVisibleItemScrollOffset / 100f).coerceIn(0f, 1f)
        } else {
            toolbarProgress.floatValue = 1f
        }
    }

    MotionLayout(
        motionScene = MotionScene(content = motionString),
        modifier = Modifier
            .height(148.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        progress = toolbarProgress.floatValue
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId("box")
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId("imagesrow")
        ) {
            LazyRow(
                modifier = Modifier
                    .height(96.dp)
                    .fillMaxWidth()
            ) {
                item {
                    Image(
                        painter = painterResource(R.drawable.image1),
                        contentDescription = null,
                        modifier = Modifier
                            .width(180.dp)
                            .height(96.dp)
                            .padding(start = 8.dp, end = 4.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                item {
                    Box(
                        Modifier
                            .width(180.dp)
                            .height(96.dp)
                            .padding(start = 4.dp, end = 4.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Image(
                            painter = painterResource(R.drawable.image2),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                item {
                    Box(
                        Modifier
                            .width(180.dp)
                            .height(96.dp)
                            .padding(start = 4.dp, end = 4.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Image(
                            painter = painterResource(R.drawable.image1),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                item {
                    Box(
                        Modifier
                            .width(180.dp)
                            .height(96.dp)
                            .padding(start = 4.dp, end = 8.dp)
                            .clip(RoundedCornerShape(16.dp)),
                    ) {
                        Image(
                            painter = painterResource(R.drawable.image2),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                }
            }
        }


        Box(
            Modifier
                .fillMaxWidth()
                .layoutId("content")
        ) {
            content()
        }
    }
}