plugins {
    kotlin("jvm")
}

group = "pr0gramm3r101"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":util"))
}

tasks.test {
    useJUnitPlatform()
}