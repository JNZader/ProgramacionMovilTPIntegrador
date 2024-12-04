// Configuración de plugins para el proyecto
plugins {
    // Se utiliza el plugin de Android Application para crear una aplicación Android
    alias(libs.plugins.android.application)
}

android {
    // Configuración de la aplicación
    namespace = "com.example.tpi" // Identificador de la aplicación
    compileSdk = 34 // Versión del SDK de compilación

    defaultConfig {
        // Configuración de la aplicación por defecto
        applicationId = "com.example.tpi" // Identificador de la aplicación
        minSdk = 24 // Versión mínima del SDK que soporta la aplicación
        //noinspection OldTargetApi
        targetSdk = 34 // Versión del SDK objetivo
        versionCode = 1 // Código de versión de la aplicación
        versionName = "1.0" // Nombre de la versión de la aplicación

        // Configuración del ejecutor de pruebas
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        resValue ("string", "back4app_server_url", "\"${project.findProperty("BACK4APP_SERVER_URL".toString())}\"")
        resValue ("string", "back4app_app_id", "\"${project.findProperty("BACK4APP_APPLICATION_ID".toString())}\"")
        resValue ("string", "back4app_client_key", "\"${project.findProperty("BACK4APP_CLIENT_KEY".toString())}\"")
    }

    // Configuración de los tipos de compilación
    buildTypes {
        // Configuración del tipo de compilación de liberación
        release {
            // Desactiva la minimización del código
            isMinifyEnabled = false
            // Configuración de los archivos de ProGuard
            proguardFiles(
                // Archivo de ProGuard por defecto
                getDefaultProguardFile("proguard-android-optimize.txt"),
                // Archivo de ProGuard personalizado
                "proguard-rules.pro"
            )
        }
    }

    // Configuración de las opciones de compilación
    compileOptions {
        // Configuración de la compatibilidad de Java
        sourceCompatibility = JavaVersion.VERSION_21 // Versión de Java de la fuente
        targetCompatibility = JavaVersion.VERSION_21 // Versión de Java objetivo
    }

    // Configuración de las características de construcción
    buildFeatures {
        // Habilita la vista de enlace
        viewBinding = true
        // Habilita la configuración de construcción
        buildConfig = true
    }
}

// Configuración de dependencias
dependencies {
    // Dependencias de la aplicación
    implementation(libs.appcompat) // Biblioteca de Android Support
    implementation(libs.material) // Biblioteca de Material Design
    implementation(libs.activity) // Biblioteca de Activity
    implementation(libs.constraintlayout) // Biblioteca de Layout de Restricciones

    // Para manejar imágenes
    implementation(libs.picasso) // Biblioteca de manejo de imágenes

    // Circle Image View
    implementation(libs.circleimageview) // Biblioteca de vista de imagen circular

    // Shape Of View
    implementation(libs.shapeofview) // Biblioteca de vista de forma

    // Dependencias de pruebas
    testImplementation(libs.junit) // Biblioteca de pruebas de JUnit
    androidTestImplementation(libs.ext.junit) // Biblioteca de pruebas de JUnit para Android
    androidTestImplementation(libs.espresso.core) // Biblioteca de pruebas de Espresso

    // Parse SDK
    implementation(libs.bolts.tasks)
    implementation(libs.parse)

}