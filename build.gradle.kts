plugins {
    id(libs.plugins.androidApplication.get().pluginId) apply false
    id(libs.plugins.androidLibrary.get().pluginId) apply false
    id(libs.plugins.composeMultiplatform.get().pluginId) apply false
    id(libs.plugins.composeCompiler.get().pluginId) apply false
    id(libs.plugins.kotlinMultiplatform.get().pluginId) apply false
    id(libs.plugins.kotlinSerialization.get().pluginId) apply false
}