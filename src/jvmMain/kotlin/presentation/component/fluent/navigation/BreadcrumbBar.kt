package presentation.component.fluent.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import presentation.component.fluent.collections.ListItem
import presentation.icons.Icons
import presentation.icons.regular.ChevronRight

@Composable
fun BreadcrumbBar(items: List<String>, style: TextStyle = LocalTextStyle.current, onClick: (index: Int) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(items.size) {
            val index = it
            val item = items[index]
            ListItem(onClick = { onClick(index) }, padding = 8.dp) {
                val textColor =
                    if (index == items.size - 1) LocalContentColor.current else LocalContentColor.current.copy(0.6f)
                Text(text = item, style = style, color = textColor)
            }
            if (index != items.size - 1) {
                Icon(Icons.Regular.ChevronRight, Icons.Regular.ChevronRight.name)
            }
        }
    }
}