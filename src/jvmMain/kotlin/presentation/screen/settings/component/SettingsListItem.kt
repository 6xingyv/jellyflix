package presentation.screen.settings.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.component.fluent.collections.ListItem

@Composable
fun SettingsListItem(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    val hovered by interactionSource.collectIsHoveredAsState()
    val pressed by interactionSource.collectIsPressedAsState()

    val shape = RoundedCornerShape(4.dp)
    val backgroundColor = when {
        hovered || pressed -> MaterialTheme.colors.surface.copy(0.1f)
//            focused -> MaterialTheme.colors.surface.copy(0.1f)
        else -> MaterialTheme.colors.surface
    }
    val surfaceStrokeColor = MaterialTheme.colors.surface.copy(0.05f)
    val surfaceStroke = BorderStroke(width = 1.dp, color = surfaceStrokeColor)
    Surface(
        color = animateColorAsState(backgroundColor).value,
        contentColor = MaterialTheme.colors.onSurface,
        shape = shape,
        border = surfaceStroke
    ) {
        ListItem(modifier = modifier, padding = 8.dp) {
            content()
        }
    }
}