import {
  Callout,
  Code,
  Divider,
  Grid,
  H1,
  H2,
  H3,
  Stack,
  Stat,
  Table,
  Text,
} from "cursor/canvas";

export default function UnifiedGlassUiMergeHandoff() {
  return (
    <Stack gap={20}>
      <H1>Kairos 统一玻璃 UI 合并 — 开发过程与对接说明</H1>
      <Text tone="secondary" size="small">
        记录 2026-05-17 会话：将「经典绿景 UI」与「Glass / 睡莲实验 UI」合并为单一产品界面；移除设置开关；修复 Gradle 构建。便于后续接手、Code Review 与继续迭代。
      </Text>

      <Grid columns={4} gap={12}>
        <Stat value="单一 UI" label="已移除 Glass 开关" />
        <Stat value="Today" label="A 功能 + B 外观" />
        <Stat value="GlassConstants" label="设计 token 收敛" />
        <Stat value="assembleDebug" label="本地需验证编译" />
      </Grid>

      <Callout tone="info">
        产品决策摘要：全应用使用绿色氛围图（用户指定 528474ldsdl.jpg →
        drawable/kairos_atmosphere_bg）+ 背景模糊与压暗；底栏与任务卡采用玻璃形态（白字、50dp/80dp
        模糊层级）；Today 保留完整交互（左滑删除、长按跨时段拖、紧急度色圈、编辑 BottomSheet）；其他 Tab 顶栏级文案改为白色系。
      </Callout>

      <Divider />

      <H2>一、需求来源与时间线</H2>
      <Table
        headers={["阶段", "内容", "结果"]}
        rows={[
          [
            "1. 项目体检",
            "检查结构、修复 Gradle、阅读 canvas 进度",
            "settings.gradle.kts 增加 AGP/KSP resolutionStrategy；阿里云镜像顺序；清理 transforms 缓存；修复 Sparkle 图标 → AutoAwesome",
          ],
          [
            "2. Glass 开关",
            "设置页 Glass UI (Beta) + MainScreen 分支",
            "Today 用 GlassTodayContent；其他 Tab 仍经典 → 视觉割裂",
          ],
          [
            "3. 合并方案",
            "用户 15 条逐项确认（绿底 + 玻璃底栏/卡片 + A 功能）",
            "见下文「合并对照表」；去掉 DataStore glassUIEnabled",
          ],
          [
            "4. 落地提交",
            "统一组件接入 TodayScreen + MainScreen",
            "本 canvas + git commit/push",
          ],
        ]}
      />

      <Divider />

      <H2>二、用户确认的合并对照表（最终规格）</H2>
      <Table
        headers={["#", "模块", "保留方案", "关键参数"]}
        rows={[
          ["1", "背景", "绿色氛围图", "kairos_atmosphere_bg；图 blur 18dp；压暗 22%；鼠尾草雾罩"],
          ["2", "底栏", "Glass 贴底宽条", "顶圆角 24dp；blur 80dp；填充 12% 白；无粗阴影"],
          ["3", "底栏选中", "玻璃芯片", "10% 白 + 50dp blur + 边缘光晕"],
          ["4", "任务卡", "GlassTaskCard", "6% 白 + 50dp blur；左：分类 emoji/标签图标｜中：标题｜右：紧急度圈"],
          ["5", "左滑删除", "经典手势", "TaskCardSwipeStack 共享组件"],
          ["6", "长按拖动", "经典", "跨 TimeBlock，TodayScreen 边界逻辑不变"],
          ["7", "紧急度", "经典（柔和）", "完成圈浅色填充/描边 urgencyColor"],
          ["8", "时段头", "胶囊 Pill", "最大宽度约 38% 屏宽；浅色时段图标 tint"],
          ["9", "TopBar", "B 极简 + ✨ 统计玻璃底", "GlassTopBar + AutoAwesome"],
          ["10", "日期", "经典 DateSection", "44sp 星期 + yyyy年M月d日 副标题"],
          ["11", "每日一句", "B 版式 + A 字色", "GlassQuoteSection；QuoteText #2D4A2D"],
          ["12", "Today 整体", "A 功能 B 皮", "TodayScreen 未删减 BottomSheet/Widget 编辑"],
          ["13", "其他 Tab", "白字 chrome", "PrimaryTextColor → GlassConstants 白"],
          ["14", "Token", "GlassConstants", "与 AppColors 并存，新 UI 优先 Glass"],
          ["15", "开关", "移除", "Settings + DataStore 已删 glassUIEnabled"],
        ]}
      />

      <Divider />

      <H2>三、架构与入口</H2>
      <Table
        headers={["层级", "文件", "职责"]}
        rows={[
          ["背景", "ui/KairosAtmosphereBackground.kt", "全屏图 + blur + 压暗 + 雾罩；MainScreen 根 Box"],
          ["主框架", "MainScreen.kt", "始终 GlassBottomNav；Today → NavHost + TodayScreen"],
          ["Today", "TodayScreen.kt", "GlassTopBar / DateSection / GlassQuoteSection / GlassTimeBlock"],
          ["任务卡", "ui/glass/GlassTaskCard.kt", "玻璃面 + 手势；ui/components/TaskCardSwipeStack.kt"],
          ["底栏", "ui/glass/GlassBottomNav.kt", "glassMainNavTabs() 复用 i18n nav_*"],
          ["Token", "core/ui/constants/GlassConstants.kt", "模糊/填充/字色/Quote 色等"],
          ["玻璃面", "ui/glass/GlassSurface.kt", "可复用效果层（blur+fill+描边+光晕）"],
          ["遗留", "ui/glass/GlassEntryPoint.kt", "GlassOrClassicScreen 未接 MainActivity；可后续删或作预览"],
        ]}
      />

      <Divider />

      <H2>四、Gradle 修复（构建必读）</H2>
      <Text tone="secondary" size="small">
        部分环境无法解析 com.android.application 插件标记物，已在 settings.gradle.kts 使用
        pluginManagement.resolutionStrategy 映射到 com.android.tools.build:gradle 与 KSP 实体包；google()
        优先、阿里云备用。若 transforms 缓存损坏，删除 ~/.gradle/caches/8.9/transforms 后重编。
      </Text>
      <Table
        headers={["文件", "改动"]}
        rows={[
          ["settings.gradle.kts", "resolutionStrategy + 仓库顺序"],
          ["app/build.gradle.kts", "material-icons 走 BOM 版本目录"],
          ["SettingsScreen.kt", "Sparkle → AutoAwesome（旧版 icons 无 Sparkle）"],
        ]}
      />

      <Divider />

      <H2>五、数据流（Today 任务，未变）</H2>
      <Text tone="secondary" size="small">
        TaskViewModel.uiState → TodayScreen 按 currentDate/timeBlock 过滤 → GlassTaskCard 展示；点击卡片 →
        editingTask → CreateTaskBottomSheet；完成/删除/拖动仍走 taskViewModel。紧急度 LocalUrgencyConfig 在
        MainScreen CompositionLocalProvider 注入。
      </Text>

      <Divider />

      <H2>六、验证清单</H2>
      <Table
        headers={["项", "操作"]}
        rows={[
          ["编译", ".\gradlew.bat assembleDebug"],
          ["Today", "任务卡左滑删除、长按跨块拖动、✨ 3/5、每日一句点击进设置"],
          ["底栏", "五 Tab 切换；选中态玻璃芯片；白字可读"],
          ["Essay/Mine", "白字在绿底上是否舒适；卡片内深色文案是否仍可读"],
          ["设置", "确认无「玻璃界面」开关"],
        ]}
      />

      <Divider />

      <H2>七、后续可选迭代</H2>
      <Table
        headers={["优先级", "建议"]}
        rows={[
          ["P1", "Essay/View 内卡片文案：面板内仍用 AppColors 深字，避免全白导致浅玻璃板上对比不足"],
          ["P2", "删除未使用的 glass_waterlily_bg.jpg、GlassEntryPoint 死代码"],
          ["P3", "背景 blur/压暗强度微调（GlassConstants.AtmosphereImageBlurRadius / AtmosphereDimAlpha）"],
          ["P4", "时段 Pill 宽度改为 1/2 屏（改 TimeBlockPillWidthFraction）"],
        ]}
      />

      <Callout tone="info">
        关联文档：canvas/开发进度记录.md、.trae/documents/glass-ui-*.md（规划，非运行时依赖）。历史交接：
        current-development-workflow.canvas.tsx、view-today-timeline-ui-handoff.canvas.tsx。
      </Callout>
    </Stack>
  );
}
