object AppConfig {
    const val compileSdkVersion = 30
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val buildToolsVersion = "30.0.3"

    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
    const val proguardConsumerRules =  "consumer-rules.pro"
    const val dimension = "environment"
}

object Versions {
    const val gradle = "7.0.0-alpha04"
    const val kotlin = "1.4.21"
    const val hiltAndroidGradlePlugin = "2.28-alpha"
    const val hilt = "2.28-alpha"
}

object Libs {
    const val nav = "2.3.2" // https://developer.android.com/jetpack/androidx/releases/navigation
    const val nav_compose = "1.0.0-alpha05"
    const val compose = "1.0.0-alpha10" // https://developer.android.com/jetpack/androidx/releases/compose
    const val junit = "4.13.1"
    const val retrofit = "2.9.0"
    const val gson = "2.8.6"
}