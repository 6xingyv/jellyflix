package presentation.screen.login

import data.repository.JellyfinRepository
import data.source.remote.JellyfinApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jellyfin.sdk.model.api.AuthenticateUserByName
import presentation.utils.ViewModel

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private lateinit var repository: JellyfinRepository
    private lateinit var api: JellyfinApi

    sealed class UiState {
        data class Normal(
            val server: String = "http://localhost:8096", val username: String = "阿睿", val password: String = "123456zzr"
        ) : UiState()

        object Loading : UiState()
        object Logged : UiState()
        data class Error(val error: Exception) : UiState()
    }

    fun init(viewModelScope: CoroutineScope, jellyfinRepository: JellyfinRepository, jellyfinApi: JellyfinApi) {
        super.init(viewModelScope)
        this.repository = jellyfinRepository
        this.api = jellyfinApi
        viewModelScope.launch {
            _uiState.emit(UiState.Normal())
        }
    }

    fun login(server: String, username: String, password: String) {
        viewModelScope.launch {
            _uiState.emit(UiState.Normal(server, username, password))
            try {
                api.apply {
                    api.baseUrl = server
                }
                val authenticationResult by api.userApi.authenticateUserByName(
                    data = AuthenticateUserByName(
                        username = username,
                        pw = password
                    )
                )
                api.apply {
                    userId = authenticationResult.user!!.id
                    api.userId = authenticationResult.user!!.id
                    api.accessToken = authenticationResult.accessToken!!
                }
                _uiState.emit(UiState.Logged)
            } catch (e: Exception) {
                _uiState.emit(UiState.Error(e))
            }
        }
    }
}