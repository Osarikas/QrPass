import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency: Dependency) {
    add(Type.IMPLEMENTATION, dependency.fullPath)
}

fun DependencyHandler.testImplementation(dependency: Dependency) {
    add(Type.TEST_IMPLEMENTATION, dependency.fullPath)
}

fun DependencyHandler.androidTestImplementation(dependency: Dependency) {
    add(Type.ANDROID_TEST_IMPLEMENTATION, dependency.fullPath)
}

fun DependencyHandler.api(dependency: Dependency) {
    add(Type.API, dependency.fullPath)
}

fun DependencyHandler.kapt(dependency: Dependency) {
    add(Type.KAPT, dependency.fullPath)
}

fun DependencyHandler.ksp(dependency: Dependency) {
    add(Type.KSP, dependency.fullPath)
}

fun DependencyHandler.defaultLibrary() {
    api(Dependencies.AndroidX.core)
    api(Dependencies.AndroidX.appcompat)
    api(Dependencies.AndroidX.materialDesign)
}

private object Type {
    const val IMPLEMENTATION = "implementation"
    const val TEST_IMPLEMENTATION = "testImplementation"
    const val ANDROID_TEST_IMPLEMENTATION = "androidTestImplementation"
    const val API = "api"
    const val KAPT = "kapt"
    const val KSP = "ksp"
}
