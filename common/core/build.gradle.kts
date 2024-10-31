plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("compose-multiplatform-setup")
    id(libs.plugins.kotlinSerialization.get().pluginId)
    id(libs.plugins.sql.delight.get().pluginId)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.android.driver)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.native.driver)
        }
        commonMain {
            dependencies {
                implementation(libs.kodein)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                implementation(libs.ktor.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.serialization.kotlinx.json)

                implementation(libs.viewmodel)

                implementation(libs.navigation)
            }
        }
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("ru.gozerov.database")
        }
    }
}