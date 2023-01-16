package presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import presentation.utils.Fragment
import java.util.*

class FragmentStack() {
    private var currentFragment by mutableStateOf<Fragment?>(null)

    private val fragmentStack = Stack<Fragment>()

    fun push(fragment: Fragment) {
        fragmentStack.push(fragment)
        currentFragment = fragment
    }

    fun pop() {
        fragmentStack.pop()
        currentFragment = fragmentStack.peek()
    }

    fun clear() {
        fragmentStack.clear()
    }

    fun peek() = currentFragment

    fun size() = fragmentStack.size
}