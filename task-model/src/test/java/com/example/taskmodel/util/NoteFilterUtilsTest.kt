package com.example.taskmodel.util

import com.example.taskmodel.model.Note
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class NoteFilterUtilsTest {

    // ── Test data ──────────────────────────────────────────────────────

    private val today = LocalDate.of(2026, 5, 9)

    private val note1 = Note(
        id = 1, primaryCategory = "工作", secondaryCategory = "项目",
        behaviorSummary = "完成了设计稿", body = "今天完成了首页设计稿的最终版本",
        sceneTags = listOf("设计"), moodIcon = "😊", linkedCategories = emptyList(),
        projectIds = emptyList(), recordedDate = today, createdAt = 1000, updatedAt = 1000,
        status = "published", deadline = null, needsManualClassification = false
    )

    private val note2 = Note(
        id = 2, primaryCategory = "生活", secondaryCategory = "健康",
        behaviorSummary = "晨跑五公里", body = "清晨六点出门跑步，天气很好",
        sceneTags = listOf("运动"), moodIcon = "💪", linkedCategories = emptyList(),
        projectIds = emptyList(), recordedDate = today.minusDays(1), createdAt = 900, updatedAt = 900,
        status = "published", deadline = null, needsManualClassification = false
    )

    private val note3 = Note(
        id = 3, primaryCategory = "学习", secondaryCategory = "编程",
        behaviorSummary = "学习 Kotlin 协程", body = "Kotlin coroutines are lightweight threads",
        sceneTags = listOf("编程", "Kotlin"), moodIcon = "🤔", linkedCategories = emptyList(),
        projectIds = emptyList(), recordedDate = today.minusDays(2), createdAt = 800, updatedAt = 800,
        status = "published", deadline = null, needsManualClassification = false
    )

    private val note4 = Note(
        id = 4, primaryCategory = "工作", secondaryCategory = "会议",
        behaviorSummary = "周会记录", body = "讨论了下季度的产品规划方向",
        sceneTags = listOf("会议"), moodIcon = null, linkedCategories = emptyList(),
        projectIds = emptyList(), recordedDate = today.minusDays(3), createdAt = 700, updatedAt = 700,
        status = "published", deadline = null, needsManualClassification = false
    )

    private val allNotes = listOf(note1, note2, note3, note4)

    // ── filterNotesByQuery tests ──────────────────────────────────────

    @Test
    fun filterNotesByQuery_emptyQuery_returnsAll() {
        val result = NoteFilterUtils.filterNotesByQuery(allNotes, "")
        assertEquals(4, result.size)
        assertEquals(allNotes, result)
    }

    @Test
    fun filterNotesByQuery_blankQuery_returnsAll() {
        val result = NoteFilterUtils.filterNotesByQuery(allNotes, "   ")
        assertEquals(4, result.size)
    }

    @Test
    fun filterNotesByQuery_matchesBody_returnsMatching() {
        val result = NoteFilterUtils.filterNotesByQuery(allNotes, "设计稿")
        assertEquals(1, result.size)
        assertEquals(1L, result[0].id)
    }

    @Test
    fun filterNotesByQuery_matchesBehaviorSummary_returnsMatching() {
        val result = NoteFilterUtils.filterNotesByQuery(allNotes, "晨跑")
        assertEquals(1, result.size)
        assertEquals(2L, result[0].id)
    }

    @Test
    fun filterNotesByQuery_matchesCategory_returnsMatching() {
        val result = NoteFilterUtils.filterNotesByQuery(allNotes, "工作")
        assertEquals(2, result.size)
        assertTrue(result.all { it.primaryCategory == "工作" })
    }

    @Test
    fun filterNotesByQuery_matchesSecondaryCategory_returnsMatching() {
        val result = NoteFilterUtils.filterNotesByQuery(allNotes, "编程")
        assertEquals(1, result.size)
        assertEquals(3L, result[0].id)
    }

    @Test
    fun filterNotesByQuery_caseInsensitive_returnsMatching() {
        val result = NoteFilterUtils.filterNotesByQuery(allNotes, "kotlin")
        assertEquals(1, result.size)
        assertEquals(3L, result[0].id)
    }

    @Test
    fun filterNotesByQuery_noMatch_returnsEmpty() {
        val result = NoteFilterUtils.filterNotesByQuery(allNotes, "不存在的关键词")
        assertTrue(result.isEmpty())
    }

    @Test
    fun filterNotesByQuery_chineseAndEnglishMixed_returnsMatching() {
        val result = NoteFilterUtils.filterNotesByQuery(allNotes, "coroutines")
        assertEquals(1, result.size)
    }

    @Test
    fun filterNotesByQuery_emptyList_returnsEmpty() {
        val result = NoteFilterUtils.filterNotesByQuery(emptyList(), "anything")
        assertTrue(result.isEmpty())
    }

    // ── getNotePreviewText tests ──────────────────────────────────────

    @Test
    fun getNotePreviewText_returnsFirstLine() {
        val text = NoteFilterUtils.getNotePreviewText(note1)
        assertEquals("今天完成了首页设计稿的最终版本", text)
    }

    @Test
    fun getNotePreviewText_multiline_returnsFirstLine() {
        val note = note1.copy(body = "第一行内容\n第二行内容\n第三行内容")
        val text = NoteFilterUtils.getNotePreviewText(note)
        assertEquals("第一行内容", text)
    }

    @Test
    fun getNotePreviewText_longLine_truncatesTo60() {
        val longBody = "这是一段非常非常非常非常非常非常非常非常非常非常非常非常非常非常长的文字内容" +
            "继续添加更多文字来确保总长度超过六十个字符的限制范围"
        val note = note1.copy(body = longBody)
        val text = NoteFilterUtils.getNotePreviewText(note)
        assertEquals(61, text.length) // 60 chars + "…"
        assertTrue(text.endsWith("…"))
    }

    @Test
    fun getNotePreviewText_emptyBody_fallsBackToSummary() {
        val note = note1.copy(body = "", behaviorSummary = "摘要内容")
        val text = NoteFilterUtils.getNotePreviewText(note)
        assertEquals("摘要内容", text)
    }

    @Test
    fun getNotePreviewText_bothEmpty_returnsEllipsis() {
        val note = note1.copy(body = "", behaviorSummary = "")
        val text = NoteFilterUtils.getNotePreviewText(note)
        assertEquals("…", text)
    }
}
