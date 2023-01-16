package presentation.screen.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import presentation.utils.Screen


class DetailsScreen(private val id:Int) : Screen() {

    @Composable
    override fun content() {
        Column {
            Text("Details")
            Text("$id")
            Button(onClick = {
                navigator?.back()
            }) {
                Text("Back")
            }
        }
    }

}