package presentation.screen.library

import data.model.LibraryType
import data.model.SortBy
import data.repository.JellyfinRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.BaseItemDto
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.SortOrder
import presentation.utils.ViewModel

class LibraryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    sealed class UiState {
        data class Normal(val libraryItems: List<BaseItemDto>) : UiState()
        object Loading : UiState()
        data class Error(val error: Exception) : UiState()
    }

    private lateinit var repository: JellyfinRepository

    fun init(viewModelScope: CoroutineScope, jellyfinRepository: JellyfinRepository) {
        super.init(viewModelScope)
        this.repository = jellyfinRepository
    }

    fun loadItems(
        parentId: UUID,
        libraryType: LibraryType,
        sortBy: SortBy = SortBy.defaultValue,
        sortOrder: SortOrder = SortOrder.ASCENDING
    ) {
        val itemType = when (libraryType) {
            LibraryType.MOVIES -> BaseItemKind.MOVIE
            LibraryType.SERIES -> BaseItemKind.SERIES
            else -> null
        }
        viewModelScope.launch {
            _uiState.emit(UiState.Loading)
            try {
                val items = repository.getItems(
                    parentId = parentId,
                    includeTypes = if (itemType != null) listOf(itemType) else null,
                    recursive = true,
                    sortBy = sortBy,
                    sortOrder = sortOrder
                )
                _uiState.emit(UiState.Normal(items))
            } catch (e: Exception) {
                _uiState.emit(UiState.Error(e))
            }
        }
    }
}