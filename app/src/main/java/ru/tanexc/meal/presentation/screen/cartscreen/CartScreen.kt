package ru.tanexc.meal.presentation.screen.cartscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CartScreen(modifier: Modifier) {
    Box(modifier.fillMaxSize()) {
        Icon(
            imageVector = Icons.Outlined.ShoppingCart,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(56.dp)
        )
    }
}