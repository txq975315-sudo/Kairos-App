package com.example.taskmodel.model

import org.junit.Assert.assertEquals
import org.junit.Test

class UrgencyConfigTest {

    @Test
    fun defaultLevels_has4Entries() {
        assertEquals(4, UrgencyConfig.defaultLevels().size)
    }

    @Test
    fun defaultLevels_hasCorrectLevelValues() {
        val levels = UrgencyConfig.defaultLevels()
        assertEquals(0, levels[0].level)
        assertEquals(1, levels[1].level)
        assertEquals(2, levels[2].level)
        assertEquals(3, levels[3].level)
    }

    @Test
    fun defaultLevels_hasDesignReferenceColors() {
        val levels = UrgencyConfig.defaultLevels()
        assertEquals("#FF0000", levels[0].colorHex)
        assertEquals("#FF7A00", levels[1].colorHex)
        assertEquals("#FFD700", levels[2].colorHex)
        assertEquals("#007BFF", levels[3].colorHex)
    }

    @Test
    fun defaultLevels_hasChineseLabels() {
        val levels = UrgencyConfig.defaultLevels()
        assertEquals("紧急", levels[0].label)
        assertEquals("较紧急", levels[1].label)
        assertEquals("一般", levels[2].label)
        assertEquals("不紧急", levels[3].label)
    }

    @Test
    fun colorForLevel_validLevel_returnsHex() {
        val config = UrgencyConfig()
        assertEquals("#FF0000", config.colorForLevel(0))
        assertEquals("#007BFF", config.colorForLevel(3))
    }

    @Test
    fun colorForLevel_invalidLevel_returnsFallback() {
        val config = UrgencyConfig()
        assertEquals("#9E9E9E", config.colorForLevel(999))
    }

    @Test
    fun labelForLevel_validLevel_returnsLabel() {
        val config = UrgencyConfig()
        assertEquals("较紧急", config.labelForLevel(1))
    }

    @Test
    fun labelForLevel_invalidLevel_returnsEmpty() {
        val config = UrgencyConfig()
        assertEquals("", config.labelForLevel(999))
    }

    @Test
    fun toJson_then_fromJson_roundTrip() {
        val original = UrgencyConfig(listOf(
            UrgencyLevelConfig(0, "Custom", "Desc", "#AABBCC"),
            UrgencyLevelConfig(1, "High", "Desc2", "#112233"),
            UrgencyLevelConfig(2, "Med", "Desc3", "#445566"),
            UrgencyLevelConfig(3, "Low", "Desc4", "#778899")
        ))
        val json = original.toJson()
        val restored = UrgencyConfig.fromJson(json)
        assertEquals(4, restored.levels.size)
        assertEquals("Custom", restored.levels[0].label)
        assertEquals("#AABBCC", restored.levels[0].colorHex)
        assertEquals("Desc2", restored.levels[1].description)
        assertEquals("#778899", restored.levels[3].colorHex)
    }

    @Test
    fun fromJson_null_returnsDefault() {
        val config = UrgencyConfig.fromJson(null)
        assertEquals(4, config.levels.size)
        assertEquals("#FF0000", config.colorForLevel(0))
    }

    @Test
    fun fromJson_emptyString_returnsDefault() {
        val config = UrgencyConfig.fromJson("")
        assertEquals(4, config.levels.size)
    }

    @Test
    fun fromJson_invalidJson_returnsDefault() {
        val config = UrgencyConfig.fromJson("{invalid}")
        assertEquals(4, config.levels.size)
    }

    @Test
    fun fromJson_partialFields_usesDefaults() {
        val json = "[{\"level\":0,\"label\":\"Test\"}]"
        val config = UrgencyConfig.fromJson(json)
        assertEquals(1, config.levels.size)
        assertEquals("Test", config.levels[0].label)
        assertEquals("", config.levels[0].description)
        assertEquals("#9E9E9E", config.levels[0].colorHex)
    }
}