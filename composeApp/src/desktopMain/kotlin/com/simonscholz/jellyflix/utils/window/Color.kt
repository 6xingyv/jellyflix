package com.simonscholz.jellyflix.utils.window

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.connect

fun Color.toBgr(): Int {
    val colorSpace = colorSpace
    val color = floatArrayOf(red, green, blue)

    // The transformation saturates the output
    colorSpace.connect().transform(color)

    return ((color[2] * 255.0f + 0.5f).toInt() shl 16) or
            ((color[1] * 255.0f + 0.5f).toInt() shl 8) or
            (color[0] * 255.0f + 0.5f).toInt()
}