package ru.bashcony.evotortest.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bashcony.evotortest.R
import ru.bashcony.evotortest.presentation.theme.EvotorColor
import ru.bashcony.evotortest.presentation.theme.EvotorTestTheme

@Composable
fun EvotorCard(
    startContent: EvotorCard.StartContent,
    onClick: () -> Unit = {},
    endContent: @Composable () -> Unit = {},
    chevronEnabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(vertical = 4.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = EvotorColor.Container)
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .wrapContentHeight()
        ) {
            when (startContent) {
                is EvotorCard.StartContent.CustomComposable -> {
                    startContent.content()
                }

                is EvotorCard.StartContent.Text -> {
                    Text(
                        modifier = Modifier.padding(vertical = 5.dp),
                        text = startContent.text,
                        color = EvotorColor.Subtitle,
                    )
                }
            }
        }
        Row(modifier = Modifier.padding(vertical = 5.dp)) {
            endContent()
            if (chevronEnabled) {
                Icon(
                    painter = painterResource(R.drawable.ic_chevron_right),
                    contentDescription = "Перейти",
                    tint = EvotorColor.OnContainer,
                )
            }
        }
    }
}

object EvotorCard {
    sealed class StartContent {
        class Text(val text: String) : StartContent()
        class CustomComposable(val content: @Composable () -> Unit) : StartContent()
    }
}

@Composable
@Preview(backgroundColor = 0x00000000)
fun EvotorCardDefaultPreview() {
    EvotorTestTheme {
        EvotorCard(
            startContent = EvotorCard.StartContent.Text("Регистрация для клиентов банка"),
            chevronEnabled = true,
        )
    }
}

@Composable
@Preview(backgroundColor = 0x00000000)
fun EvotorCardCustomEndContentPreview() {
    EvotorTestTheme {
        EvotorCard(
            startContent = EvotorCard.StartContent.Text("Вход по геометрии"),
            endContent = {
                EvotorSwitch(
                    modifier = Modifier.height(IntrinsicSize.Min),
                    checked = true,
                    onCheckedChange = null,
                )
            },
            chevronEnabled = true,
        )
    }
}