plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(AppConfig.compileSdkVersion)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId("uk.orth.qats")
        minSdkVersion(AppConfig.minSdkVersion)
        targetSdkVersion(AppConfig.targetSdkVersion)
        versionCode(AppConfig.versionCode)
        versionName(AppConfig.versionName)

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        release {
            minifyEnabled(false)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        dataBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerVersion = "1.4.21"
        kotlinCompilerExtensionVersion = "1.0.0-alpha10"
    }
}

dependencies {
    // Refactor versions into Dependencies.kt before creating a new module
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    testImplementation("junit:junit:${Libs.junit}")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    // --- Hilt ---
    implementation("com.google.dagger:hilt-android:${Versions.hilt}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.hilt}")

    // --- Jetpack Navigation ---
    implementation("androidx.navigation:navigation-fragment-ktx:${Libs.nav}")
    implementation("androidx.navigation:navigation-ui-ktx:${Libs.nav}")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.3.2")
    androidTestImplementation("androidx.navigation:navigation-testing:${Libs.nav}")
    implementation("androidx.navigation:navigation-compose:${Libs.nav_compose}")

    // --- Jetpack Compose ---
    implementation("androidx.compose.ui:ui:${Libs.compose}")
    implementation("androidx.compose.ui:ui-tooling:${Libs.compose}")
    implementation("androidx.compose.foundation:foundation:${Libs.compose}")
    implementation("androidx.compose.material:material:${Libs.compose}")
    implementation("androidx.compose.material:material-icons-core:${Libs.compose}")
    implementation("androidx.compose.material:material-icons-extended:${Libs.compose}")
    implementation("androidx.compose.runtime:runtime-livedata:${Libs.compose}")
    implementation("androidx.compose.runtime:runtime-rxjava2:${Libs.compose}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Libs.compose}")

    // --- Network ---
    implementation("com.squareup.retrofit2:retrofit:${Libs.retrofit}")
    implementation("com.google.code.gson:gson:${Libs.gson}")
}