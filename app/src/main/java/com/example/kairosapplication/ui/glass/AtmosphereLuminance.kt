package com.example.kairosapplication.ui.glass

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import com.example.kairosapplication.core.ui.constants.GlassConstants

/**
 * Samples top / bottom horizontal bands of the atmosphere drawable (downscaled once).
 * Memory: single small bitmap via [BitmapFactory.Options.inSampleSize], recycled after read.
 */
object AtmosphereLuminance {

    fun sampleZones(context: Context, @DrawableRes resId: Int): GlassAtmosphereZones {
        val options = BitmapFactory.Options().apply {
            inSampleSize = 8
        }
        val bitmap = BitmapFactory.decodeResource(context.resources, resId, options) ?: return GlassAtmosphereZones.default()
        return try {
            val topEnd = (bitmap.height * GlassConstants.AtmosphereTopZoneFraction).toInt().coerceAtLeast(1)
            val bottomStart = (bitmap.height * GlassConstants.AtmosphereBottomZoneStartFraction)
                .toInt()
                .coerceIn(0, bitmap.height - 1)
            val topLum = averageLuminance(bitmap, yStart = 0, yEnd = topEnd)
            val bottomLum = averageLuminance(bitmap, yStart = bottomStart, yEnd = bitmap.height)
            GlassAtmosphereZones(
                topIsLight = topLum >= GlassConstants.AtmosphereLightZoneThreshold,
                bottomIsLight = bottomLum >= GlassConstants.AtmosphereLightZoneThreshold,
            )
        } finally {
            bitmap.recycle()
        }
    }

    internal fun averageLuminance(bitmap: Bitmap, yStart: Int, yEnd: Int): Float {
        val start = yStart.coerceIn(0, bitmap.height)
        val end = yEnd.coerceIn(start + 1, bitmap.height)
        var sum = 0f
        var count = 0
        for (y in start until end) {
            for (x in 0 until bitmap.width) {
                val pixel = bitmap.getPixel(x, y)
                sum += Color(pixel).luminance()
                count++
            }
        }
        return if (count == 0) 0f else sum / count
    }
}
