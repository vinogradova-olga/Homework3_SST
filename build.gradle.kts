import info.solidsoft.gradle.pitest.PitestPluginExtension

plugins {
    kotlin("jvm") version "1.9.22"
    id("info.solidsoft.pitest") version "1.9.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))

    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

configure<PitestPluginExtension>{
    mutationThreshold.set(70)
    coverageThreshold.set(70)
    threads.set(Runtime.getRuntime().availableProcessors())
    junit5PluginVersion.set("1.0.0")
    mutators.set(setOf("STRONGER"))
    targetClasses.set(setOf("org.example.*"))
    targetTests.set(setOf("*"))
    outputFormats.set(setOf("HTML"))
    avoidCallsTo.set(setOf("kotlin.jvm.internal"))
}