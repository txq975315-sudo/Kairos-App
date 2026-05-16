package com.example.kairosapplication.ui

import androidx.compose.ui.unit.dp
import com.example.kairosapplication.core.ui.AppColors
import org.junit.Test
import org.junit.Assert.*

/**
 * Tests for [AppColors] glass-effect constants used by [TaskItemCard].
 *
 * Following TDD: these tests are written BEFORE the production code
 * to validate the new background blur layer.
 */
class TaskCardStyleTest {

    @Test
    fun taskCardBackgroundBlurRadius_exists() {
        // The new background blur layer should have a dedicated constant.
        // This test will fail to compile until AppColors.TaskCardBackgroundBlurRadius is added.
        val radius = AppColors.TaskCardBackgroundBlurRadius
        assertEquals("Background blur radius must be 30.dp", 30.dp, radius)
    }

    @Test
    fun taskCardBackgroundBlurRadius_isPositive() {
        val radius = AppColors.TaskCardBackgroundBlurRadius
        assertTrue("Background blur radius must be positive", radius > 0.dp)
    }

    @Test
    fun taskCardBackgroundBlurRadius_isLessThanMainBlur() {
        val bgRadius = AppColors.TaskCardBackgroundBlurRadius
        val mainRadius = AppColors.TaskCardBlurRadius
        assertTrue(
            "Background blur ($bgRadius) should be lighter than main blur ($mainRadius)",
            bgRadius < mainRadius
        )
    }
}
