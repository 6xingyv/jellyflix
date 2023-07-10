package com.simonscholz.jellyflix.theme

import com.seiko.imageloader.ImageLoader
import com.simonscholz.jellyflix.getImageCacheDirectoryPath
import com.simonscholz.jellyflix.setupComponents

fun generateImageLoader(
    memCacheSize: Int = 16 * 1024 * 1024, //16MB
    diskCacheSize: Int = 512 * 1024 * 1024 //512MB
) = ImageLoader {
    interceptor {
        memoryCacheConfig {
            maxSizeBytes(memCacheSize)
        }
        diskCacheConfig {
            directory(getImageCacheDirectoryPath())
            maxSizeBytes(diskCacheSize.toLong())
        }
    }
    components {
        setupComponents()
    }
}