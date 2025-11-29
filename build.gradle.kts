plugins {
    id("dev.architectury.loom") version "1.11-SNAPSHOT" apply false
    id("architectury-plugin") version "3.4-SNAPSHOT" apply false
    id("com.gradleup.shadow") version "9.2.2" apply false
    id("net.darkhax.curseforgegradle") version "1.1.27" apply false
    id("com.modrinth.minotaur") version "2.8.10" apply false

    id("dev.tocraft.modmaster.root") version ("1.10.1")
}

allprojects {
    repositories {

        ivy {
            url = uri("${rootProject.projectDir}/libs")
            patternLayout {
                artifact("[artifact]-[revision].[ext]")
            }
            metadataSources {
                artifact()
            }
        }

        mavenLocal()
        mavenLocal() // Check local repository first

        // Minecraft Libraries repository with exclusive content filtering for LWJGL
        val minecraft = maven {
            name = "Minecraft Libraries"
            url = uri("https://libraries.minecraft.net")
            mavenContent {
                releasesOnly()
            }
        }

        // Set up exclusive content filtering for specific LWJGL dependencies that cause issues
        exclusiveContent {
            forRepositories(minecraft)
            filter {
                includeModule("org.lwjgl", "lwjgl-freetype")
                includeGroupAndSubgroups("com.mojang")
            }
        }

        mavenCentral() // Then Maven Central
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev/")
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.tocraft.dev/public")
    }
}

subprojects {
    // Standard configuration
    configurations.all {
        resolutionStrategy.preferProjectModules()
    }
}

ext {
    val modMeta = mutableMapOf<String, Any>()
    modMeta["minecraft_version"] = project.properties["minecraft"] as String
    modMeta["version"] = version
    modMeta["craftedcore_version"] = project.properties["craftedcore_version"] as String
    set("mod_meta", modMeta)
}
