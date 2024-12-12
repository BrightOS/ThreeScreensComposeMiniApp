package ru.bashcony.evotortest.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bashcony.evotortest.presentation.theme.EvotorColor
import ru.bashcony.evotortest.presentation.theme.EvotorTestTheme

@Composable
fun EvotorSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Switch(
        modifier = modifier.padding(0.dp),
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedTrackColor = EvotorColor.Primary,
            checkedThumbColor = EvotorColor.PrimaryContent,
        )
    )
}

@Preview
@Composable
fun EvotorSwitchCheckedPreview() {
    EvotorTestTheme {
        EvotorSwitch(
            checked = true,
            onCheckedChange = {},
        )
    }
}

@Preview
@Composable
fun EvotorSwitchUncheckedPreview() {
    EvotorTestTheme {
        EvotorSwitch(
            checked = false,
            onCheckedChange = {},
        )
    }
}
