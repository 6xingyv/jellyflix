package presentation.screen.account

import R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import org.koin.core.component.inject
import presentation.App
import presentation.screen.universal.ErrorComposable
import presentation.screen.universal.LoadingComposable
import presentation.utils.Fragment

class AccountInfoFragment : Fragment() {
    override val viewModel: AccountInfoViewModel by inject()

    @Composable
    override fun content() {
        val scope = rememberCoroutineScope()
        val jellyfinRepository = rememberSaveable { App().jellyfinRepository }

        viewModel.init(scope, jellyfinRepository)
        viewModel.loadData()

        val uiState by viewModel.uiState.collectAsState()
        when (uiState) {
            is AccountInfoViewModel.UiState.Error -> ErrorComposable((uiState as AccountInfoViewModel.UiState.Error).error)
            AccountInfoViewModel.UiState.Loading -> LoadingComposable()
            is AccountInfoViewModel.UiState.Normal -> {
                val user = (uiState as AccountInfoViewModel.UiState.Normal).user
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Text(
                        text = stringResource(R.strings.universal_account),
                        style = MaterialTheme.typography.h2
                    )
                    Text(user.name!!)
                    Text(if (user.policy?.isAdministrator == true) "Admin" else "User")
                }
            }
        }
    }
}