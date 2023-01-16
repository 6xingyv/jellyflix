

package presentation.icons.filled

import androidx.compose.ui.graphics.vector.ImageVector
import presentation.icons.Icons
import presentation.icons.fluentIcon
import presentation.icons.fluentPath

public val Icons.Filled.IosArrowLtr: ImageVector
    get() {
        if (presentation.icons.filled._iosArrowLtr != null) {
            return presentation.icons.filled._iosArrowLtr!!
        }
        presentation.icons.filled._iosArrowLtr = fluentIcon(name = "Filled.IosArrowLtr") {
            fluentPath {
                moveTo(12.73f, 3.69f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, false, -1.46f, -1.38f)
                lineToRelative(-8.5f, 9.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, false, 0.0f, 1.38f)
                lineToRelative(8.5f, 9.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, true, false, 1.46f, -1.38f)
                lineTo(4.88f, 12.0f)
                lineToRelative(7.85f, -8.31f)
                close()
            }
        }
        return presentation.icons.filled._iosArrowLtr!!
    }

private var _iosArrowLtr: ImageVector? = null
