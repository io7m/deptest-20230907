import com.android.build.gradle.LibraryExtension

plugins {
    id("org.jetbrains.kotlin.android")
        .version("1.9.0")
        .apply(false)

    id("com.android.library")
        .version("8.1.0")
        .apply(false)
}

allprojects {
    apply(plugin = "com.android.library")
    apply(plugin = "org.jetbrains.kotlin.android")

    val android: LibraryExtension =
        this.extensions["android"] as LibraryExtension

    android.namespace = "com.io7m.example"
    android.compileSdk = 34

    val implementation by configurations

    dependencies {
        implementation("com.google.firebase:firebase-messaging:23.2.1")
    }
}