#!/usr/bin/env kotlin

/**
 * Optional periodic scan for hardcoded CJK in Compose-style calls.
 *
 * Usage (from repo root, Kotlin CLI installed):
 *   kotlin scripts/scan-hardcoded-text.main.kts
 *   kotlin scripts/scan-hardcoded-text.main.kts --fail
 *
 * Default prints matches and exits 0; `--fail` exits 1 if any match (for CI).
 */
import java.io.File

val cwd = File(System.getProperty("user.dir"))
val rootArg = args.filterNot { it == "--fail" }.firstOrNull() ?: "app/src/main/java"
val root = File(rootArg).let { if (it.isAbsolute) it else File(cwd, it.path) }
val fail = args.contains("--fail")

val patterns =
    listOf(
        "Text(…)" to Regex("""Text\s*\(\s*"[^"]*[\u4e00-\u9fff][^"]*""""),
        "string assignment" to
            Regex("""^\s*(?:val|var)\s+\w+\s*=\s*"[^"]*[\u4e00-\u9fff][^"]*"""),
    )

fun shouldSkipLine(raw: String): Boolean {
    val t = raw.trimStart()
    if (t.startsWith("//") || t.startsWith("*")) return true
    if ("stringResource" in raw || "LocalizedStrings" in raw) return true
    if ("ofPattern(" in raw || "optString(" in raw || "DateTimeFormatter" in raw) return true
    return false
}

fun shouldSkipPath(rel: String): Boolean =
    "/test/" in rel ||
        "/androidTest/" in rel ||
        rel.endsWith("EmojiConstants.kt", ignoreCase = true)

val hits = mutableListOf<String>()
if (!root.isDirectory) {
    println("Not a directory: ${root.absolutePath}")
    kotlin.system.exitProcess(if (fail) 1 else 0)
} else {
    root.walkTopDown().filter { it.isFile && it.extension == "kt" }.forEach { file ->
        val rel = file.relativeTo(cwd).path.replace('\\', '/')
        if (shouldSkipPath(rel)) return@forEach
        file.readLines().forEachIndexed { idx, rawLine ->
            if (shouldSkipLine(rawLine)) return@forEachIndexed
            for ((label, regex) in patterns) {
                if (regex.containsMatchIn(rawLine)) {
                    hits += "$rel:${idx + 1} [$label] ${rawLine.trim()}"
                }
            }
        }
    }
}

if (hits.isEmpty()) {
    println("No hardcoded CJK matches for configured patterns under ${root.absolutePath}")
} else {
    println(hits.joinToString("\n"))
    if (fail) kotlin.system.exitProcess(1)
}
