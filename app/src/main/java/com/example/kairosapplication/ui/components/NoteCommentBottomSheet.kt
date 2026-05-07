package com.example.kairosapplication.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kairosapplication.core.ui.AppColors
import com.example.kairosapplication.i18n.LocalizedStrings
import com.example.kairosapplication.i18n.LocalizationManager
import com.example.taskmodel.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** Bottom sheet for adding a short self-review line to a published note (matches quick-task sheet shape). */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCommentBottomSheet(
    note: Note,
    onDismiss: () -> Unit,
    onAppendComment: (Note, String) -> Unit,
) {
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val focusRequester = remember { FocusRequester() }
    var text by remember(note.id) { mutableStateOf("") }
    val commentToastEmpty = LocalizedStrings.get("essay_comment_toast_empty")

    val sheetBg = Color(0xFFE8E4F5)
    val titleColor = Color(0xFF4A3F6B)

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboard?.show()
    }

    ModalBottomSheet(
        onDismissRequest = {
            keyboard?.hide()
            focusManager.clearFocus(force = true)
            onDismiss()
        },
        sheetState = sheetState,
        containerColor = sheetBg,
        contentColor = Color.Black,
        dragHandle = null,
        shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .imePadding()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = LocalizedStrings.get("essay_comment_sheet_title"),
                modifier = Modifier.fillMaxWidth(),
                color = titleColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = AppColors.PrimaryText,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default),
                keyboardActions = KeyboardActions(),
                decorationBox = { inner ->
                    if (text.isEmpty()) {
                        Text(
                            text = LocalizedStrings.get("essay_comment_placeholder"),
                            fontSize = 18.sp,
                            color = AppColors.SecondaryText.copy(alpha = 0.65f)
                        )
                    }
                    inner()
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val t = text.trim()
                    if (t.isEmpty()) {
                        Toast.makeText(context, commentToastEmpty, Toast.LENGTH_SHORT).show()
                    } else {
                        onAppendComment(note, t)
                        keyboard?.hide()
                        focusManager.clearFocus(force = true)
                        onDismiss()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(LocalizedStrings.get("save"))
            }
            TextButton(
                onClick = {
                    keyboard?.hide()
                    focusManager.clearFocus(force = true)
                    onDismiss()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(LocalizedStrings.get("cancel"), color = AppColors.SecondaryText)
            }
        }
    }
}

fun appendReviewCommentToNote(
    note: Note,
    comment: String,
    language: LocalizationManager.Language,
    context: Context,
): Note {
    val stamp = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())
    val sep = if (note.body.isBlank()) "" else "\n\n"
    val block = LocalizedStrings.stringFor(
        language,
        "essay_comment_review_block",
        context,
        stamp,
        comment.trim(),
    )
    val newBody = "${note.body}$sep$block"
    return note.copy(body = newBody, updatedAt = System.currentTimeMillis())
}
