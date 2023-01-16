

package presentation.icons.filled

import androidx.compose.ui.graphics.vector.ImageVector
import presentation.icons.Icons
import presentation.icons.fluentIcon
import presentation.icons.fluentPath

public val Icons.Filled.ArrowSquareDown: ImageVector
    get() {
        if (_arrowSquareDown != null) {
            return _arrowSquareDown!!
        }
        _arrowSquareDown = fluentIcon(name = "Filled.ArrowSquareDown") {
            fluentPath {
                moveTo(5.5f, 3.0f)
                arcTo(2.5f, 2.5f, 0.0f, false, false, 3.0f, 5.5f)
                verticalLineToRelative(13.0f)
                arcTo(2.5f, 2.5f, 0.0f, false, false, 5.5f, 21.0f)
                horizontalLineToRelative(13.0f)
                arcToRelative(2.5f, 2.5f, 0.0f, false, false, 2.5f, -2.5f)
                verticalLineToRelative(-13.0f)
                arcTo(2.5f, 2.5f, 0.0f, false, false, 18.5f, 3.0f)
                horizontalLineToRelative(-13.0f)
                close()
                moveTo(16.53f, 11.72f)
                curveToRelative(0.27f, 0.27f, 0.3f, 0.68f, 0.07f, 0.98f)
                lineToRelative(-0.07f, 0.08f)
                lineToRelative(-4.0f, 4.0f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, true, -0.98f, 0.07f)
                lineToRelative(-0.08f, -0.07f)
                lineToRelative(-4.0f, -4.0f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, true, 0.98f, -1.13f)
                lineToRelative(0.08f, 0.07f)
                lineToRelative(2.72f, 2.72f)
                lineTo(11.25f, 7.75f)
                curveToRelative(0.0f, -0.38f, 0.28f, -0.7f, 0.65f, -0.74f)
                lineTo(12.0f, 7.0f)
                curveToRelative(0.38f, 0.0f, 0.7f, 0.28f, 0.74f, 0.65f)
                lineToRelative(0.01f, 0.1f)
                verticalLineToRelative(6.69f)
                lineToRelative(2.72f, -2.72f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, true, 0.98f, -0.07f)
                lineToRelative(0.08f, 0.07f)
                close()
            }
        }
        return _arrowSquareDown!!
    }

private var _arrowSquareDown: ImageVector? = null
