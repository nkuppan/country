package com.nkuppan.country.buildsrc

object Versions {
    const val compileSdk = 33
    const val minSdk = 21
    const val targetSdk = 33

    const val versionCode = 14
    const val versionName = "1.0.14"

    const val groupId = "com.github.nkuppan"

    object Kotlin {
        const val kotlin = "1.8.10"
        const val coroutines = "1.6.4"
    }

    object AndroidX {
        const val core = "1.9.0"
        const val activity = "1.7.0"
        const val fragment = "1.5.6"
        const val coreTest = "2.2.0"
        const val appCompat = "1.6.1"
        const val constraintLayout = "2.1.4"
        const val recyclerView = "1.3.0"
        const val lifecycle = "2.6.1"

        object Compose {
            const val composeBom = "2023.04.01"
            const val composeCompilerVersion = "1.4.4"
        }

        object Test {
            const val core = "1.4.0"
            const val runner = "1.4.0"
            const val rules = "1.4.0"
            const val jUnit = "4.13.2"
            const val jUnitExt = "1.1.5"
            const val espresso = "3.4.0"
        }
    }

    object Google {
        const val truth = "1.1.3"
        const val material = "1.8.0"
    }
    object Test {
        const val cashTurbine = "0.7.0"
    }

    object Mockito {
        const val core = "3.3.3"
        const val ktx = "4.0.0"
        const val android = "4.3.1"
    }
}