@file:OptIn(ExperimentalMaterial3Api::class)

package ru.bashcony.evotortest.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bashcony.evotortest.presentation.theme.EvotorIcons
import ru.bashcony.evotortest.presentation.theme.EvotorTestTheme

@Composable
fun EvotorTopAppBar(
    onNavigationUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    EvotorSecondaryButton(
        modifier = modifier.padding(start = 15.dp, top = 5.dp, bottom = 5.dp),
        text = "Назад",
        icon = EvotorIcons.Back,
        onClick = onNavigationUp,
    )
}

@Composable
@Preview(backgroundColor = 0xFF3c3753)
fun EvotorTopAppBarPreview() {
    EvotorTestTheme {
        EvotorTopAppBar({})
    }
}