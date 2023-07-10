package com.simonscholz.jellyflix

import com.seiko.imageloader.component.ComponentRegistryBuilder
import com.seiko.imageloader.component.setupDefaultComponents
import okio.Path
import okio.Path.Companion.toPath

actual fun ComponentRegistryBuilder.setupComponents() = this.setupDefaultComponents()
actual fun getImageCacheDirectoryPath(): Path = "media/".toPath()