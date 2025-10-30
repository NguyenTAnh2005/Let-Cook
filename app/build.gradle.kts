plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.let__cook"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.let__cook"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    packaging {
        resources {
            excludes += setOf(
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/LICENSE.md",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/NOTICE.md",
                "META-INF/DEPENDENCIES",
                "META-INF/INDEX.LIST"
            )
        }
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("androidx.recyclerview:recyclerview:1.3.1")
    // thư viện hỗ trợ chọn ngày tháng
    implementation("com.github.florent37:singledateandtimepicker:2.2.7")
    // Hỗ trợ gửi mail
    implementation ("com.sun.mail:android-mail:1.6.7")
    implementation ("com.sun.mail:android-activation:1.6.7")

}