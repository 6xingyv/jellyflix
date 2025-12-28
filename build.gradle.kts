@file:Suppress("UNUSED_VARIABLE")

import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources")
}

group = "com.jellyflix"
version = "1.0.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Moko
                implementation("dev.icerock.moko:resources:0.22.3")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                // Window Styler(https://github.com/MayakaApps/ComposeWindowStyler)
                implementation("com.mayakapps.compose:window-styler:0.3.2")
                // Koin
                implementation("io.insert-koin:koin-core:4.1.0")
                // Retrofit
//                implementation("com.squareup.retrofit2:retrofit:2.9.0")
//                implementation("com.squareup.retrofit2:converter-gson:2.9.0")
                // Moko
                implementation("dev.icerock.moko:resources:0.22.3")
                implementation("dev.icerock.moko:resources-compose:0.22.3")
                // Jellyfin SDK
                implementation("org.jellyfin.sdk:jellyfin-core:1.8.5")
                // Jetpack Paging Multiplatform()
                implementation("app.cash.paging:paging-common:3.1.1-0.2.0")
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe)
            packageName = "desktop"
            packageVersion = "1.0.0"
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = ""
    multiplatformResourcesClassName = "R"
}
