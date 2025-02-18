plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.1.10"
}

group = "hu.pinter.beci"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.mockito:mockito-junit-jupiter:5.13.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.8.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}