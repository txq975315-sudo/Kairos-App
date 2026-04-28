package com.example.taskmodel.store

import com.example.taskmodel.model.Task

/**
 * 简单进程内任务创建总线：CreateScreen 推送，TodayScreen 消费。
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
