plugins {
    id("dev.tocraft.modmaster.common")
}

dependencies {
    // 使用本地 craftedcore
    modApi(files("${rootDir}/libs/craftedcore-fabric-7.1.jar"))
}