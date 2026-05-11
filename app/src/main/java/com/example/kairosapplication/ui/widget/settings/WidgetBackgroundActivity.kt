package com.example.kairosapplication.ui.widget.settings

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import com.example.kairosapplication.BaseLocaleActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.data.DataStoreManager
import com.example.kairosapplication.ui.mine.settings.SettingsDividerC
import com.example.kairosapplication.ui.mine.settings.SettingsL2Scaffold
import com.example.kairosapplication.ui.mine.settings.SettingsSubC
import com.example.kairosapplication.ui.mine.settings.SettingsTitleC
import com.example.kairosapplication.i18n.LocalCurrentLanguage
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.ui.theme.KairosApplicationTheme
import com.example.kairosapplication.widget.WidgetBackgroundManager
import com.example.kairosapplication.widget.WidgetManager
import com.example.kairosapplication.widget.data.WidgetBackground
import com.example.kairosapplication.widget.data.WidgetBackgroundType
import com.example.kairosapplication.widget.data.WidgetConfigRepository
import com.example.kairosapplication.widget.data.WidgetSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

class WidgetBackgroundActivity : BaseLocaleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val sizeName = intent.getStringExtra(EXTRA_SIZE) ?: WidgetSize._1X1.name
        val selectedSize =
            runCatching { WidgetSize.valueOf(sizeName) }.getOrDefault(WidgetSize._1X1)
        setContent {
            val dm = remember { DataStoreManager(applicationContext) }
            val lang =
                remember { LocalizationManager.Language.fromCode(dm.getLanguageSync()) }
            val langState = remember { mutableStateOf(lang) }
            CompositionLocalProvider(LocalCurrentLanguage provides langState) {
                KairosApplicationTheme(dynamicColor = false) {
                    BackgroundEditScreen(
                        initialSize = selectedSize,
                        onBack = { finish() }
                    )
                }
            }
        }
    }

    companion object {
        const val EXTRA_SIZE = "widget_size"

        fun createIntent(context: Context, size: WidgetSize): Intent {
            return Intent(context, WidgetBackgroundActivity::class.java).apply {
                putExtra(EXTRA_SIZE, size.name)
            }
        }
    }
}

@Composable
private fun BackgroundEditScreen(
    initialSize: WidgetSize,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val repo = remember(context) {
        WidgetConfigRepository(DataStoreManager(context.applicationContext))
    }
    var backgroundType by remember { mutableStateOf(WidgetBackgroundType.WHITE) }
    var solidColor by remember { mutableStateOf("#FFFFFF") }
    var gradientStart by remember { mutableStateOf("#E8E0FF") }
    var gradientEnd by remember { mutableStateOf("#7B61FF") }
    var imageUri by remember { mutableStateOf<String?>(null) }
    var blurRadius by remember { mutableIntStateOf(0) }
    var alphaPercent by remember { mutableIntStateOf(100) }
    var gradientAngle by remember { mutableIntStateOf(45) }
    var isRadialGradient by remember { mutableStateOf(false) }
    var baseBackground by remember { mutableStateOf<WidgetBackground?>(null) }
    var previewBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val presetColors =
        listOf("#F5F5F5", "#F0E6E6", "#E8F4F8", "#F0F0E8", "#E6F0F0", "#F0E8F0")

    fun mergedBackground(): WidgetBackground {
        val b = baseBackground ?: WidgetBackground()
        return when (backgroundType) {
            WidgetBackgroundType.WHITE -> WidgetBackground(type = WidgetBackgroundType.WHITE)
            WidgetBackgroundType.SOLID_COLOR ->
                b.copy(
                    type = WidgetBackgroundType.SOLID_COLOR,
                    solidColor = solidColor,
                    alphaPercent = alphaPercent,
                )
            WidgetBackgroundType.IMAGE ->
                WidgetBackground(
                    type = WidgetBackgroundType.IMAGE,
                    imageUri = imageUri,
                    blurRadius = blurRadius,
                    alphaPercent = alphaPercent
                )
            WidgetBackgroundType.GRADIENT ->
                WidgetBackground(
                    type = WidgetBackgroundType.GRADIENT,
                    gradientStartColor = gradientStart,
                    gradientEndColor = gradientEnd,
                    gradientAngle = gradientAngle,
                    isRadialGradient = isRadialGradient
                )
        }
    }

    val pickImage = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            runCatching {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }
            imageUri = uri.toString()
        }
    }

    LaunchedEffect(initialSize) {
        val loaded = repo.getConfig(initialSize).background
        baseBackground = loaded
        backgroundType = loaded.type
        solidColor = loaded.solidColor
        gradientStart = loaded.gradientStartColor
        gradientEnd = loaded.gradientEndColor
        imageUri = loaded.imageUri
        blurRadius = loaded.blurRadius
        alphaPercent = loaded.alphaPercent
        gradientAngle = loaded.gradientAngle
        isRadialGradient = loaded.isRadialGradient
    }

    LaunchedEffect(
        backgroundType,
        solidColor,
        imageUri,
        blurRadius,
        alphaPercent,
        gradientStart,
        gradientEnd,
        isRadialGradient,
        gradientAngle
    ) {
        val bg = mergedBackground()
        val bmp = withContext(Dispatchers.Default) {
            WidgetBackgroundManager.generateBackgroundBitmap(
                context.applicationContext,
                bg,
                720,
                400
            )
        }
        previewBitmap = bmp
    }

    com.example.kairosapplication.ui.mine.settings.SettingsL2Scaffold(
        title = LocalizedStrings.get("widget_background_style"),
        onBack = onBack
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(16.dp))
            Text(
                LocalizedStrings.get("widget_preview_area"),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = SettingsTitleC
            )
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, SettingsDividerC, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                val bmp = previewBitmap
                if (bmp != null) {
                    Image(
                        bitmap = bmp.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        LocalizedStrings.get("widget_preview_placeholder"),
                        fontSize = 14.sp,
                        color = SettingsSubC,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            Spacer(Modifier.height(24.dp))
            Text(
                LocalizedStrings.get("widget_background_type"),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = SettingsTitleC
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                WidgetBackgroundType.entries.forEach { type ->
                    val isSelected = type == backgroundType
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable { backgroundType = type }
                            .padding(vertical = 8.dp)
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = { backgroundType = type },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF7B61FF),
                                unselectedColor = SettingsSubC
                            )
                        )
                        Text(
                            when (type) {
                                WidgetBackgroundType.WHITE ->
                                    LocalizedStrings.get("widget_bg_white")
                                WidgetBackgroundType.SOLID_COLOR ->
                                    LocalizedStrings.get("widget_bg_solid")
                                WidgetBackgroundType.IMAGE ->
                                    LocalizedStrings.get("widget_bg_image")
                                WidgetBackgroundType.GRADIENT ->
                                    LocalizedStrings.get("widget_bg_gradient")
                            },
                            fontSize = 11.sp,
                            color = if (isSelected) SettingsTitleC else SettingsSubC,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.widthIn(min = 56.dp)
                        )
                    }
                }
            }
            if (backgroundType == WidgetBackgroundType.SOLID_COLOR) {
                Spacer(Modifier.height(16.dp))
                Text(
                    LocalizedStrings.get("widget_preset_colors"),
                    fontSize = 13.sp,
                    color = SettingsTitleC
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    presetColors.forEach { colorHex ->
                        val selected = solidColor.equals(colorHex, ignoreCase = true)
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color(android.graphics.Color.parseColor(colorHex)))
                                .then(
                                    if (selected) {
                                        Modifier.border(2.dp, Color(0xFF7B61FF), CircleShape)
                                    } else {
                                        Modifier
                                    }
                                )
                                .clickable { solidColor = colorHex }
                        )
                    }
                }
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = solidColor,
                    onValueChange = { solidColor = it.trim().uppercase() },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(LocalizedStrings.get("widget_custom_color")) },
                    singleLine = true
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    "${LocalizedStrings.get("widget_alpha")}: $alphaPercent%",
                    fontSize = 13.sp,
                    color = SettingsTitleC
                )
                Slider(
                    value = alphaPercent.toFloat(),
                    onValueChange = {
                        alphaPercent = it.roundToInt().coerceIn(10, 100)
                    },
                    valueRange = 10f..100f
                )
            }
            if (backgroundType == WidgetBackgroundType.IMAGE) {
                Spacer(Modifier.height(16.dp))
                OutlinedButton(
                    onClick = { pickImage.launch(arrayOf("image/*")) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        LocalizedStrings.get("widget_select_image"),
                        color = SettingsTitleC,
                        fontSize = 14.sp
                    )
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    "${LocalizedStrings.get("widget_blur_radius")}: $blurRadius",
                    fontSize = 13.sp,
                    color = SettingsTitleC
                )
                Slider(
                    value = blurRadius.toFloat(),
                    onValueChange = { blurRadius = it.roundToInt().coerceIn(0, 20) },
                    valueRange = 0f..20f,
                    steps = 19
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    "${LocalizedStrings.get("widget_alpha")}: $alphaPercent%",
                    fontSize = 13.sp,
                    color = SettingsTitleC
                )
                Slider(
                    value = alphaPercent.toFloat(),
                    onValueChange = {
                        alphaPercent = it.roundToInt().coerceIn(10, 100)
                    },
                    valueRange = 10f..100f
                )
            }
            if (backgroundType == WidgetBackgroundType.GRADIENT) {
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = gradientStart,
                    onValueChange = { gradientStart = it.trim().uppercase() },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(LocalizedStrings.get("widget_gradient_start")) },
                    singleLine = true
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = gradientEnd,
                    onValueChange = { gradientEnd = it.trim().uppercase() },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(LocalizedStrings.get("widget_gradient_end")) },
                    singleLine = true
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    LocalizedStrings.get("widget_gradient_type"),
                    fontSize = 13.sp,
                    color = SettingsTitleC
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = !isRadialGradient,
                        onClick = { isRadialGradient = false },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF7B61FF),
                            unselectedColor = SettingsSubC
                        )
                    )
                    Text(
                        LocalizedStrings.get("widget_linear"),
                        fontSize = 13.sp,
                        color = SettingsTitleC
                    )
                    RadioButton(
                        selected = isRadialGradient,
                        onClick = { isRadialGradient = true },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF7B61FF),
                            unselectedColor = SettingsSubC
                        )
                    )
                    Text(
                        LocalizedStrings.get("widget_radial"),
                        fontSize = 13.sp,
                        color = SettingsTitleC
                    )
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    "${LocalizedStrings.get("widget_angle")}: ${gradientAngle}°",
                    fontSize = 13.sp,
                    color = SettingsTitleC
                )
                Slider(
                    value = gradientAngle.toFloat(),
                    onValueChange = {
                        gradientAngle = it.roundToInt().coerceIn(0, 360)
                    },
                    valueRange = 0f..360f,
                    steps = 35
                )
            }
            Spacer(Modifier.height(24.dp))
            OutlinedButton(
                onClick = {
                    scope.launch {
                        val bg = mergedBackground()
                        WidgetSize.entries.forEach { size ->
                            val c = repo.getConfig(size)
                            repo.saveConfig(size, c.copy(background = bg))
                        }
                        WidgetManager.refreshWidgets(context.applicationContext)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    LocalizedStrings.get("widget_apply_all_sizes"),
                    color = SettingsTitleC,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(Modifier.height(12.dp))
            OutlinedButton(
                onClick = {
                    scope.launch {
                        val bg = mergedBackground()
                        val c = repo.getConfig(initialSize)
                        repo.saveConfig(initialSize, c.copy(background = bg))
                        WidgetManager.refreshWidgets(context.applicationContext)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    LocalizedStrings.get("widget_apply_current_size"),
                    color = SettingsTitleC,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}
