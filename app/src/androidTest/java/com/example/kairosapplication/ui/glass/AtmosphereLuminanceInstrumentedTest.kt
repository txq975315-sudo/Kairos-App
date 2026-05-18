package com.example.kairosapplication.ui.glass

import android.graphics.BitmapFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.kairosapplication.core.ui.constants.GlassConstants
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Verifies bundled atmosphere presets:
 * - [kairos_light_wallpaper.jpg] → bright top band → dark chrome text
 * - [kairos_dark_wallpaper.jpg] → dark top band → light chrome text
 */
@RunWith(AndroidJUnit4::class)
class AtmosphereLuminanceInstrumentedTest {

    @Test
    fun darkWallpaperAsset_topZoneIsDark_usesOnDarkChrome() {
        val zones = zonesFromAsset("kairos_dark_wallpaper.jpg")
        val ui = GlassAtmosphereUi.fromZones(zones)

        assertEquals(false, zones.topIsLight)
        assertEquals(GlassTextColors.onDarkBackground().primary, ui.topChrome.primary)
    }

    @Test
    fun lightWallpaperAsset_topZoneIsLight_usesOnLightChrome() {
        val zones = zonesFromAsset("kairos_light_wallpaper.jpg")
        val ui = GlassAtmosphereUi.fromZones(zones)

        assertEquals(true, zones.topIsLight)
        assertEquals(GlassTextColors.onLightBackground().primary, ui.topChrome.primary)
        assertEquals(GlassConstants.ChromeOnLightPrimary, ui.topChrome.primary)
    }

    private fun zonesFromAsset(fileName: String): GlassAtmosphereZones {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val bytes = context.assets.open(fileName).use { it.readBytes() }
        val options = BitmapFactory.Options().apply { inSampleSize = 8 }
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)
        checkNotNull(bitmap) { "Failed to decode $fileName" }
        val topEnd = (bitmap.height * GlassConstants.AtmosphereTopZoneFraction).toInt().coerceAtLeast(1)
        val bottomStart = (bitmap.height * GlassConstants.AtmosphereBottomZoneStartFraction)
            .toInt()
            .coerceIn(0, bitmap.height - 1)
        val topLum = AtmosphereLuminance.averageLuminance(bitmap, yStart = 0, yEnd = topEnd)
        val bottomLum = AtmosphereLuminance.averageLuminance(
            bitmap,
            yStart = bottomStart,
            yEnd = bitmap.height,
        )
        bitmap.recycle()
        return GlassAtmosphereZones(
            topIsLight = topLum >= GlassConstants.AtmosphereLightZoneThreshold,
            bottomIsLight = bottomLum >= GlassConstants.AtmosphereLightZoneThreshold,
        )
    }
}
