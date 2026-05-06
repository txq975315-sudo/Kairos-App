import { H1, H2, H3, Table, Text, Stack, Divider, Callout, Grid, Stat, Code } from "cursor/canvas";

export default function WidgetModuleDevelopmentHandoff() {
  return (
    <Stack gap={20}>
      <H1>Kairos 桌面小组件模块：开发过程与对接说明</H1>
      <Text tone="secondary" size="small">
        对应远端提交：610fc1e（feat(widget): 桌面小组件多 Provider、交互与定时刷新）。本文档便于后续联调、PR 评审与扩展；代码细节以仓库为准。
      </Text>

      <Grid columns={4} gap={12}>
        <Stat value="4" label="AppWidgetProvider 条目" />
        <Stat value="1×1·2×2·3×1·3×3" label="挑选器独立尺寸" />
        <Stat value="已编译" label=":app:compileDebugKotlin" />
        <Stat value="Alarm + Boot" label="定时刷新调度" />
      </Grid>

      <Callout tone="info">
        背景：单一 Receiver 只对应系统挑选器里「一条」小组件；Android 要求多条预览必须多个带 meta-data 的组件。现改为 KairosWidgetProvider1x1 / 2x2 / 3x1 /
        3x3，共用 KairosWidgetProviderBase；刷新时 WidgetManager 遍历四类 ComponentName，并按 AppWidgetInfo.provider 反推尺寸 hint，与宿主测量值做
        WidgetSizeResolver.resolve。
      </Callout>

      <Divider />

      <H2>一、功能清单（与需求/分析报告对齐）</H2>
      <Table
        headers={["能力", "说明", "关键落点"]}
        rows={[
          ["多尺寸挑选器", "四条独立 label/description", "AndroidManifest 四个 receiver；res/xml/widget_info_*.xml；strings widget_picker_*"],
          ["1×1 布局 1B（Todo）", "三行摘要 + 统计 + 引用", "widget_1x1_1b.xml；layoutKind _1B；load1x1TodoDisplayState / ViewFactory 分支"],
          ["创建按钮", "各布局 widget_create_button", "bindCreateButton → TARGET_CREATE"],
          ["编辑任务", "列表行点击带 taskId", "WidgetTaskRow.taskId + JSON i；FillInIntent TARGET_EDIT_TASK"],
          ["深度链接", "从桌面进 Today 创建或编辑", "MainWidgetIntentViewModel WidgetNavPayload；MainScreen + TodayScreen LaunchedEffect"],
          ["文字对比度", "图片/渐变/深色底可读性", "applyBackgroundToRemoteViews 末尾 WidgetContrast.maybeApply；3×1 按 layoutKind 分支"],
          ["定时刷新", "HOURLY / DAILY", "WidgetAlarmScheduler.rescheduleAll；BootReceiver；WidgetSettingsScreen 保存后"],
          ["数据刷新入口", "避免广播扩散", "WidgetRefreshHelper → WidgetManager.refreshWidgetsAsync"],
        ]}
      />

      <Divider />

      <H2>二、核心类与职责</H2>
      <Table
        headers={["符号", "职责"]}
        rows={[
          ["WidgetManager", "合并 widget id、resolve 尺寸、加载状态、调用 ViewFactory、notifyAppWidgetViewDataChanged"],
          ["WidgetViewFactory", "按 WidgetConfig 装配 RemoteViews；bindTaskList；applyBackground；bindCreateButton"],
          ["WidgetClickHandler", "PendingIntent 槽位；TARGET_*；EXTRA_TASK_ID；列表模板 MUTABLE（S+）"],
          ["WidgetRemoteViewsService / Factory", "Task 列表 RemoteViews；行级 FillInIntent"],
          ["WidgetTaskListStore", "按 appWidgetId 存 JSON 行（含 taskId）"],
          ["WidgetAlarmScheduler / WidgetAlarmReceiver", "setInexactRepeating；触发 refreshWidgets"],
          ["WidgetContrast / WidgetSizeResolver / WidgetActions", "对比度；尺寸 hint 与测量合并；常量（广播动作可选保留）"],
        ]}
      />

      <Divider />

      <H2>三、集成点（App 内）</H2>
      <Text tone="secondary" size="small">
        MainActivity 配合 MainWidgetIntentViewModel；MainScreen 处理 TARGET_CREATE（切 Today + navigate create）与 TARGET_EDIT_TASK（pendingWidgetEditTaskId）；
        TodayScreen 消费 widgetEditTaskId 打开 CreateTaskBottomSheet。小组件设置 WidgetSettingsScreen 保存后 rescheduleAll + refreshWidgets。
      </Text>

      <Divider />

      <H2>四、验证与注意事项</H2>
      <Stack gap={8}>
        <H3>构建</H3>
        <Code block>{`gradlew :app:compileDebugKotlin`}</Code>
        <H3>设备安装后</H3>
        <Text>桌面长按添加小组件：应看到 Kairos 四条（1×1 / 2×2 / 3×1 / 3×3）。旧实例若来自旧 provider class，可能需要移除后重新添加。</Text>
        <H3>已知后续可增强（未在本轮强制）</H3>
        <Text tone="secondary" size="small">
          WidgetMainScreen 预览区 Add / 任务行与 Nav 的联动；首次安装仅依赖打开设置或 Boot 才挂 Alarm 时，可按需在 MainActivity 增加一次 rescheduleAll。
        </Text>
      </Stack>

      <Divider />

      <H2>五、Git 范围说明</H2>
      <Text tone="secondary" size="small">
        主提交仅包含 app 模块内源码与资源。未纳入仓库的路径示例：.agents、.cursor、emoji、skills-lock.json；task-model/build 构建产物勿提交。
      </Text>
    </Stack>
  );
}
