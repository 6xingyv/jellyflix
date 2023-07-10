plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.buildConfig)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    jvm("desktop")

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.voyager.navigator)
                implementation(libs.napier)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.multiplatformSettings)
                implementation(libs.koin.core)
                implementation(libs.jellyfin)
            }
        }
    }
}

android {
    namespace = "com.simonscholz.jellyflix.core"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
    }
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/resources")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        resources.excludes.add("META-INF/**")
    }
}

buildConfig {
    // BuildConfig configuration here.
    // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts
}
