plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("compose-multiplatform-setup")
    id(libs.plugins.kotlinSerialization.get().pluginId)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":common:features:shopping-list:domain"))
            implementation(project(":common:core"))
            implementation(project(":common:core-database"))

            implementation(libs.kodein)
            implementation(libs.ktor.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}