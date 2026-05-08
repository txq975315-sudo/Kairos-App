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

export default function ViewTodayTimelineUiHandoff() {
  return (
    <Stack gap={20}>
      <H1>View「今日」时间轴 UI 与日期切换 — 开发交接</H1>
      <Text tone="secondary" size="small">
        记录 2026-05-08 本轮对 View 模块「今日」Tab 的界面重构：日期导航对齐周视图、列表改为日程轴式分区。Git 提交{" "}
        <Code>1f776de</Code>，分支 <Code>main</Code>，已推送 <Code>origin/main</Code>（Kairos-App）。
      </Text>

      <Grid columns={3} gap={12}>
        <Stat value="DaySwitcher" label="对齐 WeekSwitcher 行" />
        <Stat value="2 段" label="Todo + Notes 一级头" />
        <Stat value="4 时段" label="与 task-model 常量一致" />
      </Grid>

      <Callout tone="info">
        动机：原「今日」使用独立圆角 DateSelector 与两张 Surface 卡片；现改为顶部与「周」一致的左右箭头 +
        中间文案，列表采用左侧时段标签 + 色点 + 右侧标题/副标题的纵向时间轴阅读顺序。任务仍按 Task.timeBlock 分桶；笔记无
        timeBlock，按 Note.createdAt 本地小时推断时段（5–11 上午，12–16 下午，17–21 晚间，其余随时）。
      </Callout>

      <Divider />

      <H2>一、需求与实现摘要</H2>
      <Table
        headers={["项", "说明"]}
        rows={[
          [
            "日期切换",
            "ViewScreen 在 selectedTabIndex==0 时挂载 DaySwitcher；上一日/下一日调用 ViewViewModel.shiftFocusedDateBy；中间点击 setFocusedDate(now)。查看当天时文案追加 i18n_view_day_today。",
          ],
          [
            "一级结构",
            "TodayAgendaSectionHeader：待办 (完成/总数)、课题笔记 (条数)；英文标题全大写，中文保持原样；右侧 HorizontalDivider。",
          ],
          [
            "待办行",
            "TodayTodoAgendaTimeline：左列固定宽度时段文案（仅每段第一条显示）；与内容区间距为 (12.sp * 2).toDp()；紧急度色圆点；标题 + 可选标签副标题；右侧 UrgencyCircle；整行 viewClickable 切换完成。",
          ],
          [
            "笔记行",
            "TodayNotesAgendaTimeline：时段推断见上；圆点 NoteCardConstants.categoryColor；主行摘要/正文；点击展开后下方仍为 NoteCard(TIMELINE) + 发布态 PublishedNoteCardActions。",
          ],
          [
            "限额与跳转",
            "保留最多展示条数与「查看全部」链到主 Todo / Essay；空态沿用 view_empty_* 文案。",
          ],
        ]}
      />

      <Divider />

      <H2>二、文件索引</H2>
      <Table
        headers={["路径", "职责"]}
        rows={[
          ["app/.../ui/view/ViewScreen.kt", "今日 Tab 下注入 DaySwitcher；周/月分支 spacing 用 when"],
          ["app/.../ui/view/today/DaySwitcher.kt", "新建：周视图同款日期行"],
          ["app/.../ui/view/today/TodayTab.kt", "去掉 DateSelector 与双 Surface；串联 Agenda 与空态"],
          ["app/.../ui/view/today/TodayAgendaTimeline.kt", "新建：一级头、任务/笔记时间轴行与分桶逻辑"],
          ["app/.../res/values/strings.xml", "i18n_view_day_today"],
          ["app/.../res/values-en/strings.xml", "i18n_view_day_today"],
        ]}
      />

      <Divider />

      <H2>三、后续可增强（未强制）</H2>
      <Stack gap={8}>
        <Text tone="secondary" size="small">
          DateSelector.kt 已不再被引用，若需收敛可删除并更新翻译提取脚本引用。
        </Text>
        <Text tone="secondary" size="small">
          笔记时段规则若需与业务字段对齐（例如按 recordedDate 或用户可选时段），可替换 inferNoteTimeBlock 实现。
        </Text>
        <Text tone="secondary" size="small">
          验证命令：<Code>.\gradlew.bat :app:compileDebugKotlin</Code>
        </Text>
      </Stack>

      <Divider />

      <H2>四、与现有 canvas 关系</H2>
      <H3>关联文档</H3>
      <Text tone="secondary" size="small">
        仓库内 <Code>canvases/current-development-workflow.canvas.tsx</Code> 侧重主屏 Today/TaskViewModel
        全链路；本页仅覆盖 View 子模块「今日」预览 UI。Widget / Mine i18n 等见各自 handoff canvas。
      </Text>
    </Stack>
  );
}
