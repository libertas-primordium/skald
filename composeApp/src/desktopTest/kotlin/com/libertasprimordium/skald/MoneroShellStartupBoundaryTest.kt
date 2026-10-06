package com.libertasprimordium.skald

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MoneroShellStartupBoundaryTest {
    @Test
    fun compositionAndPlatformInitializersStayInTheApprovedInertCallGraph() {
        contracts.forEach { (path, contract) ->
            val file = SourceGuardCorpus.productionRuntimeSourceFiles.single {
                SourceGuardCorpus.relativePath(it) == path
            }
            assertTrue(contract.accepts(SourceGuardCorpus.text(file)), "Unexpected startup surface in $path")
        }
    }

    @Test
    fun startupGuardRejectsAddedImportsAliasesQualifiedCallsReflectionAndIndirectLoading() {
        val path = "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/App.kt"
        val contract = contracts.getValue(path)
        val original = SourceGuardCorpus.productionRuntimeSourceFiles.single {
            SourceGuardCorpus.relativePath(it) == path
        }.let(SourceGuardCorpus::text)
        assertTrue(contract.accepts(original))
        val mutations = listOf(
            original + "\nimport example.unapproved.Service\n",
            replaceExactlyOnce(original, "import androidx.compose.runtime.remember", "import androidx.compose.runtime.remember as launch"),
            original + "\nval inserted = example.unapproved.Service()\n",
            original + "\nval inserted = Class.forName(\"example.unapproved.Service\")\n",
            original + "\nval inserted = Runtime.getRuntime()\n",
            original + "\nval inserted = InjectedInitializer\n",
            original + "\nval inserted = ::unapprovedOperation\n",
            replaceExactlyOnce(original, "state.navigate(it)", "state.getSecret(it)"),
            original + "\nval page = '\"'; val state = java.lang.System.gc(); val screen = '\"'\n",
            replaceExactlyOnce(original, "package com.libertasprimordium.skald", "package com.libertasprimordium.skald; val inserted = example.unapproved.Service()"),
            original + "\nval inserted = \"\${example.unapproved.Service()}\"\n",
        )
        mutations.forEachIndexed { index, source ->
            assertFalse(source == original, "Startup mutation $index did not change the source")
            assertFalse(contract.accepts(source), "Startup mutation $index was admitted")
        }
    }

    @Test
    fun actualShellRejectsStorageOperationsAndOperationCapableHandles() {
        val path = "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/SkaldShellState.kt"
        val original = source("/ui/SkaldShellState.kt")
        val contract = contracts.getValue(path)
        assertTrue(contract.accepts(original))
        val mutations = listOf(
            replaceExactlyOnce(
                original,
                "secureStorage.capability.toUiStatus()",
                "secureStorage.capability.toUiStatus().also { secureStorage.listMetadata() }",
            ),
            replaceExactlyOnce(
                original,
                "val secureStorageStatus: SecureStorageUiStatus,",
                "val secureStorageStatus: SecureStorageUiStatus, val operationStorage: SecureSecretStorage? = null,",
            ),
        )
        mutations.forEachIndexed { index, mutation ->
            assertFalse(contract.accepts(mutation), "Shell mutation $index was admitted")
        }
    }

    @Test
    fun everyDestinationIsWiredThroughTheStateAndReadOnlyPagesHaveNoCallbacks() {
        val app = source("/App.kt")
        assertTrue(app.contains("remember(secureStorage)"))
        assertEquals(1, app.split("SkaldShellState.start(secureStorage)").size - 1)
        assertTrue(app.contains("state = state.navigate(it)"))
        assertTrue(app.contains("val page = state.page"))
        listOf("Wallet", "Settings").forEach { screen ->
            assertTrue(app.contains("AppScreen.$screen -> ${screen}Screen(page"))
            val page = source("/ui/screens/${screen}Screen.kt")
            assertTrue(page.contains("page.sections"))
            assertFalse(Regex("onClick|clickable|onValueChange").containsMatchIn(page))
        }
        val cards = source("/ui/components/SkaldCards.kt")
        assertFalse(Regex("onClick|clickable|onValueChange").containsMatchIn(cards))
        val scaffold = source("/ui/components/SkaldAppScaffold.kt")
        val notice = "Development build. Wallet and vault features are unavailable. Do not use real funds."
        assertEquals(1, scaffold.split(notice).size - 1)
        assertTrue(scaffold.contains("remember(selectedScreen) { ScrollState(0) }"))
        assertFalse(scaffold.contains("rememberScrollState"))
        assertFalse(scaffold.contains("rememberSaveable"))
        listOf("Wallet", "Settings").forEach { screen ->
            assertFalse(source("/ui/screens/${screen}Screen.kt").contains(notice))
        }
    }

    @Test
    fun retainedStateAndPresentationModelsContainOnlyImmutableSnapshotsAndText() {
        fun fields(type: Class<*>): Map<String, String> = type.declaredFields
            .filterNot { java.lang.reflect.Modifier.isStatic(it.modifiers) }
            .onEach { assertTrue(java.lang.reflect.Modifier.isFinal(it.modifiers), "Presentation fields must be final") }
            .associate { it.name to it.genericType.typeName }
        assertEquals(
            mapOf(
                "selectedScreen" to "com.libertasprimordium.skald.ui.navigation.AppScreen",
                "secureStorageStatus" to "com.libertasprimordium.skald.security.SecureStorageUiStatus",
            ),
            fields(com.libertasprimordium.skald.ui.SkaldShellState::class.java),
        )
        assertEquals(
            mapOf("title" to "java.lang.String", "subtitle" to "java.lang.String", "sections" to "java.util.List<com.libertasprimordium.skald.ui.ShellSection>"),
            fields(com.libertasprimordium.skald.ui.ShellPage::class.java),
        )
        assertEquals(
            mapOf("title" to "java.lang.String", "status" to "java.lang.String", "paragraphs" to "java.util.List<java.lang.String>"),
            fields(com.libertasprimordium.skald.ui.ShellSection::class.java),
        )
    }

    private fun source(suffix: String): String = SourceGuardCorpus.productionRuntimeSourceFiles.single {
        SourceGuardCorpus.relativePath(it).endsWith(suffix)
    }.let(SourceGuardCorpus::text)

    private fun replaceExactlyOnce(source: String, target: String, replacement: String): String {
        assertEquals(1, source.split(target).size - 1, "Mutation target must identify one current source location")
        return source.replace(target, replacement).also { assertFalse(it == source, "Mutation must change the source") }
    }

    private data class StartupContract(val packageName: String, val imports: Set<String>, val identifiers: Set<String>) {
        fun accepts(source: String): Boolean {
            val packages = source.lineSequence().filter { it.trimStart().startsWith("package ") }.toList()
            if (packages != listOf("package $packageName")) return false
            val importLines = source.lineSequence().filter { it.trimStart().startsWith("import ") }
                .map { it.trim().removePrefix("import ") }.toList()
            if (importLines.size != imports.size || importLines.toSet() != imports) return false
            val body = source.lineSequence().filterNot {
                it.startsWith("package ") || it.startsWith("import ")
            }.joinToString("\n")
            val code = withoutCommentsAndStrings(body) ?: return false
            if (code.any { it == '`' || it.code > 127 }) return false
            return Regex("[A-Za-z_][A-Za-z0-9_]*").findAll(code).all { it.value in identifiers }
        }
    }

    private companion object {
        // Fixed positive identifier/import vocabulary: only presentation, navigation and disabled capability metadata.
        // This confines all startup declarations, initializer expressions and calls, not only import names.
        val contracts = mapOf(
        "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/App.kt" to StartupContract(
            packageName = "com.libertasprimordium.skald",
            imports = setOf(
                "androidx.compose.runtime.Composable",
                "androidx.compose.runtime.getValue",
                "androidx.compose.runtime.mutableStateOf",
                "androidx.compose.runtime.remember",
                "androidx.compose.runtime.setValue",
                "com.libertasprimordium.skald.security.DisabledSecureSecretStorage",
                "com.libertasprimordium.skald.security.SecureSecretStorage",
                "com.libertasprimordium.skald.ui.SkaldShellState",
                "com.libertasprimordium.skald.ui.components.SkaldAppScaffold",
                "com.libertasprimordium.skald.ui.navigation.AppScreen",
                "com.libertasprimordium.skald.ui.screens.SettingsScreen",
                "com.libertasprimordium.skald.ui.screens.WalletScreen",
            ),
            identifiers = setOf(
                "AppScreen",
                "Composable",
                "DisabledSecureSecretStorage",
                "SecureSecretStorage",
                "Settings",
                "SettingsScreen",
                "SkaldApp",
                "SkaldAppScaffold",
                "SkaldShellState",
                "Wallet",
                "WalletScreen",
                "by",
                "fun",
                "it",
                "mutableStateOf",
                "navigate",
                "onSelectedScreen",
                "page",
                "remember",
                "screen",
                "secureStorage",
                "selectedScreen",
                "start",
                "state",
                "val",
                "var",
                "when",
            ),
        ),
        "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/SkaldShellState.kt" to StartupContract(
            packageName = "com.libertasprimordium.skald.ui",
            imports = setOf(
                "com.libertasprimordium.skald.security.SecureSecretStorage",
                "com.libertasprimordium.skald.security.SecureStorageUiStatus",
                "com.libertasprimordium.skald.security.toUiStatus",
                "com.libertasprimordium.skald.ui.navigation.AppScreen",
            ),
            identifiers = setOf(
                "AppScreen",
                "List",
                "SecureSecretStorage",
                "SecureStorageUiStatus",
                "Settings",
                "ShellPage",
                "ShellSection",
                "SkaldShellState",
                "String",
                "Wallet",
                "capability",
                "class",
                "companion",
                "constructor",
                "data",
                "fun",
                "get",
                "listOf",
                "navigate",
                "object",
                "page",
                "paragraphs",
                "sections",
                "null",
                "private",
                "screen",
                "secureStorage",
                "secureStorageStatus",
                "selectedScreen",
                "start",
                "status",
                "subtitle",
                "title",
                "toUiStatus",
                "val",
                "when",
            ),
        ),
        "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/MainActivity.kt" to StartupContract(
            packageName = "com.libertasprimordium.skald",
            imports = setOf(
                "android.os.Bundle",
                "androidx.activity.ComponentActivity",
                "androidx.activity.compose.setContent",
                "com.libertasprimordium.skald.security.AndroidSecureStorage",
            ),
            identifiers = setOf(
                "AndroidSecureStorage",
                "Bundle",
                "ComponentActivity",
                "MainActivity",
                "SkaldApp",
                "class",
                "fun",
                "onCreate",
                "override",
                "savedInstanceState",
                "secureStorage",
                "setContent",
                "super",
            ),
        ),
        "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/Main.kt" to StartupContract(
            packageName = "com.libertasprimordium.skald",
            imports = setOf(
                "androidx.compose.ui.window.Window",
                "androidx.compose.ui.window.application",
                "com.libertasprimordium.skald.security.DesktopSecureStorage",
            ),
            identifiers = setOf(
                "DesktopSecureStorage",
                "SkaldApp",
                "Window",
                "application",
                "exitApplication",
                "fun",
                "main",
                "onCloseRequest",
                "secureStorage",
                "title",
            ),
        ),
        )

        fun withoutCommentsAndStrings(source: String): String? {
            val result = StringBuilder()
            var index = 0
            while (index < source.length) {
                when {
                    source.startsWith("//", index) -> {
                        index = source.indexOf('\n', index).let { if (it < 0) source.length else it }
                        result.append(' ')
                    }
                    source.startsWith("/*", index) -> {
                        var depth = 1
                        index += 2
                        while (index < source.length && depth > 0) {
                            when {
                                source.startsWith("/*", index) -> { depth += 1; index += 2 }
                                source.startsWith("*/", index) -> { depth -= 1; index += 2 }
                                else -> index += 1
                            }
                        }
                        if (depth != 0) return null
                        result.append(' ')
                    }
                    source[index] == '\'' -> return null
                    source[index] == '"' -> {
                        if (source.startsWith("\"\"\"", index)) return null
                        index += 1
                        var closed = false
                        while (index < source.length) {
                            val character = source[index++]
                            if (character == '$' || character == '\n') return null
                            if (character == '\\') {
                                if (index >= source.length) return null
                                index += 1
                            } else if (character == '"') {
                                closed = true
                                break
                            }
                        }
                        if (!closed) return null
                        result.append(' ')
                    }
                    else -> result.append(source[index++])
                }
            }
            return result.toString()
        }
    }
}
