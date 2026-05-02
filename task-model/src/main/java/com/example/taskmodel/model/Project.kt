package com.example.taskmodel.model

/**
 * User project for Essay module.
 */
data class Project(
    val id: Long = 0,
    val name: String,
    val description: String?,
    val coverImageUri: String?,
    val createdAt: Long,
    val updatedAt: Long
)
