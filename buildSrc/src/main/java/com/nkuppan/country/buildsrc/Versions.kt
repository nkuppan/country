package com.nkuppan.country.buildsrc

object Versions {
    const val compileSdk = 33
    const val minSdk = 21
    const val targetSdk = 33

    const val versionCode = 9
    const val versionName = "1.0.9"

    const val androidGradlePlugin = "7.4.1"
    const val ktlint = "0.42.1"
    const val coil = "2.2.2"
    const val playCoreUpdate = "2.0.0"

    object Kotlin {
        const val kotlin = "1.8.10"
        const val coroutines = "1.6.4"
    }

    object AndroidX {
        const val core = "1.9.0"
        const val coreTest = "2.2.0"
        const val activity = "1.7.0"
        const val fragment = "1.5.6"
        const val multiDex = "2.0.1"
        const val appCompat = "1.6.1"
        const val constraintLayout = "2.1.4"
        const val recyclerView = "1.3.0"
        const val swipeRefreshLayout = "1.1.0"
        const val lifecycle = "2.6.1"
        const val preference = "1.2.0"
        const val viewPager2 = "1.0.0"
        const val room = "2.5.1"
        const val navigation = "2.5.3"
        const val dataStore = "1.0.0"
        const val workManager = "2.8.1"
        const val cardView = "1.0.0"
        const val vectorDrawable = "1.1.0"
        const val crypto = "1.1.0-alpha01"
        const val broadcast = "1.0.0"
        const val splashScreen = "1.0.0"

        object Compose {
            const val compose = "1.4.0"
            const val composeFoundation = "1.4.0"
            const val composeMaterial = "1.4.0"
            const val activity = "1.5.0"
            const val poolingContainer = "1.0.0-rc01"
        }

        object Test {
            const val core = "1.4.0"
            const val runner = "1.4.0"
            const val rules = "1.4.0"
            const val jUnit = "1.1.3"
            const val espresso = "3.4.0"
            const val uiautomator = "2.2.0"
        }

        object Benchmark {
            const val macro = "1.2.0-alpha01"
            const val micro = "1.1.0"
        }

        object BaselineProfile {
            const val profileInstaller = "1.2.0"
        }
    }

    object Google {
        const val services = "4.3.15"
        const val truth = "1.1.3"
        const val material = "1.8.0"
        const val material3Compose = "1.1.0-rc01"
        const val hilt = "2.44"
        const val hiltNavigationCompose = "1.1.0-alpha01"

        object OssLicenses {
            const val ossLicenses = "17.0.0"
            const val gradlePlugin = "0.10.5"
        }

        object Services {
            const val auth = "20.4.1"
            const val map = "18.1.0"
        }

        object Play {
            const val core = "2.0.0"
        }
    }

    object Square {
        const val okHttp = "4.9.1"
        const val retrofit = "2.9.0"
        const val logging = "4.2.1"
    }

    object Test {
        const val jUnitCore = "4.13.2"
        const val cashTurbine = "0.7.0"
    }

    object Mockito {
        const val core = "3.3.3"
        const val ktx = "4.0.0"
        const val android = "4.3.1"
    }

    object Jacoco {
        const val gradle = "0.8.7"
    }

    object Firebase {
        const val bom = "30.1.0"

        object Crashlytics {
            const val gradlePlugin = "2.4.1"
        }
    }

    object ThirdParty {
        const val couchbase = "3.0.0"
        const val mpcharts = "v3.1.0"
        const val colorpicker = "2.2.4"
        const val materialDialog = "0.9.0"
    }
}