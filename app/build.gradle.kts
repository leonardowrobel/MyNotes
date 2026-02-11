import com.google.firebase.appdistribution.gradle.firebaseAppDistribution
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.firebase.appdistribution)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.serialization)
    alias(libs.plugins.google.gms)
}

android {
    namespace = "com.lw.mynotes"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.lw.mynotes"
        minSdk = 34
        targetSdk = 35

        versionCode = 1
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // noinspection WrongGradleMethod
        ksp {
            arg ("room.schemaLocation", "$projectDir/schemas")
        }

    }

    buildTypes {
        create("development"){
            initWith(getByName("debug"))
            isMinifyEnabled = false
            versionNameSuffix = "-dev"
            firebaseAppDistribution {
                artifactType = "APK"
                releaseNotes = "Development version"
                groups = "dev-qa"
                serviceCredentialsFile = "$rootDir/my-notes-dev-24774-ea1adae1d3e4.json"
            }
        }
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
    buildFeatures {
        compose = true
    }
    hilt {
        enableAggregatingTask = false
    }
}

configurations.implementation{
    exclude(group = "com.intellij", module = "annotations")
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // ROOM DB
    implementation(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // DAGGER-HILT
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // COMPOSE
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    // NAVIGATION
    implementation(libs.androidx.hilt.navigation)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.ndff)

    implementation(libs.kotlinx.serialization.json)

    // FIREBASE
    implementation(platform(libs.google.firebase.bom))

    // TESTS [GENERAL]
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.google.truth)
    androidTestImplementation(libs.android.arch.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    // TODO: review this
    // TESTS [DAGGER-HILT]
//    androidTestImplementation(libs.hilt)
    // TESTS [NAVIGATION]
    androidTestImplementation(libs.androidx.navigation.testing)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}