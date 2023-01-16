package presentation.utils

import androidx.compose.runtime.Composable
import org.koin.core.component.KoinComponent
import presentation.navigation.Navigator

abstract class Screen : KoinComponent {
    var navigator: Navigator? = null
    open val viewModel: ViewModel? = null

    @Composable
    abstract fun content()
}