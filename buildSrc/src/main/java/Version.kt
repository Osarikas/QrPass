import org.gradle.api.JavaVersion

object Version {

    /**
     * Gradle is an open-source build automation tool focused on flexibility and performance.
     *
     * [Documentation](https://docs.gradle.org/current/userguide/userguide.html)
     *
     * [Github](https://github.com/gradle/gradle)
     *
     * [Apache 2.0 License](https://github.com/gradle/gradle/blob/master/LICENSE)
     *
     * [Changelog](https://gradle.org/releases/)
     */
    const val agp = "8.8.0"

    object Kotlin {

        /**
         * [Documentation](https://kotlinlang.org/)
         *
         * [Source Code](https://github.com/JetBrains/kotlin/)
         *
         * [Apache 2.0 License](https://github.com/JetBrains/kotlin/blob/master/license/LICENSE.txt)
         *
         * [Changelog](https://kotlinlang.org/releases.html)
         */
        const val language = "2.0.21"

        val javaSource = JavaVersion.VERSION_11
        const val jvmTarget = "11"
    }

    object Android {
        object Sdk {
            const val min = 31
            const val compile = 35
            const val target = 34
        }
    }
}