plugins {
    id("multiplatform-setup")
    id("compose-multiplatform-setup")
    id("android-setup")
}

compose.resources {
    publicResClass = false
    packageOfResClass = "ru.gozerov.test_market.common.features.shopping-list.presentation.resources"
    generateResClass = auto
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:features:shopping-list:domain"))
                implementation(project(":common:core"))

                implementation(compose.runtime)
                implementation(compose.components.resources)

                implementation(libs.viewmodel)

                implementation(libs.coil.compose)
                implementation(libs.coil.compose.core)
                implementation(libs.coil.mp)
                implementation(libs.coil.network.ktor)

                implementation(libs.navigation)

                implementation(libs.immutable.collections)
            }
        }
    }
}

android {
    namespace = "ru.gozerov.test_market.shopping_list.presentation"
}