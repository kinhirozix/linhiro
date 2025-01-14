plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    `java-gradle-plugin`
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    composite(libs.kotlin.stdlib)
    composite(libs.kotlin.reflect)
    composite(libs.annotations)
    composite(gradleApi())
    composite(gradleKotlinDsl())
    composite(libs.spotless.plugin.gradle)
    composite(files(libs::class.java.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        create("settings") {
            id = "me.kinhiro.composite.settings"
            implementationClass = "me.kinhiro.composite.RootSettings"
        }

        create("root") {
            id = "me.kinhiro.composite.root"
            implementationClass = "me.kinhiro.composite.RootPlugin"
        }

        create("code") {
            id = "me.kinhiro.composite.code"
            implementationClass = "me.kinhiro.composite.CodePlugin"
        }
    }
}

fun DependencyHandler.composite(dependencyNotation: Any) {
    compileOnly(dependencyNotation)
    runtimeOnly(dependencyNotation)
}
