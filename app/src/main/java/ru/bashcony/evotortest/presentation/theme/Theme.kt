package ru.bashcony.evotortest.presentation.theme

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalView

@Composable
fun EvotorTestTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = darkColorScheme(),
        typography = Typography,
    ) {
        Box(
            modifier = Modifier.background(
                Brush.linearGradient(
                    colors = listOf(
                        EvotorColor.BackgroundGradientStart,
                        EvotorColor.BackgroundGradientEnd,
                    ),
                ),
            )
        ) {
            content()
        }
    }
}