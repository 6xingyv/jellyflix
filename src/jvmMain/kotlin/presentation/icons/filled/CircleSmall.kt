

package presentation.icons.filled

import androidx.compose.ui.graphics.vector.ImageVector
import presentation.icons.Icons
import presentation.icons.fluentIcon
import presentation.icons.fluentPath

public val Icons.Filled.CircleSmall: ImageVector
    get() {
        if (_circleSmall != null) {
            return _circleSmall!!
        }
        _circleSmall = fluentIcon(name = "Filled.CircleSmall") {
            fluentPath {
                moveTo(8.0f, 12.0f)
                arcToRelative(4.0f, 4.0f, 0.0f, true, true, 8.0f, 0.0f)
                arcToRelative(4.0f, 4.0f, 0.0f, false, true, -8.0f, 0.0f)
                close()
            }
        }
        return _circleSmall!!
    }

private var _circleSmall: ImageVector? = null
