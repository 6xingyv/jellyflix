

package presentation.icons.regular

import androidx.compose.ui.graphics.vector.ImageVector
import presentation.icons.Icons
import presentation.icons.fluentIcon
import presentation.icons.fluentPath

public val Icons.Regular.ReOrderDotsHorizontal: ImageVector
    get() {
        if (presentation.icons.regular._reOrderDotsHorizontal != null) {
            return presentation.icons.regular._reOrderDotsHorizontal!!
        }
        presentation.icons.regular._reOrderDotsHorizontal = fluentIcon(name = "Regular.ReOrderDotsHorizontal") {
            fluentPath {
                moveTo(7.0f, 15.5f)
                arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, true, 3.0f, 0.0f)
                close()
                moveTo(7.0f, 8.5f)
                arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, true, 3.0f, 0.0f)
                close()
                moveTo(14.0f, 15.5f)
                arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, true, 3.0f, 0.0f)
                close()
                moveTo(14.0f, 8.5f)
                arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, true, 3.0f, 0.0f)
                close()
                moveTo(21.0f, 15.5f)
                arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, true, 3.0f, 0.0f)
                close()
                moveTo(21.0f, 8.5f)
                arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, true, 3.0f, 0.0f)
                close()
            }
        }
        return presentation.icons.regular._reOrderDotsHorizontal!!
    }

private var _reOrderDotsHorizontal: ImageVector? = null
