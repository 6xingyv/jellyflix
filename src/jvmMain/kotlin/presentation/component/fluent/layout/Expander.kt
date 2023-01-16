package presentation.component.fluent.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.component.fluent.navigation.MenuItem
import presentation.icons.Icons
import presentation.icons.regular.ChevronDown
import presentation.icons.regular.ChevronUp

@Composable
fun Expander(
    expanded: Boolean = false,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    header: @Composable RowScope.() -> Unit,
    content: @Composable () -> Unit
) {
    var _expanded by remember { mutableStateOf(expanded) }
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
        Column(modifier) {
            Row(
                modifier = Modifier.clickable(
                    onClick = {
                        _expanded = !_expanded
                        onClick()
                    },
                    interactionSource = interactionSource,
                    indication = null
                ).padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                header()
                Spacer(Modifier.weight(1f))
                MenuItem(
                    icon = if (_expanded) Icons.Regular.ChevronUp else Icons.Regular.ChevronDown,
                    onClick = {
                        _expanded = !_expanded
                        onClick()
                    }) {}
            }
            AnimatedVisibility(_expanded) {
                Spacer(Modifier.fillMaxWidth().height(1.dp).background(surfaceStrokeColor).border(surfaceStroke))
                content()
            }
        }
    }
}