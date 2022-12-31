plugins {
    id("java")
    id("fabric-loom") version "0.11-SNAPSHOT"
}

group = "com.spyeic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    minecraft("com.mojang:minecraft:1.16.5")
    mappings("net.fabricmc:yarn:1.16.5+build.10")
    modImplementation("net.fabricmc:fabric-loader:0.14.12")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.42.0+1.16")
}

tasks.processResources {
    // TODO
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}