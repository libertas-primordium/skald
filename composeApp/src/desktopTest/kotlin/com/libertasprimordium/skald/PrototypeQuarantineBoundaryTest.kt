package com.libertasprimordium.skald

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PrototypeQuarantineBoundaryTest {
    @Test
    fun everyQuarantinedFileHasOneExplicitTestOwnerAndNoProductionOwner() {
        val root = SourceGuardCorpus.repositoryRoot
        val productionPaths = SourceGuardCorpus.productionRuntimeSourceFiles.map(SourceGuardCorpus::relativePath).toSet()
        val testPaths = SourceGuardCorpus.testSourceFiles.map(SourceGuardCorpus::relativePath)
        PrototypeQuarantineBoundary.movedPaths.forEach { (oldPath, supportPath) ->
            assertFalse(File(root, oldPath).exists(), "Prototype remained at $oldPath")
            assertFalse(supportPath in productionPaths, "Test helper entered production: $supportPath")
            assertEquals(1, testPaths.count { it == supportPath }, "Missing/duplicate test owner: $supportPath")
            val source = SourceGuardCorpus.text(File(root, supportPath))
            assertFalse(Regex("""@(?:Test|org\.junit\.Test|kotlin\.test\.Test)\b""").containsMatchIn(source), "Support root contains a test suite: $supportPath")
        }
        assertEquals(8, PrototypeQuarantineBoundary.movedPaths.values.count { "/prototypeTestSupport/" in it })
        assertEquals(4, PrototypeQuarantineBoundary.movedPaths.values.count { "/androidPrototypeTestSupport/" in it })
        assertEquals(4, PrototypeQuarantineBoundary.movedPaths.values.count { "/desktopTest/" in it })
    }

    @Test
    fun productionCorpusContainsNoExecutablePrototypeReferenceOrMaterialBearingCompatibility() {
        SourceGuardCorpus.productionRuntimeSourceFiles.forEach { file ->
            val path = SourceGuardCorpus.relativePath(file)
            assertTrue(PrototypeQuarantineBoundary.acceptsProductionSource(path, SourceGuardCorpus.text(file)), "Prototype reference or unsupported compatibility code: $path")
        }
        val compatibility = File(SourceGuardCorpus.repositoryRoot, PrototypeQuarantineBoundary.COMPATIBILITY_PATH)
        assertTrue(compatibility.isFile)
        assertTrue(VaultImportConfinementTest().acceptsReviewedProductionSource(PrototypeQuarantineBoundary.COMPATIBILITY_PATH, SourceGuardCorpus.text(compatibility)))
    }

    @Test
    fun actualAppSourceRejectsPrototypeImportsCallsReferencesWrappersAndInitializers() {
        val path = "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/App.kt"
        val source = SourceGuardCorpus.text(File(SourceGuardCorpus.repositoryRoot, path))
        val entry = VaultImportConfinementTest()
        assertTrue(entry.acceptsReviewedProductionSource(path, source))
        val packageMarker = "package com.libertasprimordium.skald\n"
        assertEquals(1, source.split(packageMarker).size - 1)
        val mutations = linkedMapOf(
            "direct helper import" to source.replace(packageMarker, packageMarker + "\nimport com.libertasprimordium.skald.security.SkaldVaultV1RecordAead\n"),
            "unqualified call" to source.replace(packageMarker, packageMarker + "\nimport com.libertasprimordium.skald.security.SkaldVaultV1HeaderCommitment\n") + "\nfun syntheticLeak() = SkaldVaultV1HeaderCommitment.vectorFixtureHeader()\n",
            "qualified fixture initializer" to source + "\nval syntheticLeak = com.libertasprimordium.skald.security.SkaldVaultV1ContainerFormat.vectorFixtureContainer()\n",
            "callable reference" to source + "\nval syntheticLeak = com.libertasprimordium.skald.security.SkaldVaultV1ManifestFormat::parse\n",
            "synthetic parser wrapper" to source + "\nfun syntheticLeak(request: com.libertasprimordium.skald.security.EncryptedVaultWorkingParserRequest) = com.libertasprimordium.skald.security.EncryptedVaultWorkingParser.parse(request)\n",
            "synthetic parser value-class reference" to source + "\nfun syntheticLeak(value: com.libertasprimordium.skald.security.EncryptedVaultWorkingParserSafeLabel) = value\n",
            "primitive reference" to source.replace(packageMarker, packageMarker + "\nimport com.libertasprimordium.skald.security.skaldVaultV1NormalizeNfc\n") + "\nval syntheticLeak = ::skaldVaultV1NormalizeNfc\n",
        )
        mutations.forEach { (name, mutated) ->
            assertFalse(mutated == source, "$name mutation did not change its real source")
            assertFalse(PrototypeQuarantineBoundary.acceptsProductionSource(path, mutated), "$name must fail the quarantine boundary itself")
            assertFalse(entry.acceptsReviewedProductionSource(path, mutated), "$name passed the real production admission entry point")
        }
    }

    @Test
    fun actualCompatibilitySourceRejectsOperationsMaterialAndUnknownMembers() {
        val path = PrototypeQuarantineBoundary.COMPATIBILITY_PATH
        val source = SourceGuardCorpus.text(File(SourceGuardCorpus.repositoryRoot, path))
        val marker = "object SkaldVaultV1PrototypeKdfCompatibility {"
        assertEquals(1, source.split(marker).size - 1)
        assertTrue(PrototypeQuarantineBoundary.acceptsProductionSource(path, source))
        listOf(
            "fun derive() = Unit",
            "val material: ByteArray = ByteArray(1)",
            "val mutableEvidence = mutableListOf<Int>()",
            "const val NEW_APPROVAL = true",
        ).forEachIndexed { index, declaration ->
            val mutated = source.replace(marker, "$marker\n    $declaration")
            assertFalse(mutated == source, "Compatibility mutation $index did not change its target")
            assertFalse(PrototypeQuarantineBoundary.acceptsProductionSource(path, mutated), "Compatibility mutation $index expanded the inert declaration contract")
            assertFalse(VaultImportConfinementTest().acceptsReviewedProductionSource(path, mutated))
        }
    }

    @Test
    fun exactReviewedBuildGraphAdmitsOnlyTestSupportOwnership() {
        val source = SourceGuardCorpus.text(File(SourceGuardCorpus.repositoryRoot, "composeApp/build.gradle.kts"))
        assertTrue(PrototypeQuarantineBoundary.acceptsBuildConfiguration(source))
    }

    @Test
    fun actualBuildRejectsSupportDirectoriesAndDependenciesAttachedToProduction() {
        val source = SourceGuardCorpus.text(File(SourceGuardCorpus.repositoryRoot, "composeApp/build.gradle.kts"))
        val marker = "val commonMain by getting {"
        assertEquals(1, source.split(marker).size - 1)
        val mutations = listOf(
            source + "\nkotlin.sourceSets.getByName(\"commonMain\").dependsOn(kotlin.sourceSets.getByName(\"commonTest\"))\n",
            source.replace(marker, "$marker\n            kotlin.srcDir(\"src/prototypeTestSupport/kotlin\")"),
            source + "\nkotlin.sourceSets.getByName(\"androidMain\").kotlin.srcDir(\"src/androidPrototypeTestSupport/kotlin\")\n",
            source + "\nkotlin.targets.getByName(\"desktop\").compilations.getByName(\"main\").defaultSourceSet.dependsOn(kotlin.sourceSets.getByName(\"prototypeTestSupport\"))\n",
            source + "\ntasks.named(\"compileKotlinDesktop\") { source(\"src/prototypeTestSupport/kotlin\") }\n",
            source.replace("sourceSets[\"main\"].res.srcDirs(\"src/androidMain/res\")", "sourceSets[\"main\"].res.srcDirs(\"src/prototypeTestSupport/kotlin\")"),
        )
        mutations.forEachIndexed { index, mutated ->
            assertFalse(mutated == source, "Build mutation $index missed its real input")
            assertFalse(PrototypeQuarantineBoundary.acceptsBuildConfiguration(mutated), "Build mutation $index admitted runtime/test linkage")
        }
    }
}
