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
        }

        object Test {
            const val runner = "androidx.test:runner:${Versions.AndroidX.Test.runner}"
            const val rules = "androidx.test:rules:${Versions.AndroidX.Test.rules}"
            const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            object Core {
                const val ktx = "androidx.test:core-ktx:${Versions.AndroidX.Test.core}"
            }

            object JUnit {
                const val core = "junit:junit:${Versions.AndroidX.Test.jUnit}"
                const val ktx = "androidx.test.ext:junit-ktx:${Versions.AndroidX.Test.jUnit}"
            }

            object Espresso {
                const val core =
                    "androidx.test.espresso:espresso-core:${Versions.AndroidX.Test.espresso}"
                const val contrib =
                    "androidx.test.espresso:espresso-contrib:${Versions.AndroidX.Test.espresso}"
            }

            object UiAutomator {
                const val uiautomator =
                    "androidx.test.uiautomator:uiautomator:${Versions.AndroidX.Test.uiautomator}"
            }
        }

        object Compose {
            const val bom =
                "androidx.compose:compose-bom:${Versions.AndroidX.Compose.activity}"
            const val activity =
                "androidx.activity:activity-compose:${Versions.AndroidX.Compose.activity}"
            const val poolingContainer =
                "androidx.customview:customview-poolingcontainer:${Versions.AndroidX.Compose.poolingContainer}"
            const val runtime =
                "androidx.compose.runtime:runtime:${Versions.AndroidX.Compose.compose}"
            const val animation =
                "androidx.compose.animation:animation:${Versions.AndroidX.Compose.compose}"
            const val foundation =
                "androidx.compose.foundation:foundation:${Versions.AndroidX.Compose.composeFoundation}"
            const val foundationLayout =
                "androidx.compose.foundation:foundation-layout:${Versions.AndroidX.Compose.composeFoundation}"
            const val viewBinding =
                "androidx.compose.ui:ui-viewbinding:${Versions.AndroidX.Compose.compose}"
            const val ui = "androidx.compose.ui:ui-util:${Versions.AndroidX.Compose.compose}"
            const val uiUtils = "androidx.compose.ui:ui:${Versions.AndroidX.Compose.compose}"
            const val uiText =
                "androidx.compose.ui:ui-text-google-fonts:${Versions.AndroidX.Compose.compose}"
            const val uiTooling =
                "androidx.compose.ui:ui-tooling:${Versions.AndroidX.Compose.compose}"
            const val uiToolingPreview =
                "androidx.compose.ui:ui-tooling-preview:${Versions.AndroidX.Compose.compose}"
            const val material =
                "androidx.compose.material:material:${Versions.AndroidX.Compose.composeMaterial}"
            const val materialIcon =
                "androidx.compose.material:material-icons-extended:${Versions.AndroidX.Compose.composeMaterial}"
            const val uiTestManifest =
                "androidx.compose.ui:ui-test-manifest:${Versions.AndroidX.Compose.compose}"
        }
    }

    object Google {

        const val material = "com.google.android.material:material:${Versions.Google.material}"
        const val truth = "com.google.truth:truth:${Versions.Google.truth}"
    }


    object Mockito {
        const val core = "org.mockito:mockito-core:${Versions.Mockito.core}"
        const val ktx = "org.mockito.kotlin:mockito-kotlin:${Versions.Mockito.ktx}"
        const val android = "org.mockito:mockito-android:${Versions.Mockito.android}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.Test.jUnitCore}"
        const val cashTurbine = "app.cash.turbine:turbine:${Versions.Test.cashTurbine}"
    }

    object ThirdParty {
        const val gson = "com.google.code.gson:gson:2.8.0"
    }
}
