package ru.bashcony.evotortest.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import ru.bashcony.evotortest.R

@Stable
sealed class EvotorIcons(private val resId: Int) {

    object Back : EvotorIcons(R.drawable.ic_arrow_back)
    object Edit : EvotorIcons(R.drawable.ic_edit)

    val painter: Painter
        @Composable get() = painterResource(resId)
}