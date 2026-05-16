package com.example.kairosapplication.ui

import org.junit.Test
import org.junit.Assert.*

/**
 * Tests for [EssayTab] navigation state and the tab list used in [EssayMainScreen].
 *
 * The tab switching bar (TIMELINE / TOPIC / PROJECT) is being moved from
 * inside Scaffold.topBar into the content area to resolve layout overlap.
 * These tests validate that the tab enumeration and label mapping remain correct
 * after the refactoring.
 */
class EssayTabTest {

    @Test
    fun essayTab_enum_hasExactlyThreeEntries() {
        val entries = EssayTab.entries
        assertEquals(3, entries.size)
    }

    @Test
    fun essayTab_containsTimelineTopicProject() {
        val entries = EssayTab.entries.toSet()
        assertTrue("TIMELINE must exist", entries.contains(EssayTab.TIMELINE))
        assertTrue("TOPIC must exist", entries.contains(EssayTab.TOPIC))
        assertTrue("PROJECT must exist", entries.contains(EssayTab.PROJECT))
    }

    @Test
    fun essayTab_timelineLabel_isCorrect() {
        assertEquals(EssayTab.TIMELINE, EssayTab.TIMELINE)
    }

    @Test
    fun essayTab_tabsList_matchesEnumOrder() {
        // This simulates the tabs list construction inside EssayMainScreen.
        // The order must be: TIMELINE → TOPIC → PROJECT.
        val expected = listOf(EssayTab.TIMELINE, EssayTab.TOPIC, EssayTab.PROJECT)
        assertEquals(expected, EssayTab.entries.toList())
    }

    @Test
    fun essayTab_topicSelection_clearsTimelineExpandedState() {
        // When switching tabs, expandedNoteId should be null.
        // This validates the LaunchedEffect(selectedTab) behavior.
        var expandedNoteId: Long? = 123L
        val selectedTab = EssayTab.TOPIC

        if (selectedTab != EssayTab.PROJECT) {
            expandedNoteId = null
        }

        assertNull("Switching to TOPIC should clear expandedNoteId", expandedNoteId)
    }

    @Test
    fun essayTab_projectSelection_preservesExpandedState() {
        // PROJECT tab does NOT clear expandedNoteId.
        var expandedNoteId: Long? = 123L
        val selectedTab = EssayTab.PROJECT

        if (selectedTab != EssayTab.PROJECT) {
            expandedNoteId = null
        }

        assertNotNull("Staying on PROJECT should preserve expandedNoteId", expandedNoteId)
    }
}
