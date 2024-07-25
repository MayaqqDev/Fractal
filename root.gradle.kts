plugins {
    id("dev.deftu.gradle.multiversion-root")
}

preprocess {
    val neoforge_1_21 = createNode("1.21-neoforge", 1_21_00, "srg")
    val fabric_1_21 = createNode("1.21-fabric", 1_21_00, "yarn")

    fabric_1_21.link(neoforge_1_21)
}
