package ru.bashcony.evotortest.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.bashcony.evotortest.presentation.theme.EvotorColor
import ru.bashcony.evotortest.presentation.theme.EvotorTestTheme

@Composable
fun EvotorPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(
            vertical = 16.dp,
            horizontal = 16.dp
        ),
        enabled = enabled,
        onClick = onClick,
        colors = EvotorPrimaryButton.colors(),
    ) {
        Text(text = text, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
    }
}

object EvotorPrimaryButton {
    @Composable
    fun colors() = ButtonDefaults.buttonColors(
        containerColor = EvotorColor.Primary,
        disabledContainerColor = EvotorColor.DisabledPrimary,
        contentColor = EvotorColor.PrimaryContent,
        disabledContentColor = EvotorColor.DisabledPrimaryContent,
    )
}

@Preview
@Composable
fun EvotorPrimaryButtonPreview() {
    EvotorTestTheme {
        Column {
            EvotorPrimaryButton(
                text = "Продолжить",
                enabled = true,
                onClick = {},
            )
            EvotorPrimaryButton(
                text = "Продолжить",
                enabled = false,
                onClick = {},
            )
        }
    }
}