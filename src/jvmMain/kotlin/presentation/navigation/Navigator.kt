@file:Suppress("unused")

package presentation.navigation

import presentation.utils.Fragment
import presentation.utils.Screen

class Navigator(rootPage: Screen) {
    private val screenStack = ScreenStack(rootPage)
    private val fragmentStack = FragmentStack()

    init {
        rootPage.navigator = this
    }

    fun navigate(screen: Screen) {
        screen.navigator = this
        screenStack.push(screen)
        fragmentStack.clear()
    }

    fun clear() {
        fragmentStack.clear()
        screenStack.clear()
    }

    fun navigate(fragment: Fragment) {
        fragment.navigator = this
        fragment.parentScreen = currentScreen()
        fragmentStack.push(fragment)
    }

    fun back() {
        if (fragmentStack.size() > 0) {
            fragmentStack.pop()
        } else {
            if (canBack()) {
                screenStack.pop()
            }
        }
    }

    fun currentScreen() = screenStack.peek()
    fun currentFragment() = fragmentStack.peek()

    fun canBack() = screenStack.history() > 0
}