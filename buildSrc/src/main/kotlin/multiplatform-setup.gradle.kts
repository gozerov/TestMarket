import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    androidTarget {

    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    )

    jvmToolchain(11)

}