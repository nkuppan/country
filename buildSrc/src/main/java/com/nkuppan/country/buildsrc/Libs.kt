package com.nkuppan.country.buildsrc

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"

    const val playCoreUpdate = "com.google.android.play:app-update:${Versions.playCoreUpdate}"
    const val playCoreUpdateKtx =
        "com.google.android.play:app-update-ktx:${Versions.playCoreUpdate}"

    object Glide {
        const val core = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object Coil {
        const val core = "io.coil-kt:coil:${Versions.coil}"
        const val compose = "io.coil-kt:coil-compose:${Versions.coil}"
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
        const val multiDex = "androidx.multidex:multidex:${Versions.AndroidX.multiDex}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerView}"
        const val swipeRefreshLayout =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.AndroidX.swipeRefreshLayout}"
        const val preference = "androidx.preference:preference-ktx:${Versions.AndroidX.preference}"
        const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.AndroidX.viewPager2}"
        const val cardView = "androidx.cardview:cardview:${Versions.AndroidX.cardView}"
        const val vectorDrawable =
            "androidx.vectordrawable:vectordrawable:${Versions.AndroidX.vectorDrawable}"
        const val splashScreen =
            "androidx.core:core-splashscreen:${Versions.AndroidX.splashScreen}"

        const val broadcast =
            "androidx.localbroadcastmanager:localbroadcastmanager:${Versions.AndroidX.broadcast}"
        const val cryptoPreference = "androidx.security:security-crypto:${Versions.AndroidX.crypto}"

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
            const val compose =
                "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.AndroidX.lifecycle}"
            const val runtime =
                "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}"
            const val runtimeCompose =
                "androidx.lifecycle:lifecycle-runtime-compose:${Versions.AndroidX.lifecycle}"
        }

        object Room {
            const val runtime = "androidx.room:room-runtime:${Versions.AndroidX.room}"
            const val compiler = "androidx.room:room-compiler:${Versions.AndroidX.room}"
            const val ktx = "androidx.room:room-ktx:${Versions.AndroidX.room}"
            const val testing = "androidx.room:room-testing:${Versions.AndroidX.room}"
        }

        object Navigation {
            const val uiKtx =
                "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}"
            const val fragmentKtx =
                "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}"
            const val compose =
                "androidx.navigation:navigation-compose:${Versions.AndroidX.navigation}"
            const val safeArgsGradlePlugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.AndroidX.navigation}"

            const val test =
                "androidx.navigation:navigation-testing:${Versions.AndroidX.navigation}"
        }

        object DataStore {
            const val preferences =
                "androidx.datastore:datastore-preferences:${Versions.AndroidX.dataStore}"
        }

        object WorkManager {
            const val runtimeKtx =
                "androidx.work:work-runtime-ktx:${Versions.AndroidX.workManager}"
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

        object Benchmark {
            const val macroBenchmark =
                "androidx.benchmark:benchmark-macro-junit4:${Versions.AndroidX.Benchmark.macro}"
            const val microBenchmark =
                "androidx.benchmark:benchmark-junit4:${Versions.AndroidX.Benchmark.macro}"
            const val benchmarkGradlePlugin =
                "androidx.benchmark:benchmark-gradle-plugin:${Versions.AndroidX.Benchmark.micro}"
        }

        object BaselineProfile {
            const val profileInstaller =
                "androidx.profileinstaller:profileinstaller:${Versions.AndroidX.BaselineProfile.profileInstaller}"
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
        const val servicesPlugin = "com.google.gms:google-services:${Versions.Google.services}"
        const val material = "com.google.android.material:material:${Versions.Google.material}"
        const val material3Compose =
            "androidx.compose.material3:material3:${Versions.Google.material3Compose}"
        const val truth = "com.google.truth:truth:${Versions.Google.truth}"

        object Services {
            const val auth =
                "com.google.android.gms:play-services-auth:${Versions.Google.Services.auth}"
            const val map =
                "com.google.android.gms:play-services-maps:${Versions.Google.Services.map}"
        }

        object Play {
            const val review = "com.google.android.play:review-ktx:${Versions.Google.Play.core}"
        }

        object Hilt {
            const val android = "com.google.dagger:hilt-android:${Versions.Google.hilt}"
            const val hiltCompiler =
                "com.google.dagger:hilt-android-compiler:${Versions.Google.hilt}"
            const val androidGradlePlugin =
                "com.google.dagger:hilt-android-gradle-plugin:${Versions.Google.hilt}"
            const val hiltNavigationCompose =
                "androidx.hilt:hilt-navigation-compose:${Versions.Google.hiltNavigationCompose}"
            const val test = "com.google.dagger:hilt-android-testing:${Versions.Google.hilt}"
        }

        object OssLicenses {
            const val ossLicenses =
                "com.google.android.gms:play-services-oss-licenses:${Versions.Google.OssLicenses.ossLicenses}"
            const val gradlePlugin =
                "com.google.android.gms:oss-licenses-plugin:${Versions.Google.OssLicenses.gradlePlugin}"
        }
    }

    object Square {
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.Square.okHttp}"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.Square.okHttp}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.Square.okHttp}"

        object Retrofit {
            const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Square.retrofit}"
            const val gsonConverter =
                "com.squareup.retrofit2:converter-gson:${Versions.Square.retrofit}"
            const val logging =
                "com.squareup.okhttp3:logging-interceptor:${Versions.Square.logging}"
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

    object Jacoco {
        const val gradle = "org.jacoco:org.jacoco.core:${Versions.Jacoco.gradle}"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:${Versions.Firebase.bom}"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
        const val ads = "com.google.android.gms:play-services-ads:21.5.0"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
        const val messaging = "com.google.firebase:firebase-messaging-ktx"
        const val config = "com.google.firebase:firebase-config-ktx"

        object Crashlytics {
            const val gradlePlugin =
                "com.google.firebase:firebase-crashlytics-gradle:${Versions.Firebase.Crashlytics.gradlePlugin}"
        }
    }

    object ThirdParty {
        const val couchbase =
            "com.couchbase.lite:couchbase-lite-android-ktx:${Versions.ThirdParty.couchbase}"
        const val mpcharts = "com.github.PhilJay:MPAndroidChart:${Versions.ThirdParty.mpcharts}"
        const val colorpicker =
            "com.github.skydoves:colorpickerview:${Versions.ThirdParty.colorpicker}"
        const val gson = "com.google.code.gson:gson:2.8.0"
        const val timber = "com.jakewharton.timber:timber:5.0.1"
        const val materialDialog =
            "io.github.vanpra.compose-material-dialogs:core:${Versions.ThirdParty.materialDialog}"
    }
}
