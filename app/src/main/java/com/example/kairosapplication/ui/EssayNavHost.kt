package com.example.kairosapplication.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.kairosapplication.ui.editor.NoteEditorScreen
import com.example.taskmodel.constants.NotePrimaryCategory
import com.example.kairosapplication.ui.inbox.InboxListScreen
import com.example.kairosapplication.ui.project.ProjectTimelineScreen
import com.example.kairosapplication.ui.search.SearchScreen
import com.example.kairosapplication.ui.trash.TrashScreen
import com.example.taskmodel.viewmodel.TaskViewModel

private const val EssayMainRoute = "essay_main"

/**
 * Essay module nested navigation: main tabs, inbox, search, editor, project timeline.
 */
@Composable
fun EssayNavHost(
    navController: NavHostController,
    taskViewModel: TaskViewModel,
    startDestination: String = EssayMainRoute
) {
    val onNavigateToInbox = { navController.navigate("essay_inbox") }
    val onNavigateToSearch = { navController.navigate("essay_search") }
    val onNavigateToEditor: (Long?) -> Unit = { noteId ->
        val route = if (noteId != null) "essay_editor/$noteId" else "essay_editor/new"
        navController.navigate(route)
    }
    val onNavigateToProject: (Long) -> Unit = { projectId ->
        navController.navigate("essay_project/$projectId")
    }
    val onNavigateToTrash: () -> Unit = { navController.navigate("essay_trash") }
    val onBack: () -> Unit = { navController.popBackStack() }
    val onSaveEditor: () -> Unit = {
        if (!navController.popBackStack(EssayMainRoute, inclusive = false)) {
            navController.popBackStack()
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(EssayMainRoute) {
            EssayMainScreen(
                taskViewModel = taskViewModel,
                onNavigateToInbox = onNavigateToInbox,
                onNavigateToSearch = onNavigateToSearch,
                onNavigateToTrash = onNavigateToTrash,
                onNavigateToEditor = onNavigateToEditor,
                onNavigateToNewNoteFromTopic = { primaryKey ->
                    navController.navigate("essay_editor/new_locked/$primaryKey")
                },
                onNavigateToProject = onNavigateToProject
            )
        }
        composable("essay_trash") {
            TrashScreen(
                taskViewModel = taskViewModel,
                onBackClick = onBack,
                onNoteClick = { noteId -> navController.navigate("essay_editor/$noteId") }
            )
        }
        composable("essay_inbox") {
            InboxListScreen(
                taskViewModel = taskViewModel,
                onBackClick = onBack,
                onNoteClick = { noteId -> onNavigateToEditor(noteId) },
                onQuickClassify = { noteId, category ->
                    taskViewModel.quickClassifyNote(noteId, category)
                }
            )
        }
        composable("essay_search") {
            SearchScreen(
                taskViewModel = taskViewModel,
                onBackClick = onBack,
                onNoteClick = { noteId -> onNavigateToEditor(noteId) },
                onNavigateToNewNote = { onNavigateToEditor(null) }
            )
        }
        composable(
            route = "essay_editor/new_locked/{primaryKey}",
            arguments = listOf(navArgument("primaryKey") { type = NavType.StringType })
        ) { entry ->
            val raw = entry.arguments?.getString("primaryKey").orEmpty()
            val lockPrimary = raw.takeIf { NotePrimaryCategory.isTopic(it) }
            NoteEditorScreen(
                noteId = null,
                taskViewModel = taskViewModel,
                onBackClick = onBack,
                onSaveComplete = onSaveEditor,
                lockedPrimaryCategory = lockPrimary
            )
        }
        composable("essay_editor/new") {
            NoteEditorScreen(
                noteId = null,
                taskViewModel = taskViewModel,
                onBackClick = onBack,
                onSaveComplete = onSaveEditor,
                lockedPrimaryCategory = null
            )
        }
        composable(
            route = "essay_editor/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.LongType })
        ) { entry ->
            val noteId = entry.arguments?.getLong("noteId")
            if (noteId != null) {
                NoteEditorScreen(
                    noteId = noteId,
                    taskViewModel = taskViewModel,
                    onBackClick = onBack,
                    onSaveComplete = onSaveEditor
                )
            }
        }
        composable(
            route = "essay_project/{projectId}",
            arguments = listOf(navArgument("projectId") { type = NavType.LongType })
        ) { entry ->
            val projectId = entry.arguments?.getLong("projectId") ?: return@composable
            ProjectTimelineScreen(
                projectId = projectId,
                taskViewModel = taskViewModel,
                onBack = onBack,
                onNoteClick = { id -> onNavigateToEditor(id) },
                onNavigateToNewNote = { onNavigateToEditor(null) }
            )
        }
    }
}
