plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("compose-multiplatform-setup")
    id(libs.plugins.sql.delight.get().pluginId)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.android.driver)
        }

        commonMain {
            dependencies {
                implementation(libs.kodein)
            }
        }

        iosMain.dependencies {
            implementation(libs.native.driver)
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