package ru.bashcony.evotortest.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import ru.bashcony.evotortest.presentation.theme.EvotorColor

@Composable
fun EvotorScaffold(
    onNavigationUp: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        topBar = {
            EvotorTopAppBar(
                onNavigationUp = onNavigationUp,
            )
        },
        containerColor = Color.Transparent,
        content = content,
    )
}