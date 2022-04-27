plugins {
    kotlin("multiplatform") version "1.6.21"
}

repositories {
    mavenCentral()
}

kotlin {
    linuxX64 {
        binaries {
            executable(listOf(DEBUG, RELEASE)) {
                entryPoint = "main"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-core:2.0.0")
                implementation("io.ktor:ktor-server-cio:2.0.0")
                implementation("io.ktor:ktor-client-core:2.0.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1-native-mt")
            }
        }

        val linuxX64Main by getting {
            dependencies {
                implementation("io.ktor:ktor-client-curl:2.0.0")
            }
        }
    }
}
