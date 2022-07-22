import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version = "1.6.0"
plugins {
    kotlin("jvm") version "1.6.0"
}

group = "me.marc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}



tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-serialization:$ktor_version")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.8.+")
}