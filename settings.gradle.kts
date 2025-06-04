pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Este es importante si estás usando versiones alfa de bibliotecas como Accompanist:
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
        // Opcional si otras dependencias están en JitPack
        maven { url = uri("https://jitpack.io") }
    }
}


rootProject.name = "Lab4_GpsWhatsappApp"
include(
    ":app",
    ":BackgroundLocation",
    ":SendMessageWhatsappCompose")
project(":BackgroundLocation").projectDir = file("C:/Users/lopez/AndroidStudioProjects/BackgroundLocation/app")
project(":SendMessageWhatsappCompose").projectDir = file("C:/Users/lopez/AndroidStudioProjects/SendMessageWhatsappCompose/app")