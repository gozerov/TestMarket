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


}