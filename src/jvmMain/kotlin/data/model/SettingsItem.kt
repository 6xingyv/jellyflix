package data.model

import androidx.compose.ui.graphics.vector.ImageVector
import dev.icerock.moko.resources.StringResource
import presentation.icons.Icons
import presentation.icons.regular.Circle
import presentation.utils.Fragment

data class SettingsItem(
    val icon: ImageVector = Icons.Regular.Circle,
    val label: StringResource,
    val description: StringResource,
    val fragment: Fragment? = null,
    val onClick: () -> Unit = {}
)