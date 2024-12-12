package ru.bashcony.evotortest.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.bashcony.evotortest.presentation.theme.EvotorColor
import ru.bashcony.evotortest.presentation.theme.EvotorIcons
import ru.bashcony.evotortest.presentation.theme.EvotorTestTheme

@Composable
fun EvotorSecondaryButton(
    modifier: Modifier = Modifier,
    text: String = "",
    icon: EvotorIcons? = null,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        EvotorColor.ButtonOutStrokeGradientStart,
                        EvotorColor.ButtonOutStrokeGradientEnd,
                    ),
                ),
                shape = CircleShape,
            )
            .padding(1.dp)
            .background(
                color = EvotorColor.ButtonMiddleStroke,
                shape = CircleShape,
            )
            .padding(1.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        EvotorColor.ButtonInnerStrokeGradientStart,
                        EvotorColor.ButtonInnerStrokeGradientEnd,
                    ),
                ),
                shape = CircleShape,
            )
            .padding(1.dp)
            .background(
                color = EvotorColor.ButtonBackground,
                shape = CircleShape,
            )
            .padding(9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = icon.painter,
                contentDescription = text,
                tint = EvotorColor.PrimaryContent,
            )
            Spacer(modifier = Modifier.width(2.dp))
        }
        Text(
            text = text,
            color = EvotorColor.PrimaryContent,
            fontSize = 14.sp
        )
    }
}

@Composable
@Preview
fun EvotorSecondaryButtonPreview() {
    EvotorTestTheme {
        Column(
            modifier = Modifier.background(
                Brush.linearGradient(
                    colors = listOf(
                        EvotorColor.BackgroundGradientStart,
                        EvotorColor.BackgroundGradientEnd,
                    ),
                ),
            ),
        ) {
            EvotorSecondaryButton(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Простая кнопка",
            )

            EvotorSecondaryButton(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                icon = EvotorIcons.Back,
                text = "Назад",
            )
        }
    }
}