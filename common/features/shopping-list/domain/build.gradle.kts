plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("compose-multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(path = ":common:core"))
        }

        commonTest.dependencies { 
            implementation(libs.kotlin.test)
        }
    }
}