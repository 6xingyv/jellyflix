

package presentation.icons.filled

import androidx.compose.ui.graphics.vector.ImageVector
import presentation.icons.Icons
import presentation.icons.fluentIcon
import presentation.icons.fluentPath

public val Icons.Filled.Home: ImageVector
    get() {
        if (presentation.icons.filled._home != null) {
            return presentation.icons.filled._home!!
        }
        presentation.icons.filled._home = fluentIcon(name = "Filled.Home") {
            fluentPath {
                moveTo(10.55f, 2.53f)
                curveToRelative(0.84f, -0.7f, 2.06f, -0.7f, 2.9f, 0.0f)
                lineToRelative(6.75f, 5.7f)
                curveToRelative(0.5f, 0.43f, 0.8f, 1.05f, 0.8f, 1.72f)
                verticalLineToRelative(9.8f)
                curveToRelative(0.0f, 0.97f, -0.78f, 1.75f, -1.75f, 1.75f)
                horizontalLineToRelative(-3.0f)
                curveToRelative(-0.97f, 0.0f, -1.75f, -0.78f, -1.75f, -1.75f)
                verticalLineToRelative(-5.0f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, false, -0.75f, -0.75f)
                horizontalLineToRelative(-3.5f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, false, -0.75f, 0.75f)
                verticalLineToRelative(5.0f)
                curveToRelative(0.0f, 0.97f, -0.78f, 1.75f, -1.75f, 1.75f)
                horizontalLineToRelative(-3.0f)
                curveToRelative(-0.97f, 0.0f, -1.75f, -0.78f, -1.75f, -1.75f)
                verticalLineToRelative(-9.8f)
                curveToRelative(0.0f, -0.67f, 0.3f, -1.3f, 0.8f, -1.72f)
                lineToRelative(6.75f, -5.7f)
                close()
            }
        }
        return presentation.icons.filled._home!!
    }

private var _home: ImageVector? = null
