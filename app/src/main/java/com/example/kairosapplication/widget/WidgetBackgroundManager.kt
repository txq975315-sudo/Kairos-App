package com.example.kairosapplication.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RadialGradient
import android.graphics.Shader
import android.net.Uri
import androidx.core.graphics.drawable.toBitmap
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Scale
import com.example.kairosapplication.widget.data.WidgetBackground
import com.example.kairosapplication.widget.data.WidgetBackgroundType
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

object WidgetBackgroundManager {

    suspend fun generateBackgroundBitmap(
        context: Context,
        background: WidgetBackground,
        width: Int,
        height: Int
    ): Bitmap? {
        val w = max(2, width)
        val h = max(2, height)
        return withContext(Dispatchers.Default) {
            when (background.type) {
                WidgetBackgroundType.WHITE -> createSolidColorBitmap(Color.WHITE, w, h)
                WidgetBackgroundType.SOLID_COLOR -> {
                    val c = runCatching { Color.parseColor(background.solidColor) }.getOrDefault(Color.WHITE)
                    createSolidColorBitmap(c, w, h)
                }
                WidgetBackgroundType.IMAGE -> generateImageBackground(context, background, w, h)
                WidgetBackgroundType.GRADIENT -> generateGradientBitmap(background, w, h)
            }
        }
    }

    private fun createSolidColorBitmap(color: Int, width: Int, height: Int): Bitmap {
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            eraseColor(color)
        }
    }

    private suspend fun generateImageBackground(
        context: Context,
        background: WidgetBackground,
        width: Int,
        height: Int
    ): Bitmap? {
        val uriStr = background.imageUri ?: return null
        val uri = Uri.parse(uriStr)
        return withContext(Dispatchers.IO) {
            runCatching {
                val request = ImageRequest.Builder(context)
                    .data(uri)
                    .size(width, height)
                    .scale(Scale.FILL)
                    .build()
                val result = context.imageLoader.execute(request)
                val drawable = (result as? SuccessResult)?.drawable ?: return@runCatching null
                val decoded = drawable.toBitmap(width, height, Bitmap.Config.ARGB_8888)
                var out = decoded
                if (background.blurRadius > 0) {
                    val blurred = blurByResize(out, background.blurRadius.coerceIn(0, 20))
                    if (blurred !== out && !out.isRecycled) out.recycle()
                    out = blurred
                }
                if (background.alphaPercent < 100) {
                    val a =
                        (background.alphaPercent.coerceIn(10, 100) * 255 / 100f).roundToInt().coerceIn(25, 255)
                    val withAlpha = applyAlpha(out, a)
                    if (withAlpha !== out && !out.isRecycled) out.recycle()
                    out = withAlpha
                }
                if (out.width != width || out.height != height) {
                    val scaled = Bitmap.createScaledBitmap(out, width, height, true)
                    if (scaled !== out && !out.isRecycled) out.recycle()
                    out = scaled
                }
                out
            }.getOrNull()
        }
    }

    private fun blurByResize(source: Bitmap, radius: Int): Bitmap {
        if (radius <= 0) return source
        val factor = 1f / (1f + radius / 6f)
        val w = max(1, (source.width * factor).toInt())
        val h = max(1, (source.height * factor).toInt())
        val small = Bitmap.createScaledBitmap(source, w, h, true)
        val enlarged = Bitmap.createScaledBitmap(small, source.width, source.height, true)
        if (small !== source && small !== enlarged && !small.isRecycled) small.recycle()
        return enlarged
    }

    private fun generateGradientBitmap(background: WidgetBackground, width: Int, height: Int): Bitmap {
        val startColor =
            runCatching { Color.parseColor(background.gradientStartColor) }.getOrDefault(Color.WHITE)
        val endColor =
            runCatching { Color.parseColor(background.gradientEndColor) }.getOrDefault(Color.LTGRAY)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val gradShader = if (background.isRadialGradient) {
            val cx = width / 2f
            val cy = height / 2f
            val r = max(width, height) / 2f
            RadialGradient(cx, cy, r, startColor, endColor, Shader.TileMode.CLAMP)
        } else {
            val angleRad = Math.toRadians(background.gradientAngle.toDouble())
            val cx = width / 2f
            val cy = height / 2f
            val dx = (cos(angleRad) * width / 2).toFloat()
            val dy = (sin(angleRad) * height / 2).toFloat()
            LinearGradient(
                cx - dx,
                cy - dy,
                cx + dx,
                cy + dy,
                startColor,
                endColor,
                Shader.TileMode.CLAMP
            )
        }
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            shader = gradShader
        }
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        return bitmap
    }

    private fun applyAlpha(bitmap: Bitmap, alpha: Int): Bitmap {
        val result = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.alpha = alpha
        }
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return result
    }

    fun calculateTextColor(backgroundColor: Int): Int {
        val r = Color.red(backgroundColor)
        val g = Color.green(backgroundColor)
        val b = Color.blue(backgroundColor)
        val luminance = (0.299 * r + 0.587 * g + 0.114 * b) / 255.0
        return if (luminance > 0.5) Color.parseColor("#1A1A1A") else Color.parseColor("#FFFFFF")
    }

    fun approximateArgbFromBitmap(bitmap: Bitmap): Int {
        val w = bitmap.width
        val h = bitmap.height
        if (w <= 0 || h <= 0) return Color.WHITE
        val samples = intArrayOf(
            bitmap.getPixel(w / 2, h / 2),
            bitmap.getPixel(w / 4, h / 4),
            bitmap.getPixel(3 * w / 4, h / 4),
            bitmap.getPixel(w / 4, 3 * h / 4),
            bitmap.getPixel(3 * w / 4, 3 * h / 4)
        )
        var a = 0
        var r = 0
        var g = 0
        var b = 0
        val n = samples.size
        for (p in samples) {
            a += Color.alpha(p)
            r += Color.red(p)
            g += Color.green(p)
            b += Color.blue(p)
        }
        return Color.argb(a / n, r / n, g / n, b / n)
    }
}
