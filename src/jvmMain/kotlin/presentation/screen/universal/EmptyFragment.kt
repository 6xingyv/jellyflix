package presentation.screen.universal

import androidx.compose.runtime.Composable
import presentation.utils.Fragment

class EmptyFragment(private val composable: @Composable () -> Unit) : Fragment() {
    @Composable
    override fun content() {
        composable()
    }
}