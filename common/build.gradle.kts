plugins {
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    kotlin("jvm")
}

version = "2.1.6-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    jvmToolchain(21)
}

dependencies {

    compileOnly(files("../lib/android-35.jar"))
    compileOnly(project(":api"))
    compileOnly(project(":api-rs"))
    compileOnly(project(":rs2"))
    compileOnly(project(":eventbus"))

    with(compose) {
        compileOnly(runtime)
        compileOnly(ui)
        compileOnly(desktop.currentOs)
    }

    with(libs) {
        implementation(libs.jsoup)
        implementation(libs.androidx.annotation.jvm)
        compileOnly(kotlin.reflect)
        compileOnly(project(":logger"))
        compileOnly(gson)
        compileOnly(material3)
        compileOnly(line.awesome)
    }
}