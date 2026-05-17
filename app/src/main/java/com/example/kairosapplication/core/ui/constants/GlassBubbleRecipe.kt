package com.example.kairosapplication.core.ui.constants

/**
 * Bubble recipe for on-device A/B/C preview.
 *
 * - [A]: light dark bubble (black ~22% + white sheen)
 * - [B]: lighter tint only (black ~10% + sheen) — subtle vs A
 * - [C]: [dev.chrisbanes.haze] backdrop blur + light tint (reference chat glass)
 *
 * Set [active] to compare; use [A] if C is not preferred.
 */
enum class GlassBubbleRecipe {
    A,
    B,
    C,
    ;

    companion object {
        /** Current recipe — [C] backdrop blur preview. */
        val active: GlassBubbleRecipe = C
    }
}
