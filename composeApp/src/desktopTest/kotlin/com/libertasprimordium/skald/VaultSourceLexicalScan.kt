package com.libertasprimordium.skald

/**
 * Test-side lexer for the reviewed source corpus, not a Kotlin parser or a security sandbox.
 * One forward walk supplies both complete, unnormalized template expressions and executable code.
 * Unsupported or incomplete lexical structure rejects the entire source; no partial result escapes.
 * Longer raw-string closing quote runs are outside this corpus and reject instead of leaving a
 * quote to be misinterpreted as the start of a regular string.
 *
 * The closeout corpus has 118 production files, at most 223,638 UTF-16 characters per file.
 * The 1 Mi-character bound gives over 4x headroom. Flat expressions are bounded to 4,096
 * characters, 8,192 per source, and 32 comment/parenthesis levels (far above current inputs).
 * The cursor advances at most MAX_SOURCE_CHARS times; each advance consumes one work unit.
 * These are test-source scanning limits, never vault data or format limits.
 */
internal object VaultSourceLexicalScan {
    const val MAX_SOURCE_CHARS = 1_048_576
    const val MAX_EXPRESSION_CHARS = 4_096
    const val MAX_TEMPLATES = 8_192
    const val MAX_NESTING = 32

    sealed interface Result
    data class Accepted(val code: String, val templates: List<String>) : Result
    data class Rejected(val reason: Failure) : Result
    enum class Failure {
        SourceLimit, ExpressionLimit, TemplateLimit, NestingLimit, WorkLimit,
        UnclosedComment, UnclosedString, UnclosedCharacter, UnclosedInterpolation,
        InvalidEscape, UnsupportedCharacter, UnsupportedExpression, NestedExecutableTemplate,
        RecursiveInterpolation, UnsupportedRawQuoteRun,
    }

    fun scan(source: String): Result =
        if (source.length > MAX_SOURCE_CHARS) Rejected(Failure.SourceLimit) else Walker(source).scan()

    private class Walker(private val source: String) {
        private val code = CharArray(source.length) {
            if (source[it] == '\n' || source[it] == '\r') source[it] else ' '
        }
        private val templates = mutableListOf<String>()
        private var cursor = 0
        private var work = 0
        private var failure: Failure? = null
        private var expressionStart: Int? = null

        fun scan(): Result {
            while (cursor < source.length && failure == null) {
                when {
                    starts("//") -> lineComment()
                    starts("/*") -> blockComment()
                    starts("\"\"\"") -> string(raw = true, expressionLiteral = false)
                    source[cursor] == '"' -> string(raw = false, expressionLiteral = false)
                    source[cursor] == '\'' -> character()
                    else -> advance(visible = true)
                }
            }
            return failure?.let(::Rejected) ?: Accepted(code.concatToString(), templates.toList())
        }

        private fun starts(value: String): Boolean = source.startsWith(value, cursor)

        private fun reject(reason: Failure) {
            if (failure == null) failure = reason
        }

        private fun advance(count: Int = 1, visible: Boolean = false) {
            repeat(count) {
                if (failure != null) return
                if (cursor >= source.length || work >= MAX_SOURCE_CHARS) {
                    reject(Failure.WorkLimit)
                    return
                }
                if (visible) code[cursor] = source[cursor]
                cursor++
                work++
                if (expressionStart?.let { cursor - it > MAX_EXPRESSION_CHARS } == true) {
                    reject(Failure.ExpressionLimit)
                }
            }
        }

        private fun lineComment() {
            while (cursor < source.length && source[cursor] != '\n' && source[cursor] != '\r' && failure == null) {
                advance()
            }
        }

        private fun blockComment() {
            advance(2)
            var depth = 1
            while (cursor < source.length && failure == null) {
                when {
                    starts("/*") -> {
                        depth++
                        if (depth > MAX_NESTING) reject(Failure.NestingLimit) else advance(2)
                    }
                    starts("*/") -> {
                        advance(2)
                        depth--
                        if (depth == 0) return
                    }
                    else -> advance()
                }
            }
            reject(Failure.UnclosedComment)
        }

        private fun escapedCharacter() {
            advance() // backslash
            if (cursor >= source.length) {
                reject(Failure.InvalidEscape)
                return
            }
            when (source[cursor]) {
                't', 'b', 'n', 'r', '\'', '"', '\\', '$' -> advance()
                'u' -> {
                    advance()
                    repeat(4) {
                        if (cursor >= source.length || source[cursor] !in "0123456789abcdefABCDEF") {
                            reject(Failure.InvalidEscape)
                            return
                        }
                        advance()
                    }
                }
                else -> reject(Failure.InvalidEscape)
            }
        }

        private fun character() {
            advance() // opening quote
            if (cursor >= source.length || source[cursor] == '\n' || source[cursor] == '\r') {
                reject(Failure.UnclosedCharacter)
                return
            }
            when (source[cursor]) {
                '\\' -> escapedCharacter()
                '\'' -> reject(Failure.UnsupportedCharacter)
                else -> advance()
            }
            if (cursor < source.length && source[cursor] == '\'') advance()
            else reject(Failure.UnclosedCharacter)
        }

        private fun string(raw: Boolean, expressionLiteral: Boolean) {
            advance(if (raw) 3 else 1)
            while (cursor < source.length && failure == null) {
                when {
                    raw && starts("\"\"\"") -> {
                        if (source.getOrNull(cursor + 3) == '"') reject(Failure.UnsupportedRawQuoteRun)
                        else advance(3)
                        return
                    }
                    !raw && source[cursor] == '"' -> { advance(); return }
                    !raw && (source[cursor] == '\n' || source[cursor] == '\r') -> {
                        reject(Failure.UnclosedString)
                    }
                    !raw && source[cursor] == '\\' -> escapedCharacter()
                    source[cursor] == '$' && starts("\${") -> {
                        if (expressionLiteral) reject(Failure.RecursiveInterpolation)
                        else interpolation()
                    }
                    source[cursor] == '$' && cursor + 1 < source.length && identifierStart(source[cursor + 1]) -> {
                        if (expressionLiteral) reject(Failure.RecursiveInterpolation)
                        else {
                            advance() // dollar is masked; retain the whole executable identifier.
                            while (cursor < source.length && identifierPart(source[cursor]) && failure == null) {
                                advance(visible = true)
                            }
                            // Do not silently mask a continuation of an unsupported Kotlin identifier.
                            if (cursor < source.length && (source[cursor].code > 127 || source[cursor] == '`')) {
                                reject(Failure.UnsupportedExpression)
                            }
                        }
                    }
                    source[cursor] == '$' && cursor + 1 < source.length &&
                        (source[cursor + 1].code > 127 || source[cursor + 1] == '`') -> {
                        reject(Failure.UnsupportedExpression)
                    }
                    else -> advance()
                }
            }
            reject(Failure.UnclosedString)
        }

        private fun interpolation() {
            advance(2) // opening dollar/brace
            val start = cursor
            expressionStart = start
            var parentheses = 0
            var hasToken = false
            while (cursor < source.length && failure == null) {
                when {
                    starts("//") -> lineComment()
                    starts("/*") -> blockComment()
                    starts("\"\"\"") -> { hasToken = true; string(raw = true, expressionLiteral = true) }
                    source[cursor] == '"' -> { hasToken = true; string(raw = false, expressionLiteral = true) }
                    source[cursor] == '\'' -> { hasToken = true; character() }
                    source[cursor] == '{' -> reject(Failure.NestedExecutableTemplate)
                    source[cursor] == '}' -> {
                        if (!hasToken || parentheses != 0) {
                            reject(Failure.UnsupportedExpression)
                            return
                        }
                        if (templates.size >= MAX_TEMPLATES) {
                            reject(Failure.TemplateLimit)
                            return
                        }
                        templates += source.substring(start, cursor)
                        expressionStart = null
                        advance()
                        return
                    }
                    source[cursor] == '(' -> {
                        parentheses++
                        if (parentheses > MAX_NESTING) reject(Failure.NestingLimit)
                        else { hasToken = true; advance(visible = true) }
                    }
                    source[cursor] == ')' -> {
                        parentheses--
                        if (parentheses < 0) reject(Failure.UnsupportedExpression)
                        else advance(visible = true)
                    }
                    identifierPart(source[cursor]) || source[cursor] in ".?:/+*-%,!<>&|" -> {
                        hasToken = true
                        advance(visible = true)
                    }
                    source[cursor] in " \t\r\n" -> advance(visible = true)
                    else -> reject(Failure.UnsupportedExpression)
                }
            }
            reject(Failure.UnclosedInterpolation)
        }

        private fun identifierStart(char: Char): Boolean = char in 'a'..'z' || char in 'A'..'Z' || char == '_'
        private fun identifierPart(char: Char): Boolean = identifierStart(char) || char in '0'..'9'
    }
}
