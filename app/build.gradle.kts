plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    kotlin("plugin.serialization") version "1.9.23"
}

android {
    namespace = "ru.tanexc.meal"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.tanexc.meal"
        minSdk = 24
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
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Serialization kotlinx
    implementation(libs.kotlinx.serialization.json)

    // Dagger Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)

    // ComposeShadowPlus
    implementation(libs.composeshadowsplus)

    // Dynamic theme
    implementation(libs.t8rin.dynamictheme)

    // Datastore
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences.core)
    implementation(libs.androidx.datastore.preferences)

    // Accompanist
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.accompanist.adaptive)

    // Navigation
    implementation(libs.reimagined)
    implementation(libs.reimagined.hilt)

    // Material icons extended
    implementation(libs.androidx.material.icons.extended)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    annotationProcessor(libs.androidx.room.compiler)
}