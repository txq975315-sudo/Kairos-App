import { Callout, Divider, H1, H2, Stack, Table, Text, Grid, Stat } from "cursor/canvas";

export default function MineNotificationsI18nHandoff() {
  return (
    <Stack gap={20}>
      <H1>Mine 模块：通知推送与国际化（对接备忘）</H1>
      <Text tone="secondary" size="small">
        本文档汇总本轮在 KairosApplication 中已落地的「我的」相关能力、每日提醒/反思通知链路、以及应用内语言实时切换的实现要点与文件索引，便于后续联调与扩展。
      </Text>

      <Grid columns={3} gap={12}>
        <Stat value="AlarmManager" label="setExactAndAllowWhileIdle" />
        <Stat value="2 Channels" label="提醒 / 反思独立渠道" />
        <Stat value="ZH / EN" label="CompositionLocal 实时文案" />
      </Grid>

      <Callout tone="info">
        约束回顾：未使用 Hilt；未新增 Maven 依赖；通知点击统一进入 MainActivity；Android 13+
        在开启通知开关时请求 POST_NOTIFICATIONS；BootReceiver 在开机后按 DataStore 开关恢复闹钟。
      </Callout>

      <Divider />

      <H2>一、功能范围</H2>
      <Text>
        Mine：个人资料与头像表情、心情卡片与周视图、心情日历、全部记录与本周洞察、设置入口。
      </Text>
      <Text>
        设置：通知（每日提醒与每日反思时间）、主题、语言、心情与反思、Widget、数据导出/导入、隐私、其他；导出/导入走 FileProvider。
      </Text>
      <Text>
        通知：NotificationHelper 建双渠道并展示两类通知；AlarmManager 按用户选择的小时/分钟调度；NotificationReceiver
        触发后从 DataStore 读开关与时间并顺延至次日；BootReceiver 在 BOOT_COMPLETED 后重建或取消闹钟。
      </Text>
      <Text>
        国际化：LocalizationManager 与 DataStore 语言键同步；LocalizedStrings 提供 get（Composable）与
        stringFor（非 UI 线程/Toast）；MainScreen 顶层 CompositionLocalProvider(LocalCurrentLanguage)；切换语言后立即重组。
      </Text>

      <Divider />

      <H2>二、关键文件索引</H2>
      <Table
        headers={["路径", "职责"]}
        rows={[
          ["notification/NotificationHelper.kt", "渠道、展示、PendingIntent、精确闹钟调度与取消"],
          ["notification/NotificationReceiver.kt", "广播接收：展示通知并从 DataStore 再调度"],
          ["notification/BootReceiver.kt", "开机后读 DataStore 恢复或取消两类闹钟"],
          ["AndroidManifest.xml", "权限与两个 Receiver 注册；BootReceiver exported=true"],
          ["MainActivity.kt", "应用启动时 createNotificationChannels"],
          ["MainScreen.kt", "LocalizationManager、NotificationHelper、LocalCurrentLanguage、Mine 子页 hideBottomBar"],
          ["i18n/LocalizationManager.kt", "StateFlow 语言状态与 loadLanguage / setLanguage"],
          ["i18n/LocalizedStrings.kt", "文案表、stringFor、emojiLabel、导入成功等辅助方法"],
          ["data/DataStoreManager.kt", "Mine 偏好、语言、提醒/反思开关与时间；getLanguageSync 等供 Boot/Receiver"],
          ["ui/mine/settings/NotificationSettingsScreen.kt", "开关、时间、权限与 LaunchedEffect 调度"],
          ["ui/mine/settings/MoodSettingsScreen.kt", "反思开关/时间、权限与调度"],
          ["ui/mine/settings/LanguageSettingsScreen.kt", "仅依赖 LocalizationManager 切换语言"],
          ["ui/mine/settings/SettingsScreen.kt", "设置总览文案与 key(lang) 触发重组"],
          ["app/build.gradle.kts", "preBuild 将 drawable-sw512 重命名为 drawable-sw512dp（资源限定符合法化）"],
        ]}
      />

      <Divider />

      <H2>三、对接与验证建议</H2>
      <Text>
        编译：在项目根执行 gradlew :app:compileDebugKotlin。
      </Text>
      <Text>
        通知真机验证：开启开关后将时间设为约 1 分钟后，确认通知到达且点击回到主页；重启设备后确认闹钟仍按开关生效。
      </Text>
      <Text>
        语言验证：设置中切换 English / 简体中文，Mine 与设置相关文案应立即变化；Toast 与分享等非 Composable 场景使用 stringFor 与当前
        CompositionLocal 捕获的语言变量保持一致。
      </Text>
      <Text tone="secondary" size="small">
        仓库内另有 canvases/current-development-workflow.canvas.tsx 记录 Today/Task 等历史迭代；本页专注 Mine + 通知 + i18n。
      </Text>
    </Stack>
  );
}
