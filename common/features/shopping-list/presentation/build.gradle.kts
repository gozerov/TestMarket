plugins {
    id("multiplatform-setup")
    id("compose-multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:features:shopping-list:domain"))
                implementation(project(":common:core"))

                implementation(compose.runtime)

                implementation(libs.viewmodel)

                implementation(libs.coil.compose)
                implementation(libs.coil.compose.core)
                implementation(libs.coil.mp)
                implementation(libs.coil.network.ktor)

                implementation(libs.navigation)
            }
        }
    }
}

android {
    namespace = "ru.gozerov.test_market.shopping_list.presentation"
}