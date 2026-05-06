package com.example.taskmodel.model

data class AllRecords(
    val completedTasks: Int,
    val uncompletedTasks: Int,
    val todayCompletedTasks: Int,
    /** Distinct calendar days with at least one completed task (lifetime). */
    val distinctCompletionDays: Int = 0,
    /** Incomplete tasks scheduled for today. */
    val todayIncompleteCount: Int = 0
)
