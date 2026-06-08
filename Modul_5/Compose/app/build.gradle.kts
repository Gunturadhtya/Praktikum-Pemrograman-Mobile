import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    kotlin("plugin.serialization") version "2.2.10"
}

android {
    namespace = "com.mobil.modul5compose"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.mobil.modul5compose"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    androidResources {
        localeFilters += setOf("en", "in")

        generateLocaleConfig = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    val secretProps = Properties().apply {
        val secretFile = rootProject.file("secrets.properties")
        if (secretFile.exists()) {
            load(secretFile.inputStream())
        }
    }

    defaultConfig {
        val apiKey = secretProps.getProperty("API_KEY") ?: "\"DEFAULT_KEY\""
        val readAccessToken = secretProps.getProperty("READ_ACCESS_TOKEN") ?: "\"DEFAULT_KEY\""

        buildConfigField("String", "API_KEY", apiKey)
        buildConfigField("String", "READ_ACCESS_TOKEN", readAccessToken)
    }


}

dependencies {
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.androidx.room.runtime)
    implementation(platform(libs.androidx.compose.bom.v20260500))
    implementation(libs.coil.compose)
    ksp(libs.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.rxjava2)
    implementation(libs.androidx.room.rxjava3)
    implementation(libs.androidx.room.guava)
    testImplementation(libs.androidx.room.testing)
    implementation(libs.androidx.room.paging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.timber)
}