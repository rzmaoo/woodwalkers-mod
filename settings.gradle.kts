pluginManagement {
    repositories {
        // 使用 ivy 替代 flatDir
        ivy {
            url = uri("libs")
            patternLayout {
                // 强制匹配文件名格式：[artifact]-[version].jar
                artifact("[artifact]-[revision].[ext]")
            }
            // 直接找 jar 包
            metadataSources {
                artifact()
            }
        }

        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev/")
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.tocraft.dev/public")
        gradlePluginPortal()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("dev.tocraft.modmaster")) {
                useModule("dev.tocraft:modmaster-single:1.10.1")
            }
        }
    }
}

rootProject.name = "walkers"
include("common")
include("fabric")