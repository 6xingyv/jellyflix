package presentation.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Teal200 = Color(0xFF03DAC5)

val Green800 = Color(0xFF003626)
val Green600 = Color(0xFF00825D)
val Green400 = Color(0xFF00D69A)
val Green200 = Color(0xFF62F5CB)


val DarkColorPalette = darkColors(
    primary = Green600,
    primaryVariant = Green800,
    secondary = Teal200,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.White.copy(0.05f)
)

val LightColorPalette = lightColors(
    primary = Green600,
    primaryVariant = Green800,
    secondary = Teal200,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.Black.copy(0.05f)
)