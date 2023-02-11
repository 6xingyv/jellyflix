package presentation.screen.home

import R
import data.model.HomeItem
import data.model.HomeSection
import data.repository.JellyfinRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jellyfin.sdk.model.UUID
import presentation.utils.ViewModel

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private lateinit var repository: JellyfinRepository

    sealed class UiState {
        data class Normal(val homeItems: List<HomeItem>) : UiState()
        object Loading : UiState()
        data class Error(val error: Exception) : UiState()
    }

    fun init(viewModelScope: CoroutineScope, jellyfinRepository: JellyfinRepository) {
        super.init(viewModelScope)
        this.repository = jellyfinRepository
    }

    fun loadData(includeLibraries: Boolean = true) {
        viewModelScope.launch {
            _uiState.emit(UiState.Loading)
            try {
                val items = mutableListOf<HomeItem>()

                if (includeLibraries) {
                    items.add(loadLibraries())
                }

                val updated = loadDynamicItems() + items

                _uiState.emit(UiState.Normal(updated))
            } catch (e: Exception) {
                _uiState.emit(UiState.Error(e))
            }
        }
    }

    private suspend fun loadLibraries(): HomeItem {
        val items = repository.getItems()
        return HomeItem.Libraries(
            HomeSection(
                UUID.fromString("38f5ca96-9e4b-4c0e-a8e4-02225ed07e02"),
                R.strings.universal_libraries,
                items
            )
        )
    }

    private suspend fun loadDynamicItems(): List<HomeItem.Section> {
        val resumeItems = repository.getResumeItems()
        val nextUpItems = repository.getNextUp()

        val items = mutableListOf<HomeSection>()
        if (resumeItems.isNotEmpty()) {
            items.add(
                HomeSection(
                    UUID.fromString("44845958-8326-4e83-beb4-c4f42e9eeb95"),
                    R.strings.universal_continue_watching,
                    resumeItems
                )
            )
        }

        if (nextUpItems.isNotEmpty()) {
            items.add(
                HomeSection(
                    UUID.fromString("18bfced5-f237-4d42-aa72-d9d7fed19279"),
                    R.strings.universal_next_up,
                    nextUpItems
                )
            )
        }

        return items.map { HomeItem.Section(it) }
    }
}