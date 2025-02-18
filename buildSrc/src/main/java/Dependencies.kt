data class Dependency(
        val name: String,
        val version: String,
) {
    val fullPath get() = "$name:$version"
}

object Dependencies {

    /**
     * Type-safe HTTP client for Android and Java by Square, Inc.
     *
     * [Documentation](http://square.github.io/retrofit/)
     *
     * [Github](https://github.com/square/retrofit)
     *
     * [Apache License 2.0](https://github.com/square/retrofit/blob/master/LICENSE.txt)
     *
     * [Changelog](https://github.com/square/retrofit/blob/master/CHANGELOG.md)
     */
    object Retrofit {
        private const val version = "2.9.0"

        val library = Dependency("com.squareup.retrofit2:retrofit", version)
        val gsonConverter = Dependency("com.squareup.retrofit2:converter-gson", version)
    }


    /**
     * [Documentation](https://developer.android.com/jetpack/androidx)
     *
     * [Releases](https://developer.android.com/jetpack/androidx/versions).
     */
    object AndroidX {
        /**
         * [Changelog](https://developer.android.com/jetpack/androidx/releases/core)
         */
        val core = Dependency("androidx.core:core-ktx", "1.13.1")

        /**
         * [Changelog](https://developer.android.com/jetpack/androidx/releases/appcompat)
         */
        val appcompat = Dependency("androidx.appcompat:appcompat", "1.7.0")

        /**
         * [Changelog](https://developer.android.com/jetpack/androidx/releases/activity)
         */
        val activity = Dependency("androidx.activity:activity", "1.9.3")

        /**
         * [Changelog](https://developer.android.com/jetpack/androidx/releases/fragment)
         */
        val fragment = Dependency("androidx.fragment:fragment-ktx", "1.8.4")

        /**
         * [Changelog](https://developer.android.com/jetpack/androidx/releases/recyclerview)
         */
        val recyclerView = Dependency("androidx.recyclerview:recyclerview", "1.3.2")

        /**
         * [Changelog](https://developer.android.com/jetpack/androidx/releases/cardview)
         */
        val cardView = Dependency("androidx.cardview:cardview", "1.0.0")

        /**
         * [Changelog](https://developer.android.com/jetpack/androidx/releases/gridlayout)
         */
        val gridLayout = Dependency("androidx.gridlayout:gridlayout", "1.0.0")

        /**
         * A ConstraintLayout is a ViewGroup which allows you to position and size widgets in a flexible way.
         *
         * [Documentation](https://developer.android.com/reference/android/support/constraint/ConstraintLayout)
         *
         * [Changelog](https://developer.android.com/jetpack/androidx/releases/constraintlayout)
         */
        val constraintLayout = Dependency("androidx.constraintlayout:constraintlayout", "2.1.4")

        /**
         * CoordinatorLayout is a super-powered FrameLayout.
         * CoordinatorLayout is intended for two primary use cases:
         * 1. As a top-level application decor or chrome layout
         * 2. As a container for a specific interaction with one or more child views
         *
         * [Documentation](https://developer.android.com/jetpack/androidx/releases/coordinatorlayout)
         *
         * [Changelog](https://developer.android.com/jetpack/androidx/releases/coordinatorlayout)
         */
        val coordinatorLayout = Dependency("androidx.coordinatorlayout:coordinatorlayout", "1.2.0")

        /**
         * The SwipeRefreshLayout should be used whenever the user
         * can refresh the contents of a view via a vertical swipe gesture.
         *
         * [Documentation](https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout)
         *
         * [Changelog](https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout)
         */
        val swipeRefreshLayout = Dependency("androidx.swiperefreshlayout:swiperefreshlayout", "1.1.0")

        /**
         * [Changelog](https://developer.android.com/jetpack/androidx/releases/test/)
         */
        object Testing {

            /**
             * [Documentation](https://developer.android.com/training/testing)
             */
            val core = Dependency("androidx.test:core", "1.5.0")

            /**
             * [Documentation](https://developer.android.com/training/testing)
             */
            val junit = Dependency("androidx.test.ext:junit-ktx", "1.1.5")

            /**
             * [Documentation](https://developer.android.com/training/testing/espresso)
             */
            object Espresso {
                private const val version = "3.5.1"
                val core = Dependency("androidx.test.espresso:espresso-core", version)
                val intents = Dependency("androidx.test.espresso:espresso-intents", version)
                val contrib = Dependency("androidx.test.espresso:espresso-contrib", version)
            }

            /**
             * [Documentation](https://developer.android.com/training/testing/junit-runner)
             */
            val runner = Dependency("androidx.test:runner", "1.5.2")

            /**
             * [Documentation](https://developer.android.com/training/testing/junit-rules)
             */
            val rules = Dependency("androidx.test:rules", "1.5.0")

            /**
             * [Documentation](https://developer.android.com/training/testing/junit-rules)
             */
            val compose = Dependency("androidx.compose.ui:ui-test-junit4", "1.6.0")

            /**
             * [Documentation](https://developer.android.com/training/testing/junit-runner#using-android-test-orchestrator)
             */
            val orchestrator = Dependency("androidx.test:orchestrator", "1.4.2")

            /**
             * [Documentation](https://developer.android.com/training/testing/ui-automator)
             */
            val uiAutomator = Dependency("androidx.test.uiautomator:uiautomator", "2.2.0")
        }

        /**
         * [Documentation](https://material.io/develop/android/)
         *
         * [Github](https://github.com/material-components/material-components-android)
         *
         * [Changelog](https://github.com/material-components/material-components-android/releases)
         */
        val materialDesign = Dependency("com.google.android.material:material", "1.11.0")

        object Lifecycle {
            private const val version = "2.6.1"

            val viewModel = Dependency("androidx.lifecycle:lifecycle-viewmodel-ktx", version)
            val common = Dependency("androidx.lifecycle:lifecycle-common", version)
        }

        object Navigation {
            private const val version = "2.8.3"

            val fragment = Dependency("androidx.navigation:navigation-fragment-ktx", version)
            val navigationUi = Dependency("androidx.navigation:navigation-ui-ktx", version)
        }
    }

    /**
     * JUnit is a simple framework to write repeatable tests.
     *
     * [Documentation](https://junit.org/junit4/)
     *
     * [Github](https://github.com/junit-team/junit4)
     *
     * [Eclipse Public License 1.0](https://github.com/junit-team/junit4/blob/master/LICENSE-junit.txt)
     *
     * [Changelog](https://github.com/junit-team/junit4/wiki)
     */
    val junit = Dependency("junit:junit", "4.13")

    /**
     * Truth makes your test assertions and failure messages more readable.
     * Similar to AssertJ, it natively supports many JDK and Guava types,
     * and it is extensible to others.
     *
     * [Documentation](https://truth.dev/)
     *
     * [Github](https://github.com/google/truth)
     *
     * [Apache License 2.0](https://github.com/google/truth/blob/master/LICENSE)
     *
     * [Changelog](https://github.com/google/truth/releases)
     */
    val truth = Dependency("com.google.truth:truth", "1.3.0")

    /**
     * Kaspresso is a framework for Android UI testing. Based on Espresso and UI Automator.
     *
     * [Documentation](https://kasperskylab.github.io/Kaspresso/)
     *
     * [Github](https://github.com/KasperskyLab/Kaspresso)
     *
     * [Apache License 2.0](https://github.com/KasperskyLab/Kaspresso/blob/master/LICENSE.txt)
     *
     * [Changelog](https://github.com/KasperskyLab/Kaspresso/releases)
     */
    object Kaspresso {
        private const val version = "1.5.3"
        val core = Dependency("com.kaspersky.android-components:kaspresso", version)
        val composeSupport = Dependency("com.kaspersky.android-components:kaspresso-compose-support", version)
    }
}