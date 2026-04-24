import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.google.firebase.appdistribution.gradle.firebaseAppDistribution
import org.gradle.kotlin.dsl.main

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

        versionCode = 6
        versionName = "0.6.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        android.buildFeatures.buildConfig = true

        // noinspection WrongGradleMethod
        ksp {
            arg ("room.schemaLocation", "$projectDir/schemas")
        }

    }

    // Local
    val googleClientId: String = gradleLocalProperties(rootDir, providers).getProperty("google.clientId")
    val googleServiceAccountFileName: String = gradleLocalProperties(rootDir, providers).getProperty("google.serviceAccount.file")

    buildTypes {
        create("development"){
            initWith(getByName("debug"))
            isMinifyEnabled = false
            isDebuggable = true
            versionNameSuffix = "-dev"
            buildConfigField("String", "GOOGLE_CLIENT_IP", googleClientId)
            firebaseAppDistribution {
                artifactType = "APK"
                releaseNotes = "Development version"
                groups = "dev-qa"
                serviceCredentialsFile = "$rootDir/$googleServiceAccountFileName"
            }
        }
        create("staging"){
            initWith(getByName("development"))
            isMinifyEnabled = true
            isDebuggable = false
            versionNameSuffix = "-staging"
            buildConfigField("String", "GOOGLE_CLIENT_IP", googleClientId)
            firebaseAppDistribution {
                artifactType = "APK"
                releaseNotes = "Development version"
                groups = "dev-qa"
                serviceCredentialsFile = "$rootDir/$googleServiceAccountFileName"
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

    testBuildType = "development"

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
    implementation(libs.google.firebase.auth)
    implementation(libs.google.firebase.firestore)

    // Credential Manager
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play)
    implementation(libs.android.identity)

    // TESTS [GENERAL]
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
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