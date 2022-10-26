/**
 * Created by rivaldy on 01/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

object Versions {
    const val kotlin_version = "1.5.31"
    const val hilt_version = "2.38.1"
    const val lifecycle_extensions_version = "1.1.1"
    const val core_ktx_version = "1.6.0"
    const val app_compat_version = "1.3.1"
    const val material_version = "1.4.0"
    const val constraint_version = "2.1.1"
    const val junit_version = "4.13.2"
    const val junit_test_version = "1.1.3"
    const val espresso_test_version = "3.4.0"
    const val ui_ktx_version = "2.3.5"
    const val spin_kit_version = "1.4.0"
    const val picasso_version = "2.71828"
    const val retrofit_version = "2.9.0"
    const val okHttp3_version = "4.9.0"
    const val lifecycle_version = "2.2.0"
    const val arch_version = "2.1.0"
    const val activity_version = "1.3.1"
    const val fragment_version = "1.3.6"
    const val hilt_viewModels = "1.0.0-alpha03"
    const val kotlin_coroutines_version = "1.5.0"
    const val room_version = "2.3.0"
    const val room_runtime_version = "2.2.5"
    const val data_store_version = "1.0.0-rc01"
    const val glide_version = "4.11.0"

    const val compile_sdk = 31
    const val build_tools_version = "30.0.3"
    const val min_sdk = 23
    const val target_sdk = 31
    const val version_code = 1
    const val version_name = "1.0"
    const val paging_version = "3.1.1"
    const val room_paging_version = "2.4.0-rc01"
}

object MyDependencies {
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.app_compat_version}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_version}"
    const val junit = "junit:junit:${Versions.junit_version}"
    const val test_ext_junit = "androidx.test.ext:junit:${Versions.junit_test_version}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_test_version}"

    //UI
    const val material = "com.google.android.material:material:${Versions.material_version}"
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso_version}"
    const val ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.ui_ktx_version}"
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.ui_ktx_version}"

    // REMOTE
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofit2_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    const val retrofit2_adapter_rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit_version}"
    const val okhttp3 = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp3_version}"

    // LOCAL STORAGE
    const val room = "androidx.room:room-runtime:${Versions.room_version}"
    const val room_kapt = "androidx.room:room-compiler:${Versions.room_version}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"

    // Coroutines
    const val kotlinx_coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlin_coroutines_version}"

    // Loading Progress - SpinKit
    const val spin_kit = "com.github.ybq:Android-SpinKit:${Versions.spin_kit_version}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"

    //DataStore
    const val datastore_preferences = "androidx.datastore:datastore-preferences:${Versions.data_store_version}"

    // Lifecycle KTX
    const val lifecycle_extensions = "android.arch.lifecycle:extensions:${Versions.lifecycle_extensions_version}"

    // Activity & Fragment KTX
    const val activity_ktx = "androidx.activity:activity-ktx:${Versions.activity_version}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_version}"

    // DI - Hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    const val hilt_kapt = "com.google.dagger:hilt-compiler:${Versions.hilt_version}"

    // ViewModel with Hilt
    const val hilt_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_viewModels}"

    // Paging
    const val paging = "androidx.paging:paging-runtime:${Versions.paging_version}"
    const val room_paging = "androidx.room:room-paging:${Versions.room_paging_version}"
}