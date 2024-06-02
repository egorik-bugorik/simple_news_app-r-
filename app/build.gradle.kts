import buildSrc.src.main.java.Browser
import buildSrc.src.main.java.Coil
import buildSrc.src.main.java.DaggerHilt
import buildSrc.src.main.java.Navigation
import buildSrc.src.main.java.Paging
import buildSrc.src.main.java.Retrofit
import buildSrc.src.main.java.Room

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id ("androidx.room")
    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "by.gorik.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "by.gorik.newsapp"
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
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "API_KEY", "\"babcf6ba838e40258ba7125711a4eab2\"")

        }
        hilt {
            enableAggregatingTask = true
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
        buildConfig = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.1")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.ui:ui-android:1.7.0-alpha05")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.browser:browser:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.05.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation(Retrofit.retrofitItself)
    implementation(Retrofit.gsonConvrt)
    kapt(DaggerHilt.compiler)
    implementation(DaggerHilt.hilt)
    implementation(DaggerHilt.hiltNavigatioCompose)

    implementation(Room.roomRun)
    implementation(Room.ktx)
    kapt(Room.compiler)


    implementation(Paging.pagingRuntime)
    implementation(Paging.compose)


    implementation(Navigation.navigationUi)
    implementation(Navigation.navigationCompose)

    implementation(Coil.coilCompose)

    implementation(Browser.browser)

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
//    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

}
