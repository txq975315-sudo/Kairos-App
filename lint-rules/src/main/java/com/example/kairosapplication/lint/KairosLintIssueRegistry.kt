package com.example.kairosapplication.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class KairosLintIssueRegistry : IssueRegistry() {

    override val issues: List<Issue> = listOf(ComposeHardcodedCjkDetector.ISSUE)

    override val api: Int = CURRENT_API

    override val minApi: Int = 8

    override val vendor: Vendor = Vendor(
        vendorName = "KairosApplication",
        feedbackUrl = "https://github.com/example/kairos",
        contact = "https://github.com/example/kairos",
    )
}
