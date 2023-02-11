package presentation.screen.universal

import R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import presentation.icons.Icons
import presentation.icons.regular.Warning

@Composable
fun ErrorComposable(exception: Exception) {
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Regular.Warning, Icons.Regular.Warning.name)
            Text(stringResource(R.strings.composable_error))
            if (exception.localizedMessage != null) {
                Text(exception.localizedMessage)
            }
        }
    }
}