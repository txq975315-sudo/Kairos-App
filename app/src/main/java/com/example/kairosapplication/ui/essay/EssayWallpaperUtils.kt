package com.example.kairosapplication.ui.essay

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.max
import kotlin.math.min

/**
 * Samples a downscaled bitmap from [uriString] and returns whether the wallpaper is dark enough
 * to warrant light foreground text.
 */
suspend fun computeWallpaperIsDark(context: Context, uriString: String): Boolean =
    withContext(Dispatchers.IO) {
        runCatching {
            val loader = ImageLoader(context)
            val req = ImageRequest.Builder(context)
                .data(uriString)
                .allowHardware(false)
                .size(128, 128)
                .build()
            val drawable = loader.execute(req).drawable ?: return@runCatching false
            val bmp = drawable.toBitmap(128, 128, Bitmap.Config.ARGB_8888)
            averageLuminance(bmp) < 0.42
        }.getOrDefault(false)
    }

private fun averageLuminance(bitmap: Bitmap): Double {
    val w = bitmap.width
    val h = bitmap.height
    if (w <= 0 || h <= 0) return 1.0
    var sum = 0.0
    var n = 0
    val step = max(1, min(w, h) / 12)
    var y = 0
    while (y < h) {
        var x = 0
        while (x < w) {
            val p = bitmap.getPixel(
                min(x, w - 1),
                min(y, h - 1)
            )
            val r = android.graphics.Color.red(p) / 255.0
            val g = android.graphics.Color.green(p) / 255.0
            val b = android.graphics.Color.blue(p) / 255.0
            sum += 0.2126 * r + 0.7152 * g + 0.0722 * b
            n++
            x += step
        }
        y += step
    }
    return if (n == 0) 1.0 else sum / n
}
