import proguard.gradle.ProGuardTask

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.guardsquare:proguard-gradle:7.1.0")
    }
}

plugins {
    kotlin("jvm") version "2.0.21"
    application
    java
    id("org.beryx.runtime") version "1.3.0"
}

group = "pr0gramm3r101"
version = "1.0.2"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("org.json:json:20240303")
    testImplementation(kotlin("test"))
    implementation(project(":util"))
    implementation(project(":logging"))
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("pr0gramm3r101.randomnickgen.MainKt")
}

tasks {
    val fatJar = register<Jar>("fatJar") {
        dependsOn("compileJava", "compileKotlin", "processResources") // We need this for Gradle optimization to work
        archiveClassifier.set("standalone") // Naming the jar
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest { attributes(mapOf("Main-Class" to application.mainClass)) } // Provided we set it up in the application plugin configuration
        val sourcesMain = sourceSets.main.get()
        val contents = configurations.runtimeClasspath.get()
            .map { if (it.isDirectory) it else zipTree(it) } +
                sourcesMain.output
        from(contents)
    }
    build {
        dependsOn(fatJar) // Trigger fat jar creation during build
    }

    "runtime" {
        doLast {
            copy {
                from("src/main/resources")
                into("${layout.buildDirectory}/image/bin")
            }
        }
    }
}

task<ProGuardTask>("minify") {
    val fileName = "libs/${project.name}-${version}-standalone"
    injars(layout.buildDirectory.file("$fileName.jar"))
    outjars(layout.buildDirectory.file("$fileName-optimized.jar"))

    dependsOn("fatJar")
}

runtime {
    options.set(listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages"))
}