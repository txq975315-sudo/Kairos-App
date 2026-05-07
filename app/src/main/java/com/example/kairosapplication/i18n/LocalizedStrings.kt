package com.example.kairosapplication.i18n

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import com.example.taskmodel.constants.TaskConstants

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
            "topic_manage_locked_badge" -> if (zh) "默认" else "Default"
            "topic_manage_footer_hint" -> if (zh) {
                "主课题固定；点击卡片仅可管理该课题下的二级分类（最多 8 个）。自由随想不可编辑。"
            } else {
                "Primary topics are fixed. Tap a card to edit secondaries only (max 8). Freestyle is locked."
            }
            "topic_manage_secondary_only_subtitle" -> if (zh) {
                "仅编辑二级课题"
            } else {
                "Secondaries only"
            }
            "topic_primary_meaning_section" -> if (zh) "主课题含义" else "Primary topic"
            "topic_icon_label" -> if (zh) "图标 Emoji" else "Emoji icon"
            "topic_name_label" -> if (zh) "显示名称" else "Display name"
            "topic_summary_guide_label" -> if (zh) "摘要引导（行为摘要 placeholder）" else "Summary placeholder"
            "topic_body_guide_label" -> if (zh) "正文引导（正文 placeholder）" else "Body placeholder"
            "topic_secondary_manage" -> if (zh) "二级课题管理" else "Secondary topics"
            "topic_secondary_add" -> if (zh) "添加" else "Add"
            "topic_secondary_edit" -> if (zh) "编辑二级课题" else "Edit secondary"
            "topic_secondary_name_label" -> if (zh) "名称（必填）" else "Name (required)"
            "topic_advanced_settings" -> if (zh) "高级设置：引导词 ▾" else "Advanced: guide text ▾"
            "topic_secondary_guide_hint" -> if (zh) "可选引导词（显示在分类选择下方）" else "Optional guide under picker"
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
            "view_week_more_tasks" -> if (zh) "还有 {n} 条任务" else "+{n} more tasks"
            "view_week_this_week" -> if (zh) " · 本周" else " · This week"
            "view_month_summary" -> if (zh) "本月概览" else "This month"
            "view_metric_notes" -> if (zh) "笔记" else "Notes"
            "view_metric_tasks" -> if (zh) "任务" else "Tasks"
            "view_metric_done" -> if (zh) "完成率" else "Done"
            "view_metric_streak" -> if (zh) "连续" else "Streak"
            "view_metric_completion_rate" -> if (zh) "完成率" else "Completion"
            "view_metric_streak_record" -> if (zh) "连续记录" else "Active streak"
            "view_metric_streak_all_tasks" -> if (zh) "连续清空任务" else "All tasks done"
            "view_metric_streak_both" -> if (zh) "双模块连续" else "Notes & tasks"
            "view_metric_total_chars" -> if (zh) "日记总字数" else "Diary chars"
            "view_metric_max_chars" -> if (zh) "单篇最高字数" else "Longest note"
            "view_metric_avg_tasks" -> if (zh) "日均任务" else "Avg tasks/day"
            "view_metric_avg_notes" -> if (zh) "日均笔记" else "Avg notes/day"
            "view_month_customize_title" -> if (zh) "自定义本月概览" else "Customize overview"
            "view_month_customize_subtitle" -> if (zh) "勾选要显示的指标（可多选）" else "Choose metrics to show"
            "view_month_customize_done" -> if (zh) "完成" else "Done"
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
            "widget_3x1_more_tasks" -> if (zh) "还有{n}条，在应用内查看" else "+{n} more in app"
            "widget_task_list_empty" -> if (zh) "暂无任务" else "No tasks"
            "widget_checkin_stats" -> if (zh) "连续%d天 总计%d天" else "Streak %d · Total %d days"
            "widget_4b_section" -> if (zh) "本月任务" else "This month"
            "widget_layout_1a" -> if (zh) "计数类" else "Summary ring"
            "widget_layout_1b" -> if (zh) "Todo类" else "Todo list"
            "widget_layout_2a" -> if (zh) "Todo展示类" else "Todo list"
            "widget_layout_3a" -> if (zh) "Todo展示类" else "Todo list"
            "widget_layout_3b" -> if (zh) "统计类（打卡圆环）" else "Check-in stats"
            "widget_layout_3c" -> if (zh) "一周任务条" else "Week strip"
            "widget_layout_3d" -> if (zh) "周任务+每日一句" else "Week tasks + quote"
            "widget_layout_4a" -> if (zh) "展示今日" else "Today + calendar"
            "widget_layout_4b" -> if (zh) "展示全部" else "Full month"
            "user_nickname" -> if (zh) "用户昵称" else "Nickname"
            "mine_settings" -> if (zh) "设置" else "Settings"
            "recorded_days" -> if (zh) "已记录 " else "Recorded "
            "recorded_days_suffix" -> if (zh) " 天" else " days"
            "weekly_insights" -> if (zh) "我的每周洞察" else "My weekly Insights"
            "mine_mood_card_title" -> if (zh) "心情与每日反思" else "Mood and Daily Reflections"
            "mine_planning_heading" -> if (zh) "我坚持规划的每一天" else "I've been planning everyday for"
            "mine_stat_days_in_a_row" -> if (zh) "连续天数" else "DAYS IN A ROW"
            "mine_stat_total_days" -> if (zh) "本周有记录天数" else "TOTAL DAYS"
            "mine_all_records_title" -> if (zh) "我的全部记录" else "All of My Records"
            "mine_records_completion_days" -> if (zh) "完成日天数" else "Completion days"
            "mine_records_completed_items" -> if (zh) "已完成项" else "Completed items"
            "mine_records_unfinished_today" -> if (zh) "今日未完成" else "Unfinished today"
            "mine_metric_max_streak_record" -> if (zh) "最高连续有记录" else "Longest record streak"
            "mine_checkin_title" -> if (zh) "打卡统计" else "Check-in stats"
            "mine_checkin_week" -> if (zh) "周" else "Week"
            "mine_checkin_month" -> if (zh) "月" else "Month"
            "mine_checkin_legend" -> if (zh) {
                "— 当日无待办；🌱 有待办但未完成；📌·💪·👏 部分完成（低→高）；🎉 当日待办全部完成。"
            } else {
                "— No todos that day; 🌱 Todos exist but none done; 📌 💪 👏 Partial progress (low→high); 🎉 All todos done."
            }
            "mine_records_customize_title" -> if (zh) "自定义统计与预览" else "Customize stats"
            "mine_records_scope_heading" -> if (zh) "统计范围" else "Scope"
            "mine_records_scope_year" -> if (zh) "本年度" else "This year"
            "mine_records_scope_all" -> if (zh) "自首次记录以来" else "Since first record"
            "mine_records_year_heading" -> if (zh) "年份" else "Year"
            "mine_records_metrics_heading" -> if (zh) "显示的指标（可多选）" else "Metrics to show"
            "mine_records_preview_heading" -> if (zh) "卡片预览" else "Card preview"
            "settings_export_csv_json" -> if (zh) "数据导出（CSV / JSON）" else "Data Export (CSV / JSON)"
            "settings_stat_analysis" -> if (zh) "数据统计分析" else "Statistic Analysis of Data"
            "settings_privacy_management" -> if (zh) "隐私管理" else "Privacy Management"
            "settings_problem_feedback" -> if (zh) "问题反馈" else "Problem Feedback"
            "settings_sign_out" -> if (zh) "退出登录" else "Sign out"
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
            "nav_todo" -> if (zh) "待办" else "Todo"
            "nav_essay" -> if (zh) "随笔" else "Essay"
            "nav_view" -> if (zh) "视图" else "View"
            "nav_widget" -> if (zh) "小组件" else "Widget"
            "nav_mine" -> if (zh) "我的" else "Mine"
            "essay_tab_timeline" -> if (zh) "时间轴" else "Timeline"
            "essay_tab_topic" -> if (zh) "主题" else "Topic"
            "essay_tab_project" -> if (zh) "项目" else "Project"
            "todo_hint_anytime" -> if (zh) "随时都可以安排" else "Anytime today works"
            "todo_hint_morning" -> if (zh) "上午安排在这里" else "Morning today works"
            "todo_hint_afternoon" -> if (zh) "下午安排在这里" else "Afternoon today works"
            "todo_hint_evening" -> if (zh) "晚间安排在这里" else "Evening today works"
            "todo_hint_default" -> if (zh) "添加任务" else "Add a task"
            "task_urgency_0" -> if (zh) "紧急" else "Urgent"
            "task_urgency_1" -> if (zh) "高" else "High"
            "task_urgency_2" -> if (zh) "普通" else "Normal"
            "task_urgency_3" -> if (zh) "低优先级" else "Low priority"
            "essay_topic_empty_notes" -> if (zh) "该分类下暂无笔记" else "No notes in this category"
            "note_uncategorized" -> if (zh) "未分类" else "Uncategorized"
            "essay_timeline_empty_title" -> if (zh) "还没有笔记" else "No notes yet"
            "essay_timeline_empty_subtitle" -> if (zh) "写第一条记录" else "Start your first entry"
            "essay_topic_empty_title" -> if (zh) "还没有主题笔记" else "No topic notes yet"
            "essay_topic_empty_subtitle" -> if (zh) "写第一条主题记录" else "Start your first topic entry"
            "essay_primary_freestyle" -> if (zh) "自由随想" else "Free Recall"
            "essay_primary_self_awareness" -> if (zh) "自我认知" else "Self Schema"
            "essay_primary_interpersonal" -> if (zh) "社会人际" else "Social Bond"
            "essay_primary_intimacy_family" -> if (zh) "亲密关系" else "Intimate Tie"
            "essay_primary_somatic_energy" -> if (zh) "身心能量" else "Somatic Energy"
            "essay_primary_meaning" -> if (zh) "存在探索" else "Existential Quest"
            "essay_sec_Emotional_awareness" -> if (zh) "情感觉察" else "Emotional awareness"
            "essay_sec_Behavioral_habits" -> if (zh) "行为习惯" else "Behavioral habits"
            "essay_sec_Thinking_blind_spots" -> if (zh) "思维盲区" else "Thinking blind spots"
            "essay_sec_Value_exploration" -> if (zh) "价值探索" else "Value exploration"
            "essay_sec_Workplace_dynamics" -> if (zh) "职场动态" else "Workplace dynamics"
            "essay_sec_Friendship_issues" -> if (zh) "友谊议题" else "Friendship issues"
            "essay_sec_Stranger_encounters" -> if (zh) "陌生人互动" else "Stranger encounters"
            "essay_sec_Social_anxiety" -> if (zh) "社交焦虑" else "Social anxiety"
            "essay_sec_Partner_conflicts" -> if (zh) "伴侣冲突" else "Partner conflicts"
            "essay_sec_Parenting_struggles" -> if (zh) "育儿困扰" else "Parenting struggles"
            "essay_sec_Family_boundaries" -> if (zh) "家庭边界" else "Family boundaries"
            "essay_sec_Love_language" -> if (zh) "爱的语言" else "Love language"
            "essay_sec_Energy_levels" -> if (zh) "精力水平" else "Energy levels"
            "essay_sec_Sleep_quality" -> if (zh) "睡眠质量" else "Sleep quality"
            "essay_sec_Physical_pain" -> if (zh) "身体不适" else "Physical pain"
            "essay_sec_Exercise_habits" -> if (zh) "运动习惯" else "Exercise habits"
            "essay_sec_Career_direction" -> if (zh) "职业方向" else "Career direction"
            "essay_sec_Life_goals" -> if (zh) "人生目标" else "Life goals"
            "essay_sec_Meaning_questions" -> if (zh) "意义之问" else "Meaning questions"
            "essay_sec_Self-actualization" -> if (zh) "自我实现" else "Self-actualization"
            else -> key
        }
    }

    fun timeBlockLabelFor(blockKey: String, language: LocalizationManager.Language): String {
        val key = when (blockKey) {
            TaskConstants.TIME_BLOCK_ANYTIME -> "view_time_anytime"
            TaskConstants.TIME_BLOCK_MORNING -> "view_time_morning"
            TaskConstants.TIME_BLOCK_AFTERNOON -> "view_time_afternoon"
            TaskConstants.TIME_BLOCK_EVENING -> "view_time_evening"
            else -> return blockKey
        }
        return stringFor(language, key)
    }

    fun timeBlockEmptyHintFor(blockKey: String, language: LocalizationManager.Language): String {
        val key = when (blockKey) {
            TaskConstants.TIME_BLOCK_ANYTIME -> "todo_hint_anytime"
            TaskConstants.TIME_BLOCK_MORNING -> "todo_hint_morning"
            TaskConstants.TIME_BLOCK_AFTERNOON -> "todo_hint_afternoon"
            TaskConstants.TIME_BLOCK_EVENING -> "todo_hint_evening"
            else -> "todo_hint_default"
        }
        return stringFor(language, key)
    }

    @Composable
    fun get(key: String): String = stringFor(LocalCurrentLanguage.current.value, key)

    @Composable
    fun timeBlockLabel(blockKey: String): String =
        timeBlockLabelFor(blockKey, LocalCurrentLanguage.current.value)

    @Composable
    fun timeBlockEmptyHint(blockKey: String): String =
        timeBlockEmptyHintFor(blockKey, LocalCurrentLanguage.current.value)

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
