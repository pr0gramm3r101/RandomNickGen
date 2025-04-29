plugins {
    kotlin("jvm")
    java
}

group = "pr0gramm3r101"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("commons-io:commons-io:2.16.1")
}

tasks.test {
    useJUnitPlatform()
}