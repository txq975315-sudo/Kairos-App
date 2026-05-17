package com.example.kairosapplication.ui.glass

import org.junit.Assert.assertEquals
import org.junit.Test

class AtmosphereLuminanceTest {

    @Test
    fun fromZones_topLight_usesDarkChrome_bottomDark_usesLightChrome() {
        val ui = GlassAtmosphereUi.fromZones(
            GlassAtmosphereZones(topIsLight = true, bottomIsLight = false),
        )
        assertEquals(GlassTextColors.onLightBackground().primary, ui.topChrome.primary)
        assertEquals(GlassTextColors.onDarkBackground().primary, ui.bottomChrome.primary)
    }

    @Test
    fun fromZones_bothLight_usesDarkChrome() {
        val ui = GlassAtmosphereUi.fromZones(
            GlassAtmosphereZones(topIsLight = true, bottomIsLight = true),
        )
        assertEquals(GlassTextColors.onLightBackground().primary, ui.topChrome.primary)
        assertEquals(GlassTextColors.onLightBackground().primary, ui.bottomChrome.primary)
    }

    @Test
    fun cardText_alwaysOnDarkBubble() {
        val ui = GlassAtmosphereUi.fromZones(GlassAtmosphereZones(topIsLight = true, bottomIsLight = true))
        assertEquals(GlassTextColors.onDarkBubble().primary, ui.cardText.primary)
    }
}
