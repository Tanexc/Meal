package ru.tanexc.meal.presentation.util.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.tanexc.meal.presentation.util.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealCard(
    title: String,
    imageURL: String,
    borderEnabled: Boolean,
    borderColor: Color = Color.Transparent,
    description: String,
    modifier: Modifier
) {
    Row(
        Modifier
            .padding(top = 4.dp, bottom = 4.dp)
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .border(
                1.dp,
                borderColor.copy(if (borderEnabled) 0.7f else 0f),
                RoundedCornerShape(16.dp)
            )
            .height(156.dp)
            .then(modifier)
    ) {
        Picture(
            modifier = Modifier
                .fillMaxHeight()
                .width(156.dp)
                .clip(RoundedCornerShape(16.dp)),
            imageURL = imageURL,
            contentDescription = title
        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 16.dp, bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .basicMarquee()
            ) {
                Spacer(Modifier.size(16.dp))
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontStyle = Typography.headlineLarge.fontStyle,
                    textAlign = TextAlign.Start
                )
            }

            Text(
                text = description,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}