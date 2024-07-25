plugins {
    java
    id("dev.deftu.gradle.multiversion")
    id("dev.deftu.gradle.tools")
    id("dev.deftu.gradle.tools.resources")
    id("dev.deftu.gradle.tools.bloom")
    id("dev.deftu.gradle.tools.shadow")
    id("dev.deftu.gradle.tools.minecraft.loom")
    id("dev.deftu.gradle.tools.minecraft.releases")
}

toolkitMultiversion {
    moveBuildsToRootProject.set(true)
}

toolkitLoomHelper {
    if (!mcData.isNeoForge) {
        useMixinRefMap(modData.id)
    }
}

repositories {
    maven("https://maven.su5ed.dev/releases") // Forgified Fabric API
}

dependencies {
    if (!mcData.isForge) {
        if (mcData.isFabric) {
            // Base dependencies
            modImplementation("net.fabricmc:fabric-loader:${mcData.dependencies.fabric.fabricLoaderVersion}")
            modImplementation("net.fabricmc.fabric-api:fabric-api:${mcData.dependencies.fabric.fabricApiVersion}")

            // Extra
            // ModMenu
            modImplementation("com.terraformersmc:modmenu:${mcData.dependencies.fabric.modMenuVersion}")
        } else if (mcData.isNeoForge) {
            // Base dependencies
            "neoForge"("net.neoforged:neoforge:${rootProject.extra["neoforge_version"]}")
            modImplementation("org.sinytra.forgified-fabric-api:forgified-fabric-api:${rootProject.extra["forgified_fabric_api_version"]}+${mcData.version}")
        }
    }
}
