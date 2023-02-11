package presentation.screen.account

import data.repository.JellyfinRepository
import data.source.remote.JellyfinApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import presentation.utils.ViewModel

class AccountViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    sealed class UiState {
        object Unauthorized : UiState()
        object Authorized : UiState()
        object Loading : UiState()
        data class Error(val error: Exception) : UiState()
    }

    private lateinit var repository: JellyfinRepository
    private lateinit var api: JellyfinApi

    fun init(viewModelScope: CoroutineScope, jellyfinRepository: JellyfinRepository, jellyfinApi: JellyfinApi) {
        super.init(viewModelScope)
        this.repository = jellyfinRepository
        this.api = jellyfinApi
        checkIsAuthorized()
    }

    private fun checkIsAuthorized() {
        viewModelScope.launch {
            try {
                val user = api.userApi.getCurrentUser().content
                _uiState.emit(UiState.Authorized)
            } catch (e: Exception) {
                _uiState.emit(UiState.Unauthorized)
            }
        }
    }
}