pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://raw.githubusercontent.com/zeruth/repo/main/") }
        maven { url = uri("https://raw.githubusercontent.com/MeteorLite/hosting/main/repo/") }
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
    id("de.fayard.refreshVersions") version "0.60.5"
}

rootProject.name = "Meteor Multiplatform"
//include(":android")
//include(":android-awt")
include(":api")
include(":api-rs")
include(":common")
include(":desktop")
include(":eventbus")
include(":logger")
include(":mixins")
include(":rs2")
//include(":rs2-mapview")

