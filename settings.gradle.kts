rootProject.name = "TestMarket"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":common:features:cart:data")
include(":common:features:cart:domain")
include(":common:features:cart:presentation")
include(":common:features:shopping-list:data")
include(":common:features:shopping-list:domain")
include(":common:features:shopping-list:presentation")
include(":common:features:main:data")
include(":common:features:main:domain")
include(":common:features:main:presentation")
include(":common:umbrella")
include(":common:core")
include(":common:core-database")