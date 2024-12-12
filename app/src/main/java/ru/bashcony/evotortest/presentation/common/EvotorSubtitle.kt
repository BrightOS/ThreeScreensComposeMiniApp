package ru.bashcony.evotortest.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.bashcony.evotortest.presentation.theme.EvotorColor

@Composable
fun EvotorSubtitle(
    modifier: Modifier = Modifier,
    text: String = "",
) {
    Text(
        modifier = modifier.padding(top = 20.dp, bottom = 5.dp),
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        text = text.uppercase(),
        color = EvotorColor.SecondaryContent,
    )
}