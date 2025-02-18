// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    androidApplication version "8.8.0" apply false
    kotlinJvm version Version.Kotlin.language apply false
    kotlinAnnotationProcessor version Version.Kotlin.language apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    alias(libs.plugins.kotlin.android) apply false
}