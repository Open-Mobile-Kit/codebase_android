// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.0" apply false
    alias(libs.plugins.android.application) apply false
}
