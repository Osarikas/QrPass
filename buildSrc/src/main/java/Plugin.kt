import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

val PluginDependenciesSpec.androidApplication: PluginDependencySpec
    get() = id(Plugin.Id.Android.application)
val PluginDependenciesSpec.androidLibrary: PluginDependencySpec
    get() = id(Plugin.Id.Android.library)
val PluginDependenciesSpec.kotlinJvm: PluginDependencySpec
    get() = id(Plugin.Id.Kotlin.jvm)
val PluginDependenciesSpec.kotlinAndroid: PluginDependencySpec
    get() = id(Plugin.Id.Kotlin.android)
val PluginDependenciesSpec.kotlinParcelize: PluginDependencySpec
    get() = id(Plugin.Id.Kotlin.parcelize)
val PluginDependenciesSpec.kotlinAnnotationProcessor: PluginDependencySpec
    get() = id(Plugin.Id.Kotlin.annotationProcessor)
val PluginDependenciesSpec.kotlinSerialization: PluginDependencySpec
    get() = id(Plugin.Id.Kotlin.serialization)
val PluginDependenciesSpec.jetbrainsKotlinSerialization: PluginDependencySpec
    get() = id(Plugin.Id.JetBrains.serialization)

object Plugin {
    object Id {
        object Android {
            /**
             * [Documentation](https://google.github.io/android-gradle-dsl/current/)
             * [Changelog](https://developer.android.com/studio/releases/gradle-plugin)
             */
            const val application = "com.android.application"
            /**
             * [Documentation](https://google.github.io/android-gradle-dsl/current/)
             * [Changelog](https://developer.android.com/studio/releases/gradle-plugin)
             */
            const val library = "com.android.library"
        }

        object Kotlin {
            /**
             * Plugin published in https://plugins.gradle.org/
             */
            const val jvm = "org.jetbrains.kotlin.jvm"
            /**
             * Plugin published in https://plugins.gradle.org/
             */
            const val android = "org.jetbrains.kotlin.android"

            /**
             * Plugin published in https://plugins.gradle.org/
             */
            const val parcelize = "kotlin-parcelize"

            /**
             * Plugin published in https://plugins.gradle.org/
             */
            const val annotationProcessor = "org.jetbrains.kotlin.kapt"

            /**
             * Plugin published in https://plugins.gradle.org/
             */
            const val serialization = "plugin.serialization"
        }

        object JetBrains {
            const val serialization = "org.jetbrains.kotlin.plugin.serialization"
        }
    }
}