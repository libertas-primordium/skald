import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.desktop.application.tasks.AbstractJPackageTask
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop") {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.runtime)
                implementation(compose.ui)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.activity.compose)
                implementation(libs.bdk.android)
                implementation(libs.bouncycastle.provider)
                implementation(libs.tink.android)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.bdk.jvm)
                implementation(libs.bouncycastle.provider)
                implementation(libs.tink.jvm)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val androidInstrumentedTest by getting {
            dependencies {
                implementation(libs.androidx.test.ext.junit)
                implementation(libs.androidx.test.runner)
            }
        }
    }
}

val packageDebJpackageTempDir = layout.buildDirectory.dir("compose/tmp/packageDeb-jpackage")

tasks.withType<AbstractJPackageTask>().configureEach {
    if (name == "packageDeb") {
        freeArgs.addAll(
            providers.provider {
                listOf("--temp", packageDebJpackageTempDir.get().asFile.absolutePath)
            },
        )

        doFirst {
            val tempDir = packageDebJpackageTempDir.get().asFile
            if (tempDir.exists()) {
                tempDir.deleteRecursively()
            }
            tempDir.mkdirs()
        }
    }
}

tasks.withType<Test>().configureEach {
    val configuredForks = providers
        .gradleProperty("skald.test.maxParallelForks")
        .map { it.toIntOrNull() ?: 1 }
        .orElse(1)
    val diagnosticsEnabled = providers
        .gradleProperty("skald.test.diagnostics.enabled")
        .map { it.equals("true", ignoreCase = true) }
        .orElse(false)

    maxParallelForks = configuredForks.get().coerceAtLeast(1)

    providers.gradleProperty("skald.test.diagnostics.enabled").orNull?.let { enabled ->
        systemProperty("skald.test.diagnostics.enabled", enabled)
    }
    providers.gradleProperty("skald.test.timingFile").orNull?.takeIf { it.isNotBlank() }?.let { timingFile ->
        systemProperty("skald.test.timingFile", timingFile)
    }
    testLogging {
        showStandardStreams = providers
            .gradleProperty("skald.test.showStandardStreams")
            .map { it.equals("true", ignoreCase = true) }
            .orElse(false)
            .get()
        if (diagnosticsEnabled.get()) {
            events(
                TestLogEvent.STARTED,
                TestLogEvent.PASSED,
                TestLogEvent.SKIPPED,
                TestLogEvent.FAILED,
            )
        }
    }
}

android {
    namespace = "com.libertasprimordium.skald"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.libertasprimordium.skald"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0-dev"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packaging {
        resources {
            excludes += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.libertasprimordium.skald.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Deb)
            packageName = "skald"
            packageVersion = "0.1.0"
            description = "Sovereign multi-rail Bitcoin wallet scaffold for advanced users"
            copyright = "Copyright 2026 Libertas Primordium"

            linux {
                appCategory = "Finance"
                debMaintainer = "Libertas Primordium"
                menuGroup = "Finance"
                shortcut = true
            }
        }
    }
}
