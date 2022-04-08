package com.nkuppan.country.buildsrc

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"

    object Glide {
        const val core = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.kotlin}"
        const val gradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.kotlin}"

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
        const val swipeRefreshLayout =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.AndroidX.swipeRefreshLayout}"

        object Core {
            const val ktx = "androidx.core:core-ktx:${Versions.AndroidX.core}"
            const val test = "androidx.arch.core:core-testing:${Versions.AndroidX.coreTest}"
        }

        object Activity {
            const val ktx = "androidx.activity:activity-ktx:${Versions.AndroidX.activity}"
        }

        object Fragment {
            const val ktx = "androidx.fragment:fragment-ktx:${Versions.AndroidX.fragment}"
            const val test = "androidx.fragment:fragment-testing:${Versions.AndroidX.fragment}"
        }

        object Lifecycle {
            const val viewModelKtx =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
            const val liveDataKtx =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.lifecycle}"
            const val runtime =
                "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}"
        }

        object Room {
            const val runtime = "androidx.room:room-runtime:${Versions.AndroidX.room}"
            const val compiler = "androidx.room:room-compiler:${Versions.AndroidX.room}"
            const val ktx = "androidx.room:room-ktx:${Versions.AndroidX.room}"
            const val testing = "androidx.room:room-testing:${Versions.AndroidX.room}"
        }

        object Paging {
            const val runtime = "androidx.paging:paging-runtime:${Versions.AndroidX.paging}"
        }

        object Navigation {
            const val uiKtx =
                "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}"
            const val fragmentKtx =
                "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}"
            const val safeArgsGradlePlugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.AndroidX.navigation}"

            const val test =
                "androidx.navigation:navigation-testing:${Versions.AndroidX.navigation}"
        }

        object Hilt {
            const val compiler = "androidx.hilt:hilt-compiler:${Versions.AndroidX.hilt}"
            const val work = "androidx.hilt:hilt-work:${Versions.AndroidX.hilt}"
        }

        object DataStore {
            const val preferences =
                "androidx.datastore:datastore-preferences:${Versions.AndroidX.dataStore}"
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
                    "com.android.support.test.espresso:espresso-contrib:${Versions.AndroidX.Test.espresso_contrib}"
            }
        }
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.Google.material}"
        const val gson = "com.google.code.gson:gson:${Versions.Google.gson}"

        object Hilt {
            const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.Google.hilt}"
            const val android = "com.google.dagger:hilt-android:${Versions.Google.hilt}"
            const val androidGradlePlugin =
                "com.google.dagger:hilt-android-gradle-plugin:${Versions.Google.hilt}"
        }
    }

    object Square {
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.Square.okHttp}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.Square.okHttp}"

        object Retrofit {
            const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Square.retrofit}"
            const val gsonConverter =
                "com.squareup.retrofit2:converter-gson:${Versions.Square.retrofit}"
        }
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

    object Robolectric {
        const val core = "org.robolectric:robolectric:${Versions.Robolectric.core}"
    }

    object Jacoco {
        const val gradle = "org.jacoco:org.jacoco.core:${Versions.Jacoco.gradle}"
    }
}
