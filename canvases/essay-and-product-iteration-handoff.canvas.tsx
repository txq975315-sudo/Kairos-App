import { Callout, Code, Divider, H1, H2, H3, Stack, Table, Text } from "cursor/canvas";

export default function EssayAndProductIterationHandoff() {
  return (
    <Stack gap={20}>
      <H1>Kairos：Essay 闪退与产品体验迭代（开发过程与对接说明）</H1>
      <Text tone="secondary" size="small">
        本地提交：be7a1f8（fix(ui): Essay 稳定性、任务快捷创建、小组件尺寸与随笔迭代）。若尚未推送，请在可访问 GitHub 的环境执行：git push -u origin
        main。本文档便于后续联调、Code Review 与需求对齐；实现细节以仓库为准。
      </Text>

      <Callout tone="info">
        背景摘要：用户反馈安装/重装后点击底部「Essay」随笔标签仍闪退；任务快捷创建第二次带出上次内容、弹出不顺滑；桌面小组件希望按初次选择的尺寸类型固定布局，不因桌面缩放切换内部展示形态。本迭代在代码层逐项处理，并与随笔模块其它 UI/数据改动同批提交。
      </Callout>

      <Divider />

      <H2>一、Essay 随笔标签闪退</H2>
      <H3>1.1 根因（Compose 测量）</H3>
      <Text tone="secondary" size="small">
        LazyColumn 子项在竖直方向常收到「最大高度为无穷」的约束。时间线卡片使用 Modifier.height(IntrinsicSize.Max)，再配合左侧 Column
        内 fillMaxHeight、weight(1f) 绘制竖线时，易形成非法测量或运行时崩溃，真机比模拟器更容易复现。
      </Text>
      <H3>1.2 代码修复</H3>
      <Table
        headers={["文件", "做法"]}
        rows={[
          [
            "NoteCardTimeline.kt",
            "去掉外层 Row 的 IntrinsicSize.Max；卡片内左侧主题色条改为 Box.drawBehind 绘制；正文 padding 保留 4dp + CardHorizontal。",
          ],
          [
            "NoteTimelineIntegrated.kt",
            "去掉根 Box 的 IntrinsicSize.Max 与依赖 fillMaxHeight 的叠放竖线；在 Column 上使用 drawBehind 按原 Rail 上下 inset 绘制竖线。",
          ],
          [
            "NoteMappers.kt（task-model）",
            "recordedDate / deadline：epochDay 先 clamp 到 LocalDate 合法区间，再 runCatching 解析，避免损坏数据导致 DateTimeException。",
          ],
          ["EssayMainScreen.kt", "时间线列表 notePublished 使用 distinctBy { id }，降低 LazyColumn 重复 key 风险。"],
        ]}
      />

      <Divider />

      <H2>二、任务「快捷创建」</H2>
      <Table
        headers={["文件", "做法"]}
        rows={[
          [
            "TodayScreen.kt",
            "showCreateSheet 每次打开前清空 createTitle、createDescription，并将 createUrgency / createLabel 重置为新建默认值，保证每次快捷创建为空白草稿。",
          ],
          [
            "CreateTaskBottomSheet.kt",
            "rememberModalBottomSheetState(skipPartiallyExpanded = false)；LaunchedEffect 内 delay(48) 后再 requestFocus 与弹出输入法，减轻底栏动画与 IME 抢窗。",
          ],
        ]}
      />

      <Divider />

      <H2>三、桌面小组件尺寸锁定</H2>
      <Text tone="secondary" size="small">
        原逻辑：WidgetSizeResolver 在「测量尺寸 rank 大于 provider hint」时采用测量结果，用户把 2x2 拉大后可能切到 3x3 布局分支。
      </Text>
      <Table
        headers={["文件", "做法"]}
        rows={[
          [
            "WidgetSizeResolver.kt",
            "只要 AppWidgetManager 能根据 AppWidgetProvider 反推出 hint，则 resolve 始终返回 hint；hint 为空时才退回 getAppWidgetOptions 测量尺寸。",
          ],
        ]}
      />

      <Divider />

      <H2>四、同批提交中的其它改动（便于对接时扫范围）</H2>
      <Text tone="secondary" size="small">
        与上述问题同期存在于工作区的还有：随笔 EssayMainScreen 大幅迭代、Note 卡片与复盘区（NoteReviewSection）、搜索顶栏、导入导出、月视图（MonthSwitcher
        新增、DateSelector 删除）、小组件 XML/字符串、NoteRepository 等。统计约 58 个文件，以 git show be7a1f8 --stat 为准。
      </Text>

      <Divider />

      <H2>五、验证命令与推送说明</H2>
      <H3>编译</H3>
      <Code block>{`cd KairosApplication
gradlew.bat :app:compileDebugKotlin`}</Code>
      <H3>推送</H3>
      <Text>
        远端：origin → https://github.com/txq975315-sudo/Kairos-App.git。若在 Cursor/代理环境出现 Failed to connect to github.com via 127.0.0.1，请在系统终端关闭错误代理或配置正确
        HTTPS 代理后执行 git push -u origin main；必要时先 git branch --unset-upstream 再设置上游。
      </Text>

      <Divider />

      <H2>六、后续若仍有问题</H2>
      <Text tone="secondary" size="small">
        Essay 若仍有崩溃：抓取 adb logcat 中 FATAL EXCEPTION 全栈，重点搜 IllegalStateException、测量相关文案、Room/Gson
        解析异常。任务创建若需「关闭弹窗仍保留草稿」等行为，与当前「每次打开清空」产品口径冲突时需单独评审。
      </Text>
    </Stack>
  );
}
