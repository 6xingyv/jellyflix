

package presentation.icons.filled

import androidx.compose.ui.graphics.vector.ImageVector
import presentation.icons.Icons
import presentation.icons.fluentIcon
import presentation.icons.fluentPath

public val Icons.Filled.ArrowUpLeft: ImageVector
    get() {
        if (_arrowUpLeft != null) {
            return _arrowUpLeft!!
        }
        _arrowUpLeft = fluentIcon(name = "Filled.ArrowUpLeft") {
            fluentPath {
                moveTo(13.0f, 3.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, true, true, 0.0f, 2.0f)
                horizontalLineTo(6.41f)
                lineToRelative(14.3f, 14.3f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, -1.42f, 1.4f)
                lineTo(5.0f, 6.42f)
                verticalLineTo(13.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, true, true, -2.0f, 0.0f)
                verticalLineTo(4.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.0f, -1.0f)
                horizontalLineToRelative(9.0f)
                close()
            }
        }
        return _arrowUpLeft!!
    }

private var _arrowUpLeft: ImageVector? = null
