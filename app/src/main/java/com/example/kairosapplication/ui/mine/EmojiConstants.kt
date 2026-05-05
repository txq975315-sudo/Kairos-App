package com.example.kairosapplication.ui.mine

import com.example.kairosapplication.R

object EmojiConstants {
    val CUSTOM_EMOJIS = listOf(
        EmojiItem("smiley_happy", "开心", R.drawable.emoji_smiley_happy),
        EmojiItem("flower_cool", "得意", R.drawable.emoji_flower_cool),
        EmojiItem("meaning", "思考", R.drawable.emoji_meaning),
        EmojiItem("calm", "平静", R.drawable.emoji_calm),
        EmojiItem("ignorant", "困惑", R.drawable.emoji_ignorant),
        EmojiItem("sad_tear", "难过", R.drawable.emoji_sad_tear),
        EmojiItem("annoying", "担心", R.drawable.emoji_annoying),
        EmojiItem("sick", "难受", R.drawable.emoji_sick),
        EmojiItem("mood_bubble", "茫然", R.drawable.emoji_mood_bubble)
    )

    fun emojiById(id: String): EmojiItem? = CUSTOM_EMOJIS.firstOrNull { it.id == id }

    data class EmojiItem(
        val id: String,
        val label: String,
        val drawableResId: Int
    )
}
