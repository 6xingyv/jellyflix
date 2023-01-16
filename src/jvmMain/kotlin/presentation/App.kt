package presentation

import data.repository.JellyfinRepository
import data.source.remote.JellyfinApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class App : KoinComponent {
    val jellyfinApi by inject<JellyfinApi>()
    val jellyfinRepository by inject<JellyfinRepository>()
}

//@Composable
//fun MenuButton(
//    isIcon: Boolean = false,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    onClick: () -> Unit = {},
//    content: @Composable RowScope.() -> Unit
//) {
//    val hovered by interactionSource.collectIsHoveredAsState()
//    val pressed by interactionSource.collectIsPressedAsState()
//    val focused by interactionSource.collectIsFocusedAsState()
//    val shape = RoundedCornerShape(4.dp)
//    val backgroundColor = when {
//        hovered -> MaterialTheme.colors.surface
//        pressed -> MaterialTheme.colors.surface.copy(1f)
//        focused -> MaterialTheme.colors.surface.copy(0.1f)
//        else -> Color.Transparent
//    }
//    val strokeColor = when {
//        focused -> MaterialTheme.colors.onSurface
//        else -> Color.Transparent
//    }
//    val padding = if (isIcon) 4.dp else 8.dp
//    Row(
//        modifier = Modifier.clip(shape)
//            .hoverable(interactionSource)
//            .clickable(
//                onClick = onClick,
//                interactionSource = interactionSource,
//                indication = null
//            )
//            .focusable(true,interactionSource)
//            .background(animateColorAsState(backgroundColor).value)
//            .border(BorderStroke(2.dp, animateColorAsState(strokeColor).value), shape)
//            .padding(padding)
//    ) {
//        content()
//    }
//}