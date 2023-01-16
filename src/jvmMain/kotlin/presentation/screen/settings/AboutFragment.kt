package presentation.screen.settings

import R
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import presentation.component.fluent.navigation.BreadcrumbBar
import presentation.screen.settings.component.SettingsListItem
import presentation.utils.Fragment

class AboutFragment : Fragment() {
    @Composable
    override fun content() {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            BreadcrumbBar(items = listOf(
                stringResource(R.strings.settings), stringResource(R.strings.settings_about)
            ), style = MaterialTheme.typography.h2, onClick = {})
            SettingsListItem {
                Text(text = stringResource(R.strings.settings_about_version), style = MaterialTheme.typography.body1)
                Spacer(Modifier.weight(1f))
                Text(
                    text = stringResource(R.strings.universal_app_version),
                    style = MaterialTheme.typography.body2,
                    color = LocalContentColor.current.copy(0.6f)
                )
            }
            SettingsListItem(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(R.strings.settings_about_license), style = MaterialTheme.typography.body1)
            }
            Spacer(Modifier.height(16.dp))
            Column {
                Text(
                    text = stringResource(R.strings.universal_app_name),
                    style = MaterialTheme.typography.body2,
                    color = LocalContentColor.current.copy(0.6f)
                )
                Text(
                    text = stringResource(R.strings.settings_about_copyright),
                    style = MaterialTheme.typography.body2,
                    color = LocalContentColor.current.copy(0.6f)
                )
            }
        }
    }
}