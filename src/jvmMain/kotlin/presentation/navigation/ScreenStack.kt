package presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import presentation.utils.Screen
import java.util.*

class ScreenStack(rootPage: Screen) {
    private var currentScreen by mutableStateOf(rootPage)

    private val screenStack = Stack<Screen>().apply {
        push(rootPage)
    }

    fun push(screen: Screen) {
        screenStack.push(screen)
        currentScreen = screen
    }

    fun pop() {
        screenStack.pop()
        currentScreen = screenStack.peek()
    }

    fun clear() {
        screenStack.clear()
    }

    fun peek() = currentScreen

    fun history() = screenStack.size - 1
}