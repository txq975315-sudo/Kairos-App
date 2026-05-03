package com.example.taskmodel.store

import com.example.taskmodel.model.Task

/**
 * In-process task creation bus: CreateScreen pushes, TodayScreen consumes.
 */
object TaskCreationBus {
    private val pendingTasks = mutableListOf<Task>()

    @Synchronized
    fun push(tasks: List<Task>) {
        pendingTasks.addAll(tasks)
    }

    @Synchronized
    fun consumeAll(): List<Task> {
        val snapshot = pendingTasks.toList()
        pendingTasks.clear()
        return snapshot
    }
}
