plugins {
    id("dev.tocraft.modmaster.fabric")
}

tasks.withType<ProcessResources> {
    @Suppress("UNCHECKED_CAST") val modMeta = parent!!.ext["mod_meta"]!! as Map<String, Any>

    filesMatching("fabric.mod.json") {
        expand(modMeta)
    }

    outputs.upToDateWhen { false }
}

dependencies {
    mappings(loom.officialMojangMappings())

    modApi(files("${rootDir}/libs/craftedcore-fabric-7.1.jar"))

    modImplementation("net.fabricmc.fabric-api:fabric-api:0.136.0+1.21.11") {
        isTransitive = false
    }
}

architectury {

}