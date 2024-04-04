package ru.tanexc.meal.presentation.util.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    modifier: Modifier,
    borderEnabled: Boolean,
    borderColor: Color = Color.Transparent,
    background: Color,
    selectedBackground: Color,
    isSelected: Boolean,
    shape: Shape,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .height(32.dp)
            .wrapContentWidth()
            .clip(shape)
            .border(1.dp, borderColor.copy(if (borderEnabled) 0.7f else 0f), shape)
            .clickable { onClick() }
            .background(if (isSelected) selectedBackground else background),
        contentAlignment = Alignment.Center,
    ) {
        Row {
            Spacer(Modifier.size(8.dp))
            if (isSelected) {
                Icon(
                    Icons.Outlined.Check,
                    null,
                    Modifier
                        .size(18.dp)
                        .align(Alignment.CenterVertically)
                )
            }
            Spacer(Modifier.size(8.dp))
            content()
            Spacer(Modifier.size(16.dp))
        }

    }
}