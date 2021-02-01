plugins {
    id("com.android.library")
}

android {
    compileSdkVersion(AppConfig.compileSdkVersion)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        minSdkVersion(AppConfig.minSdkVersion)
        targetSdkVersion(AppConfig.targetSdkVersion)
        versionCode(AppConfig.versionCode)
        versionName(AppConfig.versionName)

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }
}

dependencies {
    implementation("com.google.android.material:material:${Libs.material}")
}