package com.simonscholz.jellyflix

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.seiko.imageloader.component.ComponentRegistryBuilder
import com.seiko.imageloader.component.setupDefaultComponents
import okio.Path
import okio.Path.Companion.toPath

class AndroidApp : Application() {
    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { App() }
    }
}

actual fun ComponentRegistryBuilder.setupComponents() = this.setupDefaultComponents(AndroidApp.INSTANCE)

actual fun getImageCacheDirectoryPath(): Path = AndroidApp.INSTANCE.cacheDir.absolutePath.toPath()
