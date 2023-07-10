package com.simonscholz.jellyflix.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ln

private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
)

private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    error = md_theme_dark_error,
    onError = md_theme_dark_onError,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
)

private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(2.dp),
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

private val AppTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
)

@Composable
internal fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColorScheme
    } else {
        DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}


val ColorScheme.onMica: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black

@Composable
fun ColorScheme.mica(tonalElevation: Dp = 1.dp): Color {
    val alpha = ((4.5f * ln(tonalElevation.value + 1)) + 2f) / 100f
    return (if (isSystemInDarkTheme()) Color.White else Color.Black).copy(alpha)
}

@Composable
fun ColorScheme.contentColorFor(backgroundColor: Color): Color =
    when (backgroundColor) {
        primary -> onPrimary
        secondary -> onSecondary
        tertiary -> onTertiary
        background -> onBackground
        error -> onError
        surface -> onSurface
        surfaceVariant -> onSurfaceVariant
        primaryContainer -> onPrimaryContainer
        secondaryContainer -> onSecondaryContainer
        tertiaryContainer -> onTertiaryContainer
        errorContainer -> onErrorContainer
        inverseSurface -> inverseOnSurface
        mica(1.dp) -> onMica
        else -> Color.Unspecified
    }
