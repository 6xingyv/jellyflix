package presentation.screen.universal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingComposable() {
    Column {
        LinearProgressIndicator(Modifier.fillMaxWidth())
    }
}