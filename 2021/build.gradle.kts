plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
    jcenter()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.3"
    }
}

dependencies {
    implementation("com.andreapivetta.kolor:kolor:1.0.0")
}