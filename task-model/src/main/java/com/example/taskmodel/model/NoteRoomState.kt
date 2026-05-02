package com.example.taskmodel.model

/**
 * Bundled Essay (Room) snapshot for [com.example.taskmodel.viewmodel.TaskViewModel].
 */
data class NoteRoomState(
    val published: List<Note>,
    val inbox: List<Note>,
    val projects: List<Project>,
    /** Soft-deleted notes (status `deleted`) for Trash screen */
    val trash: List<Note> = emptyList()
)
