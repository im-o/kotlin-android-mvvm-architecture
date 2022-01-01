plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("kotlin-platform-android")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.compile_sdk
    buildToolsVersion = Versions.build_tools_version

    defaultConfig {
        applicationId = "com.rivaldy.id.mvvmtemplateapp"
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
        versionCode = Versions.version_code
        versionName = Versions.version_name
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", "\"8b904530a7aced766995fa063ed27355\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(MyDependencies.core_ktx)
    implementation(MyDependencies.appcompat)
    implementation(MyDependencies.constraint_layout)
    testImplementation(MyDependencies.junit)
    androidTestImplementation(MyDependencies.test_ext_junit)
    androidTestImplementation(MyDependencies.espresso_core)

    // UI
    implementation(MyDependencies.material)
    implementation(MyDependencies.picasso)
    implementation(MyDependencies.ui_ktx)
    implementation(MyDependencies.navigation_fragment_ktx)

    // REMOTE
    implementation(MyDependencies.retrofit)
    implementation(MyDependencies.retrofit2_converter_gson)
    implementation(MyDependencies.retrofit2_adapter_rxjava2)
    implementation(MyDependencies.okhttp3)

    // LOCAL STORAGE
    implementation(MyDependencies.room)
    kapt(MyDependencies.room_kapt)
    implementation(MyDependencies.room_ktx) // optional - Kotlin Extensions and Coroutines support for Room

    // Coroutines
    testImplementation(MyDependencies.kotlinx_coroutines_test)

    // Loading Progress - SpinKit
    implementation(MyDependencies.spin_kit)

    // Glide
    implementation(MyDependencies.glide)

    //DataStore
    implementation(MyDependencies.datastore_preferences)

    // Lifecycle KTX
    implementation(MyDependencies.lifecycle_extensions)
    // Activity & Fragment KTX
    implementation(MyDependencies.activity_ktx)
    implementation(MyDependencies.fragment_ktx)

    // DI - Hilt
    implementation(MyDependencies.hilt)
    kapt(MyDependencies.hilt_kapt)

    // ViewModel with Hilt
    implementation(MyDependencies.hilt_viewmodel)
}

repositories {
    mavenCentral()
}