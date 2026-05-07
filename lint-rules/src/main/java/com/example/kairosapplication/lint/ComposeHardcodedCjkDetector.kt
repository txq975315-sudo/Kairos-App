package com.example.kairosapplication.lint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement
import org.jetbrains.uast.ULiteralExpression
import org.jetbrains.uast.evaluateString
import org.jetbrains.uast.visitor.AbstractUastVisitor

/**
 * Flags CJK inside [androidx.compose.material3.Text] / material [Text] when not under a
 * [stringResource] call, so UI copy goes through resources / stringResource.
 */
class ComposeHardcodedCjkDetector : Detector(), Detector.UastScanner {

    override fun getApplicableUastTypes() = listOf(UCallExpression::class.java)

    override fun createUastHandler(context: JavaContext) = object : UElementHandler() {
        override fun visitCallExpression(node: UCallExpression) {
            if (!isComposeTextCall(node)) return
            node.accept(
                object : AbstractUastVisitor() {
                    override fun visitLiteralExpression(expression: ULiteralExpression): Boolean {
                        val text = expression.evaluateString() ?: return super.visitLiteralExpression(expression)
                        if (!text.any { it in CJK_RANGE }) return super.visitLiteralExpression(expression)
                        if (expression.hasStringResourceAncestor(node)) {
                            return super.visitLiteralExpression(expression)
                        }
                        context.report(
                            ISSUE,
                            expression,
                            context.getLocation(expression),
                            "Use stringResource / resources for user-visible Chinese text instead of a string literal in Text().",
                        )
                        return super.visitLiteralExpression(expression)
                    }
                },
            )
        }
    }

    companion object {
        private val CJK_RANGE = '\u4e00'..'\u9fff'

        private fun isComposeTextCall(call: UCallExpression): Boolean {
            val method = call.resolve() ?: return false
            if (method.name != "Text") return false
            val fq = method.containingClass?.qualifiedName ?: return false
            return fq == "androidx.compose.material3.TextKt" ||
                fq == "androidx.compose.material.TextKt"
        }

        private fun ULiteralExpression.hasStringResourceAncestor(boundary: UCallExpression): Boolean {
            var p: UElement? = uastParent
            while (p != null && p != boundary) {
                if (p is UCallExpression && p.resolve()?.name == "stringResource") return true
                p = p.uastParent
            }
            return false
        }

        @JvmField
        val ISSUE: Issue =
            Issue.create(
                id = "ComposeHardcodedCjkText",
                briefDescription = "Compose Text() uses a hardcoded CJK string",
                explanation =
                    """
                    User-visible Chinese text should come from `stringResource(R.string...)` (or an
                    approved i18n helper), not string literals inside `Text()`, so it can be translated
                    and reviewed consistently.
                    """.trimIndent(),
                category = Category.I18N,
                priority = 5,
                severity = Severity.WARNING,
                implementation = Implementation(
                    ComposeHardcodedCjkDetector::class.java,
                    Scope.JAVA_FILE_SCOPE,
                ),
            )
    }
}
