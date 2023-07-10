package com.simonscholz.jellyflix.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.rememberAsyncImagePainter
import com.simonscholz.jellyflix.theme.generateImageLoader

@Composable
fun MagicImage(
    url: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) {
    CompositionLocalProvider(
        LocalImageLoader provides generateImageLoader(),
    ) {
        val painter = rememberAsyncImagePainter(url)
        Image(painter, contentDescription, modifier, alignment, contentScale, alpha, colorFilter)
    }
}