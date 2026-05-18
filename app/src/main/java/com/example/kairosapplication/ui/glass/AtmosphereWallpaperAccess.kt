package com.example.kairosapplication.ui.glass

import android.content.Context
import android.content.Intent
import android.net.Uri

/** Read / persist access for user-picked atmosphere wallpaper URIs. */
object AtmosphereWallpaperAccess {

    fun canRead(context: Context, uriString: String): Boolean {
        return try {
            context.contentResolver.openInputStream(Uri.parse(uriString))?.use { true } ?: false
        } catch (_: SecurityException) {
            false
        } catch (_: Exception) {
            false
        }
    }

    fun persistReadPermission(context: Context, uri: Uri) {
        runCatching {
            context.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION,
            )
        }
    }
}
