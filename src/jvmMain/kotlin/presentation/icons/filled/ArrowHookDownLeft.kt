

package presentation.icons.filled

import androidx.compose.ui.graphics.vector.ImageVector
import presentation.icons.Icons
import presentation.icons.fluentIcon
import presentation.icons.fluentPath

public val Icons.Filled.ArrowHookDownLeft: ImageVector
    get() {
        if (presentation.icons.filled._arrowHookDownLeft != null) {
            return presentation.icons.filled._arrowHookDownLeft!!
        }
        presentation.icons.filled._arrowHookDownLeft = fluentIcon(name = "Filled.ArrowHookDownLeft") {
            fluentPath {
                moveTo(7.0f, 5.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.0f, -1.0f)
                horizontalLineToRelative(5.0f)
                curveToRelative(2.24f, 0.0f, 4.01f, 0.78f, 5.22f, 2.02f)
                arcTo(6.42f, 6.42f, 0.0f, false, true, 20.0f, 10.5f)
                curveToRelative(0.0f, 1.61f, -0.59f, 3.24f, -1.78f, 4.48f)
                arcTo(7.06f, 7.06f, 0.0f, false, true, 13.0f, 17.0f)
                horizontalLineTo(8.41f)
                lineToRelative(2.05f, 2.04f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, -1.42f, 1.42f)
                lineTo(5.3f, 16.7f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 0.0f, -1.42f)
                lineToRelative(3.75f, -3.75f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.42f, 1.42f)
                lineTo(8.4f, 15.0f)
                horizontalLineTo(13.0f)
                curveToRelative(1.76f, 0.0f, 2.99f, -0.6f, 3.78f, -1.41f)
                arcTo(4.42f, 4.42f, 0.0f, false, false, 18.0f, 10.5f)
                curveToRelative(0.0f, -1.14f, -0.41f, -2.26f, -1.22f, -3.09f)
                arcTo(5.07f, 5.07f, 0.0f, false, false, 13.0f, 6.0f)
                horizontalLineTo(8.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, -1.0f, -1.0f)
                close()
            }
        }
        return presentation.icons.filled._arrowHookDownLeft!!
    }

private var _arrowHookDownLeft: ImageVector? = null
