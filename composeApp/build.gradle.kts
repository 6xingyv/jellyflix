import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.application)
    alias(libs.plugins.libres)
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

//    js {
//        browser()
//        binaries.executable()
//    }
//
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()
//
//    cocoapods {
//        version = "1.0.0"
//        summary = "Compose application framework"
//        homepage = "empty"
//        ios.deploymentTarget = "11.0"
//        podfile = project.file("../iosApp/Podfile")
//        framework {
//            baseName = "ComposeApp"
//            isStatic = true
//        }
//    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core"))
                // implementation(project(":data"))
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(libs.libres)
                implementation(libs.voyager.navigator)
                implementation(libs.composeImageLoader)
                implementation(libs.napier)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.insetsx)
                implementation(libs.multiplatformSettings)
                implementation(libs.koin.core)
                implementation(libs.jellyfin)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activityCompose)
                implementation(libs.compose.uitooling)
                implementation(libs.kotlinx.coroutines.android)
                implementation(libs.sqlDelight.driver.android)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)
                implementation(libs.jna)
                implementation(libs.jna.platform)
                implementation(libs.compose.window.styler)
                implementation(libs.sqlDelight.driver.sqlite)
                implementation("org.jetbrains:annotations:24.0.0")
            }
        }
    }
}

android {
    namespace = "com.simonscholz.jellyflix"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        applicationId = "com.simonscholz.jellyflix.androidApp"
        versionCode = 1
        versionName = "1.0.0"
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

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.simonscholz.jellyflix.desktopApp"
            packageVersion = "1.0.0"
        }
    }
}

libres {
    // https://github.com/Skeptick/libres#setup
}
tasks.getByPath("desktopProcessResources").dependsOn("libresGenerateResources")
tasks.getByPath("desktopSourcesJar").dependsOn("libresGenerateResources")

buildConfig {
    // BuildConfig configuration here.
    // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts
}
