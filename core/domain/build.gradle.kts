plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrainsKotlinKapt)
    alias(libs.plugins.googleDaggerHiltAndroid)
}

android {
    namespace = "com.antonio.domain"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    api(project(":core:model"))
    api(project(":core:data"))

    // Dagger-Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Paging compose
    implementation(libs.androidx.paging.compose)

    // Testing
    testImplementation(libs.mockito.core)
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
}