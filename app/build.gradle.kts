plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.kairosapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.kairosapplication"
        minSdk = 29
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    lint {
        lintConfig = file("lint.xml")
        abortOnError = true
        checkReleaseBuilds = false
    }
}

val renameInvalidSw512Drawable by tasks.registering {
    doLast {
        val from = file("src/main/res/drawable-sw512")
        val to = file("src/main/res/drawable-sw512dp")
        if (from.exists()) {
            if (to.exists()) {
                to.deleteRecursively()
            }
            check(from.renameTo(to)) { "rename drawable-sw512 failed" }
        }
    }
}

tasks.named("preBuild").configure {
    dependsOn(renameInvalidSw512Drawable)
}

tasks.register("scanHardcodedText") {
    group = "verification"
    description =
        "Fails the build if main Kotlin sources contain CJK inside Text(\"...\") (same-line heuristic; pair with :lint-rules and ./gradlew lint)."
    doLast {
        val srcRoot = layout.projectDirectory.dir("src/main/java").asFile
        if (!srcRoot.isDirectory) return@doLast
        val textCjk = Regex("""Text\s*\(\s*"[^"]*[\u4e00-\u9fff][^"]*"""")
        val lineSkips =
            listOf(
                "stringResource",
                "LocalizedStrings",
            )
        val pathSkips =
            listOf(
                "/test/",
                "/androidTest/",
            )
        val violations = mutableListOf<String>()
        srcRoot.walkTopDown().maxDepth(30).filter { it.isFile && it.extension == "kt" }.forEach { file ->
            val rel = file.relativeTo(layout.projectDirectory.asFile).invariantSeparatorsPath
            if (pathSkips.any { rel.contains(it, ignoreCase = true) }) return@forEach
            file.readLines().forEachIndexed { idx, rawLine ->
                val line = rawLine.trimStart()
                if (line.startsWith("//") || line.startsWith("*")) return@forEachIndexed
                if (lineSkips.any { rawLine.contains(it) }) return@forEachIndexed
                if (textCjk.containsMatchIn(rawLine)) {
                    violations.add("$rel:${idx + 1}: Text(...) may contain hardcoded CJK")
                }
            }
        }
        if (violations.isNotEmpty()) {
            throw GradleException(
                "scanHardcodedText failed (${violations.size} finding(s)):\n" +
                    violations.joinToString("\n") +
                    "\n\nUse stringResource / resources or narrow suppressions.",
            )
        }
    }
}

dependencies {
    lintChecks(project(":lint-rules"))
    implementation(project(":task-model"))
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation("androidx.navigation:navigation-compose:2.8.4")
    implementation(libs.androidx.datastore.preferences)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation("androidx.compose.material:material-icons-core:1.6.8")
    implementation("androidx.compose.material:material-icons-extended:1.6.8")
    implementation("io.coil-kt:coil-compose:2.6.0")
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}