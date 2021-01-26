plugins {
//    id("com.android.library")
    id("java")
}

dependencies {
    // --- Network ---
    implementation("com.squareup.retrofit2:retrofit:${Libs.retrofit}")
    implementation("com.google.code.gson:gson:${Libs.gson}")
}