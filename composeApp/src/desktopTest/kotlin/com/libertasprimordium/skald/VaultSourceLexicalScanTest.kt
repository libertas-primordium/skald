package com.libertasprimordium.skald

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultSourceLexicalScanTest {
    @Test
    fun completeFlatTemplatesAndQuotedFallbackAreAdmittedExactly() {
        val expression = "value?.name ?: \"none\""
        val source = "val label = \"${'$'}{$expression}\""
        val scanned = accepted(source)
        assertEquals(listOf(expression), scanned.templates)
        assertTrue(scanned.code.contains("value?.name ?: "))
        assertFalse(scanned.code.contains("none"))
        assertTrue(VaultImportConfinement.accepts(source, emptySet(), allowedTemplates = setOf(expression)))
        assertFalse(VaultImportConfinement.accepts(source, emptySet(), allowedTemplates = setOf("value?.name")))
        assertFalse(VaultImportConfinement.accepts("val label = \"${'$'}{value.name.size}\"", emptySet(),
            allowedQualifiedReferences = setOf("value.name.size"), allowedTemplates = setOf("value.name")))
    }

    @Test
    fun escapedDollarAndQuoteParityPreserveRegularStringStructure() {
        assertEquals(emptyList(), accepted("val label = \"\\${'$'}{ignored}\"").templates)
        val doubleBackslash = "val label = \"\\\\${'$'}{value.name}\""
        assertEquals(listOf("value.name"), accepted(doubleBackslash).templates)
        val escapedQuotes = "val label = \"escaped \\\"quote\\\" ${'$'}{value.name}\""
        assertEquals(listOf("value.name"), accepted(escapedQuotes).templates)
        val escapedFallback = "val label = \"${'$'}{value ?: \"escaped \\\"none\\\"\"}\""
        assertEquals(listOf("value ?: \"escaped \\\"none\\\"\""), accepted(escapedFallback).templates)
    }

    @Test
    fun rawStringsUseRawDollarAndBackslashRules() {
        val quotes = "\"\"\""
        val source = "val label = $quotes\\${'$'}{value.name}\n{ prose }$quotes"
        val scanned = accepted(source)
        assertEquals(listOf("value.name"), scanned.templates)
        assertTrue(scanned.code.contains("value.name"))
        assertEquals(source.count { it == '\n' }, scanned.code.count { it == '\n' })
        assertTrue(VaultImportConfinement.accepts(source, emptySet(),
            allowedQualifiedReferences = setOf("value.name"), allowedTemplates = setOf("value.name")))
        rejected("val label = $quotes${'$'}{value.also { it }}$quotes", VaultSourceLexicalScan.Failure.NestedExecutableTemplate)
    }

    @Test
    fun unsupportedRawClosingQuoteRunsCannotChangeFollowingCodeInterpretation() {
        // Four closing quotes are valid Kotlin (one content quote plus the delimiter), but are
        // deliberately outside this restricted corpus rather than partly scanned as regular text.
        val quotes = "\"\"\""
        val source = "val label = ${quotes}quoted\"$quotes; " +
            "val unexpected = println (\"SYNTHETIC_DIAGNOSTIC\")"
        rejected(source, VaultSourceLexicalScan.Failure.UnsupportedRawQuoteRun)
        assertTrue(VaultImportConfinement.containsPrivateCode(source))
        val expression = "value ?: ${quotes}quoted\"$quotes"
        rejected("val label = \"${'$'}{$expression}\"", VaultSourceLexicalScan.Failure.UnsupportedRawQuoteRun)
        (5..8).forEach { count ->
            rejected("val label = ${quotes}quoted" + "\"".repeat(count), VaultSourceLexicalScan.Failure.UnsupportedRawQuoteRun)
        }
    }

    @Test
    fun simpleDollarIdentifiersStayVisibleWithoutTemplateWhitelistEntries() {
        val scanned = accepted("val label = \"first=${'$'}first second=${'$'}second ${'$'}9\"")
        assertEquals(emptyList(), scanned.templates)
        assertTrue(Regex("\\bfirst\\b").containsMatchIn(scanned.code))
        assertTrue(Regex("\\bsecond\\b").containsMatchIn(scanned.code))
        assertFalse(scanned.code.contains("firstsecond"))
        assertTrue(VaultImportConfinement.accepts("val label = \"${'$'}value\"", emptySet()))
    }

    @Test
    fun unsupportedSimpleIdentifierStartsAndContinuationsCannotBecomeProse() {
        listOf("\"", "\"\"\"").forEach { quotes ->
            listOf("${'$'}π", "${'$'}valueπ", "${'$'}`value`", "${'$'}value`tail`").forEach { token ->
                val source = "val label = $quotes$token$quotes"
                rejected(source, VaultSourceLexicalScan.Failure.UnsupportedExpression)
                assertTrue(VaultImportConfinement.containsPrivateCode(source))
            }
        }
        assertEquals(emptyList(), accepted("val label = \"\\${'$'}π\"").templates)
    }

    @Test
    fun bracesAndDollarTextInCommentsOrPlainLiteralsAreNotExecutable() {
        val source = "// ${'$'}{notParsed { }}\n/* outer ${'$'}{ignored} /* nested } */ } */\n" +
            "val plain = \"braces { } and a dollar ${'$'} !\"\n" +
            "val chars = '}'\nval escaped = '\\''\nval label = \"${'$'}{value /* } ${'$'}{comment} */ .name}\""
        val scanned = accepted(source)
        assertEquals(listOf("value /* } ${'$'}{comment} */ .name"), scanned.templates)
        assertTrue(scanned.code.contains("value"))
        assertTrue(scanned.code.contains(".name"))
        assertFalse(scanned.code.contains("notParsed"))
        assertEquals(source.count { it == '\n' }, scanned.code.count { it == '\n' })
    }

    @Test
    fun commentsPreserveTokenSeparationAndCannotNormalizeTemplateIdentity() {
        val scanned = accepted("val joined = first/*comment*/second\nprivate/*comment*/fun item() = Unit")
        assertTrue(Regex("first\\s+second").containsMatchIn(scanned.code))
        assertFalse(scanned.code.contains("firstsecond"))
        assertTrue(VaultImportConfinement.containsPrivateCode("private/*comment*/fun item() = Unit"))
        val expression = "value/*comment*/.name"
        assertFalse(VaultImportConfinement.accepts("val label = \"${'$'}{$expression}\"", emptySet(),
            allowedQualifiedReferences = setOf("value.name"), allowedTemplates = setOf("value.name")))
        assertFalse(VaultImportConfinement.accepts("val label = \"${'$'}{ value.name }\"", emptySet(),
            allowedQualifiedReferences = setOf("value.name"), allowedTemplates = setOf("value.name")))
    }

    @Test
    fun everyKotlinLineTerminatorEndsCommentsAndPreservesExecutableSuffixes() {
        listOf("\n", "\r\n", "\r").forEach { newline ->
            val source = "// comment$newline" + "println (\"SYNTHETIC_DIAGNOSTIC\")"
            val scanned = accepted(source)
            assertTrue(scanned.code.contains("println ("))
            assertTrue(scanned.code.contains(newline))
            assertFalse(VaultImportConfinement.accepts(source, emptySet()))
            assertTrue(VaultImportConfinement.containsPrivateCode("// comment${newline}private val value = Unit"))
            assertTrue(accepted("/* comment${newline}continued */ val value = Unit").code.contains(newline))
        }
    }

    @Test
    fun nestedExecutableAndRecursiveInterpolationRejectTheWholeSource() {
        rejected("val label = \"${'$'}{value.also { it }}\"", VaultSourceLexicalScan.Failure.NestedExecutableTemplate)
        rejected("val label = \"${'$'}{value ?: \"${'$'}{other}\"}\"", VaultSourceLexicalScan.Failure.RecursiveInterpolation)
        rejected("val label = \"${'$'}{value ?: \"${'$'}other\"}\"", VaultSourceLexicalScan.Failure.RecursiveInterpolation)
        val quotes = "\"\"\""
        rejected("val label = \"${'$'}{value ?: $quotes${'$'}{other}$quotes}\"", VaultSourceLexicalScan.Failure.RecursiveInterpolation)
        assertTrue(VaultImportConfinement.containsPrivateCode("val label = \"${'$'}{object { private val value = Unit }}\""))
    }

    @Test
    fun malformedInputRejectsWithoutPartialSuccessOrFalsePrivateAbsence() {
        val malformed = listOf(
            "val label = \"unclosed",
            "val label = \"\"\"unclosed",
            "val value = 'x",
            "val value = '\\u00",
            "/* unclosed",
            "/* outer /* nested */",
            "val label = \"${'$'}{value",
            "val label = \"${'$'}{value ?: \"none}\"",
            "val label = \"${'$'}{}\"",
            "val label = \"${'$'}{value; other}\"",
            "val label = \"${'$'}{(value}\"",
            "val label = \"${'$'}{value)}\"",
            "val label = \"valid ${'$'}{value.name}\"\n/* unclosed remainder",
        )
        malformed.forEachIndexed { index, source ->
            assertIs<VaultSourceLexicalScan.Rejected>(VaultSourceLexicalScan.scan(source), "Malformed case $index admitted")
            assertFalse(VaultImportConfinement.accepts(source, emptySet(), allowedTemplates = setOf("value.name")))
            assertTrue(VaultImportConfinement.containsPrivateCode(source), "Malformed case $index concealed code uncertainty")
        }
        assertFalse(VaultImportConfinement.containsPrivateCode("val label = \"Monero private key material\""))
    }

    @Test
    fun explicitScanLimitsRejectOversizedAndExcessivelyNestedInput() {
        accepted(" ".repeat(VaultSourceLexicalScan.MAX_SOURCE_CHARS))
        rejected(" ".repeat(VaultSourceLexicalScan.MAX_SOURCE_CHARS + 1), VaultSourceLexicalScan.Failure.SourceLimit)
        rejected("val label = \"${'$'}{" + "x".repeat(VaultSourceLexicalScan.MAX_EXPRESSION_CHARS + 1) + "}\"",
            VaultSourceLexicalScan.Failure.ExpressionLimit)
        rejected("/*".repeat(VaultSourceLexicalScan.MAX_NESTING + 1) + "*/".repeat(VaultSourceLexicalScan.MAX_NESTING + 1),
            VaultSourceLexicalScan.Failure.NestingLimit)
        rejected("val label = \"${'$'}{" + "(".repeat(VaultSourceLexicalScan.MAX_NESTING + 1) + "value" +
            ")".repeat(VaultSourceLexicalScan.MAX_NESTING + 1) + "}\"", VaultSourceLexicalScan.Failure.NestingLimit)
        rejected("val label = \"" + "${'$'}{value}".repeat(VaultSourceLexicalScan.MAX_TEMPLATES + 1) + "\"",
            VaultSourceLexicalScan.Failure.TemplateLimit)
    }

    private fun accepted(source: String): VaultSourceLexicalScan.Accepted =
        assertIs<VaultSourceLexicalScan.Accepted>(VaultSourceLexicalScan.scan(source))

    private fun rejected(source: String, reason: VaultSourceLexicalScan.Failure) {
        assertEquals(reason, assertIs<VaultSourceLexicalScan.Rejected>(VaultSourceLexicalScan.scan(source)).reason)
        assertFalse(VaultImportConfinement.accepts(source, emptySet()))
    }
}
