plugins {
    androidApplication
    jetbrainsKotlinSerialization version Version.Kotlin.language
    kotlinAnnotationProcessor
    id("com.google.dagger.hilt.android") version("2.51.1")
    alias(libs.plugins.kotlin.android)
}

val packageName = "ru.myitschool.work"
android {
    namespace = packageName
    compileSdk = 35

    defaultConfig {
        applicationId = packageName
        minSdk = 31
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = Version.Kotlin.javaSource
        targetCompatibility = Version.Kotlin.javaSource
    }

    kotlinOptions {
        jvmTarget = Version.Kotlin.jvmTarget
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    defaultLibrary()

    implementation(Dependencies.AndroidX.activity)
    implementation(Dependencies.AndroidX.fragment)
    implementation(Dependencies.AndroidX.constraintLayout)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.serialization)
    implementation(libs.picasso)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.barcode.scanning)
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.mlkit.vision)

    val hilt = "2.51.1"
    implementation(libs.hilt.android)
    kapt("com.google.dagger:hilt-android-compiler:$hilt")
}

kapt {
    correctErrorTypes = true
}
