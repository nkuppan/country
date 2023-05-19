package com.nkuppan.country.buildsrc

object Libs {

    object Kotlin {
        object Coroutines {
            const val core =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
            const val android =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
            const val test =
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.coroutines}"
        }
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerView}"

        object Core {
            const val ktx = "androidx.core:core-ktx:${Versions.AndroidX.core}"
            const val test = "androidx.arch.core:core-testing:${Versions.AndroidX.coreTest}"
        }

        object Lifecycle {
            const val viewModelKtx =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
            const val liveDataKtx =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.lifecycle}"
            const val runtime =
                "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}"
            const val compose =
                "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.AndroidX.lifecycle}"
        }

        object Activity {
            const val ktx = "androidx.activity:activity-ktx:${Versions.AndroidX.activity}"
        }

        object Fragment {
            const val ktx = "androidx.fragment:fragment-ktx:${Versions.AndroidX.fragment}"
        }

        object Test {
            const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            object Core {
                const val ktx = "androidx.test:core-ktx:${Versions.AndroidX.Test.core}"
            }

            object JUnit {
                const val core = "junit:junit:${Versions.AndroidX.Test.jUnit}"
                const val ktx = "androidx.test.ext:junit-ktx:${Versions.AndroidX.Test.jUnitExt}"
            }

            object Espresso {
                const val core =
                    "androidx.test.espresso:espresso-core:${Versions.AndroidX.Test.espresso}"
            }
        }

        object Compose {
            const val bom = "androidx.compose:compose-bom:${Versions.AndroidX.Compose.composeBom}"

            const val material = "androidx.compose.material:material"
            const val material3 = "androidx.compose.material3:material3"
            const val materialIcon = "androidx.compose.material:material-icons-core"
            const val materialIconExtended = "androidx.compose.material:material-icons-extended"
            const val uiTooling = "androidx.compose.ui:ui-tooling"
            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
            const val foundation = "androidx.compose.foundation:foundation"
            const val foundationLayout = "androidx.compose.foundation:foundation-layout"
            const val ui = "androidx.compose.ui:ui"
            const val activity = "androidx.activity:activity-compose:1.7.1"
            const val runtime = "androidx.compose.runtime:runtime"
            const val viewBinding = "androidx.compose.ui:ui-viewbinding"
        }
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.Google.material}"
        const val truth = "com.google.truth:truth:${Versions.Google.truth}"
    }

    object Test {
        const val cashTurbine = "app.cash.turbine:turbine:${Versions.Test.cashTurbine}"
    }

    object ThirdParty {
        const val gson = "com.google.code.gson:gson:2.8.0"
    }
}
