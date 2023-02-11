package presentation.screen.account

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import org.koin.core.component.inject
import presentation.App
import presentation.screen.universal.EmptyFragment
import presentation.screen.universal.ErrorComposable
import presentation.screen.universal.LoadingComposable
import presentation.utils.Screen

// Todo: Replace LoginScreen with this screen
//       and code it as a Fragment
class AccountScreen : Screen() {
    override val viewModel: AccountViewModel by inject()

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun content() {
        val scope = rememberCoroutineScope()
        val jellyfinApi = rememberSaveable { App().jellyfinApi }
        val jellyfinRepository = rememberSaveable { App().jellyfinRepository }

        viewModel.init(scope, jellyfinRepository, jellyfinApi)

        val uiState by viewModel.uiState.collectAsState()

        when (uiState) {
            is AccountViewModel.UiState.Error -> navigator?.navigate(EmptyFragment {
                ErrorComposable((uiState as AccountViewModel.UiState.Error).error)
            })

            is AccountViewModel.UiState.Loading -> navigator?.navigate(EmptyFragment { LoadingComposable() })
            is AccountViewModel.UiState.Unauthorized -> navigator?.navigate(LoginFragment())
            is AccountViewModel.UiState.Authorized -> navigator?.navigate(AccountInfoFragment())
        }

        navigator?.currentFragment()?.content()
    }
}