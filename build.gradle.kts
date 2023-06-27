// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_gradle_plugin}")
    }
}

plugins {
    id("com.android.application") version Versions.gradle_plugin apply false
    id("com.android.library") version Versions.gradle_plugin apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin_gradle_plugin apply false
    id("com.android.dynamic-feature") version Versions.gradle_plugin apply false
}