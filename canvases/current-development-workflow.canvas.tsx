import { H1, H2, H3, Table, Text, Stack, Divider, Callout, Grid, Stat, Code } from "cursor/canvas";

export default function CurrentDevelopmentWorkflow() {
  return (
    <Stack gap={20}>
      <H1>Kairos 开发流程与任务全链路对接</H1>
      <Text tone="secondary" size="small">
        单页汇总：历史迭代时间线、本轮已确认改动、文件索引、风险与后续对接要点。面向后续接手：可直接按「任务流转」一节对照代码。
      </Text>

      <Grid columns={3} gap={12}>
        <Stat value="Main + Today" label="入口与列表主屏" />
        <Stat value="task-model" label="Task / DataStore / ViewModel" />
        <Stat value="Compose UI" label="BottomSheet / Card / Nav" />
      </Grid>

      <Callout tone="info">
        本轮已落地并确认：TaskItemCard 去掉本地 task 缓存、圆圈与详情点击分区、TopBar
        用当日列表直接统计（修复 0/0）、TimeBlock 与 EmptyTaskCard 共用 TimeBlockAddTaskButton（24dp
        圆 + 18dp 图标）。Gradle 在部分环境因下载超时失败，以本机 assemble 为准。
      </Callout>

      <Divider />

      <H2>一、开发流程时间线（合并历史 + 本轮）</H2>
      <Text tone="secondary" size="small">
        按时间顺序保留关键决策；同一主题多轮合并为一行，避免重复。
      </Text>
      <Table
        headers={["阶段", "目标/需求", "结果与落点", "验证"]}
        rows={[
          [
            "架构与模块",
            "task-model：Task、常量、TaskUtils、持久化",
            "独立模块；app 依赖；文案在 app 层",
            "task-model:test + app 构建",
          ],
          [
            "Today 编辑入口",
            "卡片点击进编辑，不再在 TaskItemCard 内嵌弹窗",
            "editingTask + CreateTaskBottomSheet(task)",
            "Stop / 保存作用于 allTasks",
          ],
          [
            "CreateTaskBottomSheet",
            "提取组件、Stop/删除/图标行",
            "删除为图标行红色 IconButton；Stop 橙色",
            "仅改组件时需防 UI 回归",
          ],
          [
            "重复任务 Stop",
            "整链 stopRepeat、未来日期待办移除",
            "TaskUtils.stopRepeat + ViewModel.saveTasks",
            "周日操作后周一数据一致",
          ],
          [
            "DailyReview / CreateScreen",
            "回顾页与创建页交互、repeatRule 保存",
            "多轮 UI/布局迭代；Bus 与 limit 校验",
            "按文件白名单改动",
          ],
          [
            "TaskItemCard 交互",
            "长按跨时段拖动；左滑删除",
            "无左侧手柄；detectDragGesturesAfterLongPress 仅在内容区",
            "详情区 clickable；圆圈单独 toggle",
          ],
          [
            "TaskItemCard 状态与点击",
            "紧急程度实时；圆圈完成可点",
            "移除 currentTask；父级不加整卡 clickable",
            "拖动 modifier 仅包右侧 Box",
          ],
          [
            "TopBar 完成度",
            "非硬编码 0/0",
            "todayTasksForTopBar = filter(taskDate == currentDate)",
            "随 uiState 与换日刷新",
          ],
          [
            "时间块加号按钮",
            "头部 + 与空卡片 + 样式一致",
            "TimeBlockAddTaskButton 复用",
            "Row CenterVertically 对齐",
          ],
        ]}
      />

      <Divider />

      <H2>二、任务（Task）全链路流转 — 对接用</H2>
      <Text tone="secondary" size="small">
        自顶向下：从入口到持久化。命名与文件路径便于在仓库内搜索。
      </Text>

      <H3>2.1 入口与状态源</H3>
      <Table
        headers={["环节", "位置", "行为"]}
        rows={[
          [
            "应用 Tab",
            "MainScreen.kt",
            "Today Tab 时 NavHost startDestination today",
          ],
          [
            "ViewModel 作用域",
            "MainScreen",
            "viewModel(TaskViewModel.factory(applicationContext))",
          ],
          [
            "列表数据",
            "TaskViewModel.uiState",
            "StateFlow；tasks 为全量 Task 列表（按业务保存）",
          ],
          [
            "进入 Today",
            "MainScreen composable(\"today\")",
            "TodayScreen(taskViewModel = taskViewModel)",
          ],
        ]}
      />

      <H3>2.2 Today 屏内：过滤、展示、统计</H3>
      <Table
        headers={["环节", "位置", "行为"]}
        rows={[
          [
            "订阅",
            "TodayScreen",
            "val allTasks = uiState.tasks（collectAsState）",
          ],
          [
            "分桶",
            "derivedStateOf / filter",
            "anytimeTasks 等：taskDate == currentDate 且 timeBlock 匹配常量",
          ],
          [
            "TopBar 数字",
            "TodayScreen",
            "todayTasksForTopBar = allTasks.filter { taskDate == currentDate }；total / completed",
          ],
          [
            "创建弹窗（当日）",
            "CreateTaskBottomSheet / showCreateSheet",
            "按时段打开；saveTasks(allTasks + new) 或限额拦截",
          ],
          [
            "Bus 同步",
            "LaunchedEffect(Unit)",
            "taskViewModel.syncFromCreationBus()（来自 Create 等入口）",
          ],
        ]}
      />

      <H3>2.3 单条任务卡片交互</H3>
      <Table
        headers={["动作", "回调/API", "数据效果"]}
        rows={[
          [
            "切换完成",
            "onToggleComplete → toggleTaskComplete(task)",
            "更新对应 Task 完成态并持久化",
          ],
          [
            "打开编辑",
            "onOpenDetail → editingTask = task",
            "弹出 CreateTaskBottomSheet(task, onSave, onDelete)",
          ],
          [
            "保存编辑",
            "onSave(updated)",
            "stopRepeat 分支或 updateTask；关闭 editingTask",
          ],
          [
            "编辑内删除",
            "onDelete",
            "deleteTask；Snackbar 撤销 undoDeleteTask / clearDeleteUndo",
          ],
          [
            "左滑删除",
            "onSwipeDelete（策略可删时）",
            "同上 delete + Snackbar",
          ],
          [
            "长按拖动换时段",
            "onDragAnchorYRoot / onDragVerticalEnd",
            "blockBounds + resolveTimeBlockAtY；moveTaskToTimeBlock；saveTasks",
          ],
        ]}
      />

      <H3>2.4 导航创建（Create 路由）</H3>
      <Table
        headers={["环节", "位置", "行为"]}
        rows={[
          [
            "跳转",
            "onCreateClick → navigate(\"create\")",
            "CreateScreen",
          ],
          [
            "批量创建",
            "onTasksCreated",
            "限额检查 firstDateExceedingLimitIfAdded；saveTasks",
          ],
        ]}
      />

      <H3>2.5 持久化与约束（概念层）</H3>
      <Text>
        ViewModel 负责聚合 uiState、saveTasks、updateTask、deleteTask、toggle、undo
        等；具体 DataStore/Room 实现在 task-model 内。单日未完成上限等业务规则以
        TaskViewModel 常量及 wouldExceedDailyPendingLimit 等为准。
      </Text>

      <Divider />

      <H2>三、文件索引（与本轮相关）</H2>
      <Table
        headers={["文件", "职责", "本轮/近期要点"]}
        rows={[
          ["MainScreen.kt", "Tab、NavHost、TaskViewModel、Create 限额弹窗", "传入 TodayScreen 的 ViewModel"],
          ["TodayScreen.kt", "日视图、分块、编辑/创建/统计/拖拽边界", "TopBar 统计；TimeBlockAddTaskButton；editingTask 链路"],
          ["TaskItemCard.kt", "卡片 UI、滑动删除、长按拖、圆圈/内容区手势分流", "仅用 props task；无 currentTask"],
          [
            "CreateTaskBottomSheet.kt",
            "创建/编辑表单、图标行、删除/Stop",
            "用户曾要求少改；对接以 TodayScreen 调用为准",
          ],
          ["CreateScreen.kt", "独立创建页、日历与重复规则", "与 TaskCreationBus、限额协同"],
          ["TaskViewModel.kt（task-model）", "单一状态源与持久化 API", "所有列表变更归口"],
          ["TaskUtils.kt（task-model）", "stopRepeat、completeToday 等", "Stop 整链逻辑"],
        ]}
      />

      <Divider />

      <H2>四、风险与协作约定</H2>
      <Table
        headers={["主题", "说明", "建议"]}
        rows={[
          [
            "文件白名单",
            "用户常限定只改指定文件",
            "开工前复述允许路径",
          ],
          [
            "Compose 状态",
            "remember/derivedStateOf 无 key 易导致不刷新",
            "统计类优先依赖 collectAsState 的 list + 明确 key",
          ],
          [
            "手势层叠",
            "整卡 clickable + pointerInput 会挡子项",
            "点击分区：拖动仅包内容区；圆圈单独 clickable",
          ],
          [
            "构建环境",
            "Gradle 分发包下载失败",
            "本机缓存或代理；以 exit code 0 为准",
          ],
        ]}
      />

      <H2>五、固定验证命令</H2>
      <Code>.\gradlew.bat :app:compileDebugKotlin :app:assembleDebug</Code>
      <Text tone="secondary" size="small">
        功能验收：TopBar 数字、圆圈完成、编辑紧急程度色、左滑删除、长按换时段、头部与空卡加号一致。
      </Text>
    </Stack>
  );
}
