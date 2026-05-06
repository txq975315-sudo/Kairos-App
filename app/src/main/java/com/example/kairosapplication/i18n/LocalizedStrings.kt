package com.example.kairosapplication.i18n

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

val LocalCurrentLanguage =
    compositionLocalOf<State<LocalizationManager.Language>> { mutableStateOf(LocalizationManager.Language.ZH) }

object LocalizedStrings {

    fun stringFor(language: LocalizationManager.Language, key: String): String {
        val zh = language == LocalizationManager.Language.ZH
        return when (key) {
            "settings" -> if (zh) "设置" else "Settings"
            "notification_settings" -> if (zh) "通知设置" else "Notification"
            "theme_settings" -> if (zh) "主题设置" else "Theme"
            "mood_settings" -> if (zh) "心情与反思" else "Mood & Reflection"
            "widget_settings" -> if (zh) "小组件设置" else "Widget Settings"
            "language_settings" -> if (zh) "语言设置" else "Language"
            "privacy_settings" -> if (zh) "隐私设置" else "Privacy"
            "other_settings" -> if (zh) "其他" else "Other"
            "experimental" -> if (zh) "实验功能" else "Experimental"
            "daily_reminder" -> if (zh) "每日提醒" else "Daily Reminder"
            "daily_reminder_time" -> if (zh) "提醒时间" else "Reminder Time"
            "daily_reflection" -> if (zh) "每日反思提醒" else "Daily Reflection"
            "reflection_time" -> if (zh) "反思时间" else "Reflection Time"
            "dark_mode" -> if (zh) "深色模式" else "Dark Mode"
            "system_default" -> if (zh) "系统默认" else "System"
            "light_mode" -> if (zh) "浅色模式" else "Light"
            "dark_mode_option" -> if (zh) "深色模式" else "Dark"
            "theme_color" -> if (zh) "主题色彩" else "Theme Color"
            "vitality_blue" -> if (zh) "活力蓝" else "Blue"
            "forest_green" -> if (zh) "森林绿" else "Green"
            "soft_pink" -> if (zh) "柔粉" else "Pink"
            "warm_orange" -> if (zh) "暖橙" else "Orange"
            "current_language" -> if (zh) "当前语言" else "Current Language"
            "language_hint" -> if (zh) "语言切换会立即生效" else "Language applies immediately"
            "language" -> if (zh) "语言" else "Language"
            "privacy_policy" -> if (zh) "隐私政策" else "Privacy Policy"
            "delete_account" -> if (zh) "注销账户" else "Delete Account"
            "delete_account_warning" -> if (zh) {
                "此操作不可恢复，所有数据将被永久删除。确定要注销吗？"
            } else {
                "This action is irreversible. All data will be permanently deleted. Are you sure?"
            }
            "cancel" -> if (zh) "取消" else "Cancel"
            "confirm_delete" -> if (zh) "注销" else "Delete"
            "clear_cache" -> if (zh) "清除缓存" else "Clear Cache"
            "cache_size" -> if (zh) "当前缓存" else "Cache"
            "cache_cleared" -> if (zh) "缓存已清除" else "Cache cleared"
            "feedback" -> if (zh) "意见反馈" else "Feedback"
            "share_app" -> if (zh) "分享应用" else "Share App"
            "about" -> if (zh) "关于" else "About"
            "back" -> if (zh) "返回" else "Back"
            "confirm" -> if (zh) "确定" else "Confirm"
            "feature_developing" -> if (zh) "功能开发中" else "Feature under development"
            "coming_soon" -> if (zh) "敬请期待" else "Coming soon"
            "reminders_notifications" -> if (zh) "提醒与通知" else "Reminders"
            "appearance" -> if (zh) "外观" else "Appearance"
            "mood_records" -> if (zh) "心情与记录" else "Mood & records"
            "mood_vocab" -> if (zh) "心情词库" else "Mood vocabulary"
            "topic_manage" -> if (zh) "课题管理" else "Topics"
            "widget_nav" -> if (zh) "Widget 设置" else "Widget"
            "tools" -> if (zh) "工具" else "Tools"
            "data_export" -> if (zh) "数据导出" else "Export data"
            "data_import" -> if (zh) "数据导入" else "Import data"
            "backup_status" -> if (zh) "备份状态" else "Backup"
            "language_nav" -> if (zh) "语言" else "Language"
            "annual_review" -> if (zh) "年度回顾" else "Year in review"
            "data_analysis" -> if (zh) "数据分析" else "Analytics"
            "soon" -> if (zh) "即将推出" else "Coming soon"
            "view_privacy_policy" -> if (zh) "查看隐私政策" else "View privacy policy"
            "delete_irreversible_short" -> if (zh) "⚠️此操作不可恢复" else "⚠️Irreversible"
            "close" -> if (zh) "关闭" else "Close"
            "privacy_policy_body" -> if (zh) {
                "欢迎使用 Kairos。我们重视您的隐私。本应用当前在本地处理您的笔记、任务与心情数据。详细政策将在后续版本完善。"
            } else {
                "Welcome to Kairos. We value your privacy. Notes, tasks, and mood data are processed on device. Full policy will follow in a later release."
            }
            "no_mail_app" -> if (zh) "未找到邮件应用" else "No email app found"
            "version_prefix" -> if (zh) "版本 " else "Version "
            "recommend_kairos" -> if (zh) "推荐应用 Kairos：" else "Try Kairos: "
            "dev_in_widget" -> if (zh) "此功能正在开发中" else "Under development"
            "widget_quote_default" -> if (zh) "纵有疾风起，人生不言弃" else "The wind rises; I keep going."
            "widget_quote_prefix" -> if (zh) "今日一句：" else ""
            "widget_main_tab_small" -> if (zh) "小" else "Small"
            "widget_main_tab_medium" -> if (zh) "中" else "Medium"
            "widget_main_tab_large" -> if (zh) "大" else "Large"
            "widget_main_tab_super" -> if (zh) "超大" else "Super large"
            "widget_main_no_tasks" -> if (zh) "暂无今日任务" else "No tasks today"
            "widget_main_week_row" -> if (zh) "一 二 三 四 五 六 日" else "M T W T F S S"
            "widget_main_today_tasks" -> if (zh) "今日任务" else "Today"
            "view_tab_today" -> if (zh) "今日" else "Today"
            "view_tab_week" -> if (zh) "周" else "Week"
            "view_tab_month" -> if (zh) "月" else "Month"
            "view_todo_prefix" -> if (zh) "待办" else "Todo"
            "view_empty_tasks_today" -> if (zh) "今天还没有记录" else "Nothing logged today"
            "view_empty_tasks_other" -> if (zh) "暂无任务" else "No tasks for this day"
            "view_see_all_more" -> if (zh) "查看全部（还有{n}条）→" else "See all ({n} more) →"
            "view_see_all" -> if (zh) "查看全部 →" else "See all →"
            "view_notes_section" -> if (zh) "课题笔记" else "Topic notes"
            "view_empty_notes" -> if (zh) "（暂无笔记）" else "(No notes yet)"
            "view_time_anytime" -> if (zh) "随时" else "Anytime"
            "view_time_morning" -> if (zh) "上午" else "Morning"
            "view_time_afternoon" -> if (zh) "下午" else "Afternoon"
            "view_time_evening" -> if (zh) "晚间" else "Evening"
            "view_week_no_tasks" -> if (zh) "暂无任务" else "No tasks"
            "view_week_no_notes" -> if (zh) "暂无笔记" else "No notes"
            "view_week_add_task" -> if (zh) "+ 添加任务" else "+ Add task"
            "view_week_this_week" -> if (zh) " · 本周" else " · This week"
            "view_month_summary" -> if (zh) "本月概览" else "This month"
            "view_metric_notes" -> if (zh) "笔记" else "Notes"
            "view_metric_tasks" -> if (zh) "任务" else "Tasks"
            "view_metric_done" -> if (zh) "完成率" else "Done"
            "view_metric_streak" -> if (zh) "连续" else "Streak"
            "view_streak_one" -> if (zh) "1 天" else "1 day"
            "view_streak_many" -> if (zh) "{n} 天" else "{n} days"
            "widget_layout_section" -> if (zh) "布局选择" else "Layout"
            "widget_display_section" -> if (zh) "显示内容" else "Show"
            "widget_show_tasks" -> if (zh) "今日任务" else "Today tasks"
            "widget_show_daily_quote" -> if (zh) "每日一句" else "Daily quote"
            "widget_background_style" -> if (zh) "背景样式" else "Background"
            "widget_refresh_time" -> if (zh) "刷新时间" else "Refresh"
            "widget_refresh_on_app_open" -> if (zh) "每次打开app更新一次" else "When app opens"
            "widget_refresh_hourly" -> if (zh) "每小时更新一次" else "Every hour"
            "widget_refresh_daily" -> if (zh) "每天更新一次" else "Once a day"
            "widget_reset_default" -> if (zh) "恢复默认设置" else "Restore defaults"
            "widget_background_soon" -> if (zh) "背景自定义即将推出" else "Background customization coming soon"
            "widget_preview_area" -> if (zh) "预览区域" else "Preview Area"
            "widget_preview_placeholder" -> if (zh) "Widget 实时预览" else "Widget Live Preview"
            "widget_background_type" -> if (zh) "背景类型" else "Background Type"
            "widget_bg_white" -> if (zh) "纯白" else "White"
            "widget_bg_solid" -> if (zh) "纯色" else "Solid"
            "widget_bg_image" -> if (zh) "图片" else "Image"
            "widget_bg_gradient" -> if (zh) "渐变" else "Gradient"
            "widget_preset_colors" -> if (zh) "预设色板：" else "Preset Colors:"
            "widget_custom_color" -> if (zh) "自定义色值" else "Custom Color"
            "widget_gradient_start" -> if (zh) "起始色" else "Start Color"
            "widget_gradient_end" -> if (zh) "结束色" else "End Color"
            "widget_select_image" -> if (zh) "点击选择文件" else "Select Image"
            "widget_blur_radius" -> if (zh) "模糊程度" else "Blur Radius"
            "widget_alpha" -> if (zh) "透明度" else "Opacity"
            "widget_gradient_type" -> if (zh) "渐变类型" else "Gradient Type"
            "widget_linear" -> if (zh) "线性渐变" else "Linear"
            "widget_radial" -> if (zh) "径向渐变" else "Radial"
            "widget_angle" -> if (zh) "角度" else "Angle"
            "widget_apply_all_sizes" -> if (zh) "应用到所有尺寸" else "Apply to All Sizes"
            "widget_apply_current_size" -> if (zh) "仅应用当前尺寸" else "Apply to Current Size Only"
            "widget_2x2_stats" -> if (zh) "已完成%d/总%d" else "Done %d / %d total"
            "widget_task_list_empty" -> if (zh) "暂无任务" else "No tasks"
            "widget_checkin_stats" -> if (zh) "连续%d天 总计%d天" else "Streak %d · Total %d days"
            "widget_4b_section" -> if (zh) "本月任务" else "This month"
            "widget_layout_1a" -> if (zh) "计数类" else "Summary ring"
            "widget_layout_1b" -> if (zh) "Todo类" else "Todo list"
            "widget_layout_2a" -> if (zh) "Todo展示类" else "Todo list"
            "widget_layout_3a" -> if (zh) "Todo展示类" else "Todo list"
            "widget_layout_3b" -> if (zh) "统计类（打卡圆环）" else "Check-in stats"
            "widget_layout_3c" -> if (zh) "一周任务条" else "Week strip"
            "widget_layout_4a" -> if (zh) "展示今日" else "Today + calendar"
            "widget_layout_4b" -> if (zh) "展示全部" else "Full month"
            "user_nickname" -> if (zh) "用户昵称" else "Nickname"
            "mine_settings" -> if (zh) "设置" else "Settings"
            "recorded_days" -> if (zh) "已记录 " else "Recorded "
            "recorded_days_suffix" -> if (zh) " 天" else " days"
            "weekly_insights" -> if (zh) "本周洞察" else "This week"
            "insights_off_today" -> if (zh) "今日洞察已关闭" else "Insights off today"
            "consecutive_days" -> if (zh) "连续记录  " else "Streak "
            "consecutive_days_suffix" -> if (zh) " 天" else " days"
            "week_recorded_days" -> if (zh) "本周有记录  " else "Days with record "
            "week_recorded_days_suffix" -> if (zh) " 天" else " days"
            "my_mood" -> if (zh) "我的心情" else "My mood"
            "today_mood" -> if (zh) "今天心情" else "Today"
            "this_week" -> if (zh) "本周" else "This week"
            "view_history" -> if (zh) "查看历史 →" else "History →"
            "all_records" -> if (zh) "全部记录" else "All records"
            "completed" -> if (zh) "已完成" else "Done"
            "uncompleted" -> if (zh) "未完成" else "Pending"
            "today_completed" -> if (zh) "今日完成" else "Today done"
            "edit_profile" -> if (zh) "编辑个人资料" else "Edit profile"
            "save" -> if (zh) "保存" else "Save"
            "mood_record" -> if (zh) "心情记录" else "Mood log"
            "mood_times" -> if (zh) "次" else " times"
            "share" -> if (zh) "分享" else "Share"
            "mood_distribution_month" -> if (zh) "月心情分布" else " mood mix"
            "export_success" -> if (zh) "导出成功" else "Export succeeded"
            "export_first" -> if (zh) "请先导出文件" else "Export a file first"
            "export_content" -> if (zh) "导出内容" else "Content"
            "export_format" -> if (zh) "导出格式" else "Format"
            "export_json_desc" -> if (zh) "JSON（推荐，用于再次导入）" else "JSON (recommended, re-importable)"
            "export_txt_desc" -> if (zh) "TXT（纯文本，仅笔记内容）" else "TXT (plain text, notes only)"
            "export_to_file" -> if (zh) "导出到文件" else "Export to file"
            "include_profile" -> if (zh) "个人设置" else "Profile"
            "parse_failed" -> if (zh) "文件解析失败" else "Parse failed"
            "cannot_read_file" -> if (zh) "无法读取文件" else "Cannot read file"
            "select_file_import" -> if (zh) "选择文件导入" else "Choose file"
            "support_json" -> if (zh) "支持 .json 格式文件" else ".json files supported"
            "file_info" -> if (zh) "文件信息" else "File info"
            "export_date_label" -> if (zh) "导出日期: " else "Export date: "
            "app_version_label" -> if (zh) "App版本: " else "App version: "
            "notes_count_label" -> if (zh) "笔记: " else "Notes: "
            "notes_count_suffix" -> if (zh) " 条" else ""
            "tasks_count_label" -> if (zh) "任务: " else "Tasks: "
            "tasks_count_suffix" -> if (zh) " 条" else ""
            "moods_count_label" -> if (zh) "心情: " else "Moods: "
            "moods_count_suffix" -> if (zh) " 条" else ""
            "import_mode_label" -> if (zh) "导入模式:" else "Import mode:"
            "merge_mode_desc" -> if (zh) "合并（推荐，保留现有数据）" else "Merge (keep existing)"
            "overwrite_mode_desc" -> if (zh) "覆盖（删除现有数据后导入） ⚠️不可恢复" else "Overwrite ⚠️ irreversible"
            "start_import" -> if (zh) "开始导入" else "Import"
            "overwrite_dialog_title" -> if (zh) "⚠️不可恢复" else "⚠️ Irreversible"
            "overwrite_dialog_body" -> if (zh) {
                "覆盖将删除现有笔记、任务与心情记录后再导入。确定继续？"
            } else {
                "Overwrite deletes existing notes, tasks, and moods before import. Continue?"
            }
            "confirm_overwrite" -> if (zh) "确定覆盖" else "Overwrite"
            "emoji_smiley_happy" -> if (zh) "开心" else "Happy"
            "emoji_flower_cool" -> if (zh) "得意" else "Proud"
            "emoji_meaning" -> if (zh) "思考" else "Thinking"
            "emoji_calm" -> if (zh) "平静" else "Calm"
            "emoji_ignorant" -> if (zh) "困惑" else "Confused"
            "emoji_sad_tear" -> if (zh) "难过" else "Sad"
            "emoji_annoying" -> if (zh) "担心" else "Worried"
            "emoji_sick" -> if (zh) "难受" else "Unwell"
            "emoji_mood_bubble" -> if (zh) "茫然" else "Blank"
            else -> key
        }
    }

    @Composable
    fun get(key: String): String = stringFor(LocalCurrentLanguage.current.value, key)

    @Composable
    fun emojiLabel(id: String): String = when (id) {
        "smiley_happy" -> get("emoji_smiley_happy")
        "flower_cool" -> get("emoji_flower_cool")
        "meaning" -> get("emoji_meaning")
        "calm" -> get("emoji_calm")
        "ignorant" -> get("emoji_ignorant")
        "sad_tear" -> get("emoji_sad_tear")
        "annoying" -> get("emoji_annoying")
        "sick" -> get("emoji_sick")
        "mood_bubble" -> get("emoji_mood_bubble")
        else -> id
    }

    @Composable
    fun monthMoodDistributionTitle(monthValue: Int): String {
        val zh = LocalCurrentLanguage.current.value == LocalizationManager.Language.ZH
        return if (zh) "${monthValue}月心情分布" else "Mood mix · $monthValue"
    }

    @Composable
    fun exportNotesLabel(count: Int): String {
        val zh = LocalCurrentLanguage.current.value == LocalizationManager.Language.ZH
        return if (zh) "笔记（${count} 条）" else "Notes ($count)"
    }

    @Composable
    fun exportTasksLabel(count: Int): String {
        val zh = LocalCurrentLanguage.current.value == LocalizationManager.Language.ZH
        return if (zh) "任务（${count} 条）" else "Tasks ($count)"
    }

    @Composable
    fun exportMoodsLabel(count: Int): String {
        val zh = LocalCurrentLanguage.current.value == LocalizationManager.Language.ZH
        return if (zh) "心情记录（${count} 条）" else "Moods ($count)"
    }

    fun importSuccessMessageFor(
        language: LocalizationManager.Language,
        notes: Int,
        tasks: Int,
        moods: Int,
        conflicts: Int
    ): String {
        val zh = language == LocalizationManager.Language.ZH
        return if (zh) {
            "导入完成：笔记+$notes 任务+$tasks 心情+$moods 冲突$conflicts"
        } else {
            "Import done: +$notes notes, +$tasks tasks, +$moods moods, $conflicts conflicts"
        }
    }

    fun moodShareSummaryFor(
        language: LocalizationManager.Language,
        prefix: String,
        lines: String
    ): String {
        val zh = language == LocalizationManager.Language.ZH
        return if (zh) "${prefix}心情记录：$lines" else "$prefix mood: $lines"
    }

    fun moodStatTimesFor(language: LocalizationManager.Language): String =
        stringFor(language, "mood_times")
}
