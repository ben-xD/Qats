plugins {
    id("java")
    kotlin("jvm")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":common:model"))
    implementation("com.squareup.retrofit2:retrofit:${Libs.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Libs.retrofit}")
    implementation("com.google.code.gson:gson:${Libs.gson}")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Libs.coroutines}")

    // --- Hilt ---
    implementation("com.google.dagger:hilt-android:${Versions.hilt}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.hilt}")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}