package ru.bashcony.evotortest.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.bashcony.evotortest.presentation.theme.EvotorColor
import ru.bashcony.evotortest.presentation.theme.EvotorIcons
import ru.bashcony.evotortest.presentation.theme.EvotorTestTheme

@Composable
fun EvotorTitle(
    modifier: Modifier = Modifier,
    text: String = "",
    subtitle: String = "",
    endIcon: EvotorIcons? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = text,
                fontSize = 28.sp,
                color = EvotorColor.PrimaryContent,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    lineBreak = LineBreak.Heading,
                    lineHeight = 42.sp
                ),
            )
            if (endIcon != null) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    modifier = Modifier.size(24.dp)
                        .padding(bottom = 5.dp),
                    painter = endIcon.painter,
                    contentDescription = text,
                    tint = EvotorColor.SecondaryContent,
                )
            }
        }
        if (subtitle.isNotBlank()) {
            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = subtitle,
                color = EvotorColor.SecondaryContent,
            )
        }
    }
}

@Preview
@Composable
fun EvotorTitlePreview() {
    EvotorTestTheme {
        EvotorTitle(
            text = "art\nart",
            subtitle = "+79377808307",
            endIcon = EvotorIcons.Edit,
        )
    }
}