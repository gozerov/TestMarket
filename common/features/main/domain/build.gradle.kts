plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}