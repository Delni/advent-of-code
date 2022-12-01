plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.0-Beta"
}

dependencies {
    implementation(project(":kutils"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}