plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "gg.fullwin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://nexus.sirblobman.xyz/repository/public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.1")
    compileOnly("com.github.sirblobman.api:core:2.6-SNAPSHOT")
    compileOnly("com.github.sirblobman.combatlogx:api:11.0.0.0-SNAPSHOT")
    implementation("org.mongodb:mongo-java-driver:3.12.11")
    implementation("org.jetbrains:annotations:23.0.0")
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
