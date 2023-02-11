package presentation.screen.account

import data.repository.JellyfinRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jellyfin.sdk.model.api.UserDto
import presentation.utils.ViewModel

class AccountInfoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    sealed class UiState {
        data class Normal(val user: UserDto) : UiState()
        object Loading : UiState()
        data class Error(val error: Exception) : UiState()
    }

    private lateinit var repository: JellyfinRepository

    fun init(viewModelScope: CoroutineScope, jellyfinRepository: JellyfinRepository) {
        super.init(viewModelScope)
        this.repository = jellyfinRepository
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.emit(UiState.Loading)
            try {
                _uiState.emit(UiState.Normal(repository.getCurrentUser()))
            } catch (e: Exception) {
                _uiState.emit(UiState.Error(e))
            }
        }
    }
}