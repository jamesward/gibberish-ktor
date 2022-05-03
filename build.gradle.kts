plugins {
    //kotlin("jvm") version "1.6.21"
    application
    kotlin("multiplatform") version "1.6.21"
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("MainKt")
}

kotlin {
    linuxX64 {
        binaries {
            executable(listOf(DEBUG, RELEASE)) {
                entryPoint = "main"
            }
        }
    }

    jvm {
        withJava()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-core:2.0.1")
                implementation("io.ktor:ktor-server-cio:2.0.1")
                implementation("io.ktor:ktor-client-core:2.0.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1-native-mt")
            }
        }

        val linuxX64Main by getting {
            dependencies {
                implementation("io.ktor:ktor-client-curl:2.0.1")
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio:2.0.1")
            }
        }
    }
}
