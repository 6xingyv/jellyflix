package com.simonscholz.jellyflix.utils.window.jna.windows

import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.window.WindowScope
import com.sun.jna.Native
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.User32
import com.sun.jna.platform.win32.WinDef
import com.sun.jna.platform.win32.WinUser.GWL_STYLE
import org.jetbrains.skiko.OS
import org.jetbrains.skiko.hostOs
import java.awt.Window

val Window.hwnd
    get() =
        if (this is ComposeWindow) WinDef.HWND(Pointer(windowHandle))
        else WinDef.HWND(Native.getWindowPointer(this))


fun WindowScope.extendToClientArea() {
    when (hostOs) {
        OS.Linux -> {}
        OS.Windows -> {
            val hwnd = window.hwnd
            val extendedStyle = User32.INSTANCE.GetWindowLong(hwnd, GWL_STYLE)
            User32.INSTANCE.SetWindowLong(hwnd, GWL_STYLE, extendedStyle and User32.WS_CAPTION.inv())
            User32.INSTANCE.SetWindowPos(
                hwnd,
                null,
                0,
                0,
                0,
                0,
                User32.SWP_FRAMECHANGED or User32.SWP_NOMOVE or User32.SWP_NOSIZE or User32.SWP_NOZORDER or User32.SWP_ASYNCWINDOWPOS
            )
        }

        else -> {}
    }
}