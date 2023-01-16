package presentation.component.fluent.navigation

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import presentation.component.fluent.collections.ListItem

@Composable
fun MenuItem(
    icon: ImageVector? = null,
    label: String = "",
    style :TextStyle = MaterialTheme.typography.body2,
    modifier: Modifier = Modifier,
    compact: Boolean = false,
    selected: Boolean = false,
    onClick: () -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    ListItem(
        modifier = if (!compact)  modifier else Modifier,
        selected = selected,
        onClick = onClick,
        interactionSource = interactionSource,
        padding = 8.dp
    ) {
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = icon.name)
        }
        if (label.isNotEmpty() && !compact) {
            Text(text = label, style = style)
        }
    }
}

@Composable
fun MenuItem(
    icon: ImageVector? = null,
    modifier: Modifier = Modifier,
    compact: Boolean = false,
    selected: Boolean = false,
    onClick: () -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    label: @Composable ()->Unit = {},
) {
    ListItem(
        modifier = if (!compact)  modifier else Modifier,
        selected = selected,
        onClick = onClick,
        interactionSource = interactionSource,
        padding = 8.dp
    ) {
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = icon.name)
        }
        if (!compact) {
            label()
        }
    }
}


