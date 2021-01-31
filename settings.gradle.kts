pluginManagement {
    repositories {
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
}
include(":app", ":common:repository", ":common:ui")
rootProject.name = "Qats"
include(":common:model")
