package ru.bashcony.evotortest.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ru.bashcony.evotortest.R

object EvotorFont {
    val Nunito = FontFamily(
        Font(R.font.nunito_light, FontWeight.Light),
        Font(R.font.nunito_medium),
        Font(R.font.nunito_semibold, FontWeight.SemiBold),
        Font(R.font.nunito_bold, FontWeight.Bold),
        Font(R.font.nunito_extrabold, FontWeight.ExtraBold),
        Font(R.font.nunito_black, FontWeight.Black),
    )
}

private val defaultTypography = Typography()

val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = EvotorFont.Nunito),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = EvotorFont.Nunito),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = EvotorFont.Nunito),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = EvotorFont.Nunito),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = EvotorFont.Nunito),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = EvotorFont.Nunito),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = EvotorFont.Nunito),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = EvotorFont.Nunito),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = EvotorFont.Nunito),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = EvotorFont.Nunito),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = EvotorFont.Nunito),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = EvotorFont.Nunito),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = EvotorFont.Nunito),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = EvotorFont.Nunito),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = EvotorFont.Nunito),
)