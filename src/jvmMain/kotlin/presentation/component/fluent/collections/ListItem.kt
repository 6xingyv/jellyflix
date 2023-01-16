package presentation.component.fluent.collections

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: () -> Unit = {},
    padding: Dp,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    val hovered by interactionSource.collectIsHoveredAsState()
    val pressed by interactionSource.collectIsPressedAsState()
//        val focused by interactionSource.collectIsFocusedAsState()
    val shape = RoundedCornerShape(4.dp)
    val backgroundColor = when {
        hovered || pressed || selected -> MaterialTheme.colors.surface
//            focused -> MaterialTheme.colors.surface.copy(0.1f)
        else -> Color.Transparent
    }
    val strokeColor = MaterialTheme.colors.primary
    val strokeAlpha = when {
//            focused -> MaterialTheme.colors.onSurface
        selected -> 1f
        else -> 0f
    }
    val animatedStrokeAlpha = animateFloatAsState(strokeAlpha)
    Row(
        modifier = modifier
            .clip(shape)
            .background(animateColorAsState(backgroundColor).value)
//            .border(BorderStroke(2.dp, animateColorAsState(strokeColor).value), shape)
            .hoverable(interactionSource)
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            )
            .focusable(true, interactionSource)
            .drawWithContent {
                drawContent()
                val height = this.size.height

                val strokeLength = height / 2
                val strokeStartY = strokeLength - (strokeLength / 2)
                val strokeEndY = strokeLength + (strokeLength / 2)

                drawLine(
                    start = Offset(x = 2f, y = strokeStartY),
                    end = Offset(x = 2f, y = strokeEndY),
                    color = strokeColor,
                    alpha = animatedStrokeAlpha.value,
                    strokeWidth = 4f,
                    cap = StrokeCap.Round
                )
            }
            .padding(padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        content()
    }
}