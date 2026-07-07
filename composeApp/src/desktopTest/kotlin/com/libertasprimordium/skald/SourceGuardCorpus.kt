package com.libertasprimordium.skald

import java.io.File

object SourceGuardTiming {
    private val diagnosticsEnabled: Boolean =
        System.getProperty("skald.test.diagnostics.enabled").equals("true", ignoreCase = true)
    private val timingFile: File? =
        System.getProperty("skald.test.timingFile")
            ?.takeIf { it.isNotBlank() }
            ?.let(::File)

    fun <T> measure(
        label: String,
        countName: String,
        count: (T) -> Int,
        block: () -> T,
    ): T {
        val started = System.nanoTime()
        val result = block()
        record(label, elapsedMs = (System.nanoTime() - started) / 1_000_000, countName = countName, count = count(result))
        return result
    }

    @Synchronized
    fun record(label: String, elapsedMs: Long, countName: String, count: Int) {
        if (!diagnosticsEnabled) return
        val target = timingFile ?: return
        target.parentFile?.mkdirs()
        target.appendText("SKALD_TEST_TIMING $label ${elapsedMs}ms $countName=$count\n")
    }
}

object SourceGuardCorpus {
    val repositoryRoot: File by lazy {
        SourceGuardTiming.measure("repository_root", "count", { 1 }) {
            generateSequence(File(".").absoluteFile) { file -> file.parentFile }
                .first { candidate -> File(candidate, "settings.gradle.kts").exists() }
                .canonicalFile
        }
    }

    val commonMainFiles: List<File> by lazy {
        kotlinTextFilesUnder("common_main_files", File(repositoryRoot, "composeApp/src/commonMain"))
    }

    val androidMainFiles: List<File> by lazy {
        kotlinTextFilesUnder("android_main_files", File(repositoryRoot, "composeApp/src/androidMain"))
    }

    val desktopMainFiles: List<File> by lazy {
        kotlinTextFilesUnder("desktop_main_files", File(repositoryRoot, "composeApp/src/desktopMain"))
    }

    val productionRuntimeSourceFiles: List<File> by lazy {
        SourceGuardTiming.measure("production_runtime_source_files", "files", List<File>::size) {
            commonMainFiles + androidMainFiles + desktopMainFiles
        }
    }

    val testSourceFiles: List<File> by lazy {
        SourceGuardTiming.measure("test_source_files", "files", List<File>::size) {
            listOf(
                File(repositoryRoot, "composeApp/src/commonTest"),
                File(repositoryRoot, "composeApp/src/desktopTest"),
                File(repositoryRoot, "composeApp/src/androidInstrumentedTest"),
            ).flatMap { root -> kotlinTextFilesUnder("test_source_root_${root.name}", root) }
        }
    }

    val buildConfigFiles: List<File> by lazy {
        SourceGuardTiming.measure("build_config_files", "files", List<File>::size) {
            listOf(
                File(repositoryRoot, "settings.gradle.kts"),
                File(repositoryRoot, "build.gradle.kts"),
                File(repositoryRoot, "gradle/libs.versions.toml"),
                File(repositoryRoot, "composeApp/build.gradle.kts"),
                File(repositoryRoot, "gradle.properties"),
            ).filter { it.isFile }
        }
    }

    val docsFiles: List<File> by lazy {
        SourceGuardTiming.measure("docs_files", "files", List<File>::size) {
            listOf(File(repositoryRoot, "README.md")) +
                File(repositoryRoot, "docs")
                    .walkTopDown()
                    .filter { it.isFile && it.extension == "md" }
                    .sortedBy { relativePath(it) }
                    .toList()
        }
    }

    private val textCache = linkedMapOf<String, String>()

    @Synchronized
    fun text(file: File): String {
        val key = file.canonicalFile.path
        return textCache.getOrPut(key) {
            file.readText()
        }
    }

    fun relativePath(file: File): String =
        file.canonicalFile.relativeTo(repositoryRoot).invariantSeparatorsPath

    private fun kotlinTextFilesUnder(label: String, root: File): List<File> =
        SourceGuardTiming.measure(label, "files", List<File>::size) {
            if (!root.exists()) {
                emptyList()
            } else {
                root.walkTopDown()
                    .filter { it.isFile && it.extension in setOf("kt", "kts") }
                    .filterNot { file -> excludedPath(relativePath(file)) }
                    .sortedBy { relativePath(it) }
                    .toList()
            }
        }

    private fun excludedPath(relativePath: String): Boolean {
        val blockedSegments = listOf(
            "/.git/",
            "/.gradle/",
            "/.idea/",
            "/build/",
            "/.kotlin/",
            "/out/",
            "/reports/",
            "/tmp/",
        )
        return blockedSegments.any { segment -> segment in "/$relativePath/" } ||
            relativePath.endsWith(".apk") ||
            relativePath.endsWith(".deb") ||
            relativePath.endsWith(".class") ||
            relativePath.endsWith(".jar") ||
            relativePath.endsWith(".bin")
    }
}
