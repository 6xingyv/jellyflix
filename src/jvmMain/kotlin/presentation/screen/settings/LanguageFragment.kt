package presentation.screen.settings

import R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import presentation.component.fluent.navigation.BreadcrumbBar
import presentation.utils.Fragment

class LanguageFragment : Fragment() {

    @Composable
    override fun content() {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            BreadcrumbBar(items = listOf(
                stringResource(R.strings.settings), stringResource(R.strings.settings_language)
            ), style = MaterialTheme.typography.h2, onClick = {})
        }
    }
}