package presentation.utils

import kotlinx.coroutines.CoroutineScope

open class ViewModel {

    internal lateinit var viewModelScope: CoroutineScope

    open fun init(viewModelScope: CoroutineScope) {
        this.viewModelScope = viewModelScope
    }
}