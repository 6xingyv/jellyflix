package com.simonscholz.jellyflix.utils.window

import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.WindowScope
import com.simonscholz.jellyflix.utils.window.jna.windows.extendToClientArea

@Suppress("FunctionName")
@Composable
fun WindowScope.TitleBar(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    extendToClientArea()
    WindowDraggableArea(modifier, content)
}

