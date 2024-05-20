plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.nyakshoot.storageservice"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nyakshoot.storageservice"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.navigation:navigation-compose:2.6.0")

//    implementation ("me.saket.swipe:swipe:1.2.0") Свайпы как в телеге, например архивация чата

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // CameraX
    implementation ("androidx.camera:camera-core:1.2.2")
    implementation ("androidx.camera:camera-camera2:1.2.2")
    implementation ("androidx.camera:camera-lifecycle:1.2.2")
    implementation ("androidx.camera:camera-view:1.2.2")

    // ML Kit Vision
    implementation ("com.google.mlkit:barcode-scanning:17.2.0")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material:material2")
    implementation("com.google.android.gms:play-services-vision-common:19.1.3")
    implementation("androidx.camera:camera-view:1.3.3")
    implementation("com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.hilt:hilt-work:1.2.0")
    implementation("androidx.test:core-ktx:1.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //tests
    testImplementation ("org.mockito:mockito-core:3.9.0")
    testImplementation ("org.mockito:mockito-inline:3.9.0")
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    // retorfit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.code.gson:gson:2.8.9")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")

    // di
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    // extended icons
    implementation("androidx.compose.material:material-icons-extended:1.5.0-alpha04")

    // datastore instead of shared preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0-beta01")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // logging interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // coil - setting images
    implementation("io.coil-kt:coil-compose:1.3.1")
}