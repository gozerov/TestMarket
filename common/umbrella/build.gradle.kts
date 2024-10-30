plugins {
    id("android-setup")
    id("multiplatform-setup")
    id("compose-multiplatform-setup")
}

compose.resources {
    publicResClass = false
    packageOfResClass = "ru.gozerov.test_market.common.umbrella.resources"
    generateResClass = auto
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:core"))
                implementation(project(":common:features:cart:data"))
                implementation(project(":common:features:cart:domain"))
                implementation(project(":common:features:cart:presentation"))
                implementation(project(":common:features:main:presentation"))
                implementation(project(":common:features:main:domain"))
                implementation(project(":common:features:main:data"))
                implementation(project(":common:features:shopping-list:presentation"))
                implementation(project(":common:features:shopping-list:domain"))
                implementation(project(":common:features:shopping-list:data"))


                implementation(libs.kodein)

                implementation(compose.ui)
                implementation(compose.components.resources)

                implementation(libs.viewmodel)

                implementation(libs.navigation)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}
