import { H1, H2, Table, Text, Stack, Divider, Callout } from "cursor/canvas";

export default function CurrentDevelopmentWorkflow() {
  return (
    <Stack gap={16}>
      <H1>开发记录与对接看板</H1>
      <Text tone="secondary">
        项目：Kotlin + Jetpack Compose（含 task-model 模块）｜用途：完整记录本轮对话开发过程，并给后续接手方直接对接。
      </Text>
      <Callout tone="info">
        当前状态：Stop 按钮已可见且已打通“点击后未来任务移除”链路；CreateScreen 正在进行键盘/图标/面板布局收口，最新改动均已按轮次编译验证。
      </Callout>
      <Divider />

      <H2>全量时间线（本轮会话）</H2>
      <Table
        headers={["阶段/时间", "用户目标", "执行结果", "验证与备注"]}
        rows={[
          [
            "1) 初始诊断",
            "检查项目结构与 Gradle，先保证可编译",
            "完成结构扫描与构建问题定位",
            "后续多轮均要求 clean/compile/assemble",
          ],
          [
            "2) DailyReview UI 优化",
            "标题区间距/高度、背景统一、底部按钮、文案与 quote 样式",
            "已按要求多次调整（仅限 DailyReview）",
            "多次 compile 验证，后续进入交互增强阶段",
          ],
          [
            "3) DailyReview 交互增强",
            "全选、卡片选择、Copy/Move 到 Today、TaskCreationBus 传递",
            "实现过完整逻辑并可编译",
            "期间出现状态类型与作用域问题，已修复过",
          ],
          [
            "4) CreateTaskBottomSheet 提取（首次）",
            "从 TodayScreen 提取可复用组件",
            "用户反馈 UI 改动不可接受，要求回滚",
            "触发“只移动不改样式/逻辑”的严格重做策略",
          ],
          [
            "5) 深度迁移版",
            "迁移 5 个代码块到 ui/components/CreateTaskBottomSheet.kt",
            "完成迁移并解决可见性/导入兼容",
            "保持调用签名兼容，TodayScreen 调用继续可用",
          ],
          [
            "6) TaskCard 点击编辑（多轮）",
            "点击卡片打开 CreateTaskBottomSheet，并填充 task",
            "多次尝试后，核心链路问题定位到调用方而非仅弹窗",
            "关键结论：必须确保 TodayScreen 传入 editingTask -> task 参数",
          ],
          [
            "7) Stop 按钮问题排查",
            "重复任务编辑时显示 Stop（红色，图标行右侧）",
            "弹窗内部逻辑多次加固，但根因曾是上游未进入编辑弹窗",
            "最终转向检查 TodayScreen 点击调用链",
          ],
          [
            "8) 用户要求放弃修改",
            "撤销“本次和上次修改”",
            "已执行回滚并按新约束重新推进",
            "后续所有改动均严格遵守文件范围约束",
          ],
          [
            "9) 全页链路检查",
            "检查 Today/Daily/Create 的编辑调用链",
            "Today 完整；Create 为创建页无任务卡片；Daily 按需补齐",
            "构建命令返回成功（exit code 0）",
          ],
          [
            "10) 最新指令",
            "详细核对 TodayScreen 调用链并编译",
            "已确认 4 项检查点全部成立",
            "用户要求将全过程记录到 canvas（即当前页面）",
          ],
          [
            "11) Stop 不显示 -> 显示修复",
            "排查重复任务编辑弹窗 Stop 按钮不显示",
            "修复 repeatRule 判定并加临时 repeatRule 红字调试",
            "compile/assemble 通过，定位出数据落为 NONE 的链路问题",
          ],
          [
            "12) 数据链路深查与修复",
            "追踪 CreateScreen -> TaskCreationBus -> TodayScreen -> 编辑回写",
            "修复 Multi/Range 保存 repeatRule，统一 Stop 触发整链 stopRepeat",
            "repeatRule 可稳定为 DAILY，Stop 后进入全局列表更新逻辑",
          ],
          [
            "13) Stop 未来任务未消失 -> 根因修复",
            "修复 Sunday 点击 Stop 后 Monday 仍存在",
            "移除 TaskItemCard 内部私有编辑弹窗，统一走 TodayScreen 编辑入口",
            "Stop 操作成功作用于 allTasks，全局未来任务删除生效",
          ],
          [
            "14) CreateScreen 布局收口（持续）",
            "图标行/发送键位置与键盘交互按用户反馈反复校准",
            "经历多轮回退与微调（含实验性 API 导入修正）",
            "当前策略：仅改单文件、每轮 compile 验证、按截图继续微调",
          ],
          [
            "基础架构（历史）",
            "已完成",
            "新增 task-model 模块；Task/CreateTaskParam/常量/工具函数沉淀",
            ":task-model:test + :app:assembleDebug",
          ],
          [
            "文案分层（历史）",
            "已完成",
            "TaskText 从库回迁至 app（strings + provider），避免模型层耦合文案",
            ":app:compileDebugKotlin",
          ],
          [
            "CreateScreen（历史）",
            "已完成",
            "日历、多选/范围/重复规则、键盘上方发送按钮、创建成功反馈",
            ":app:assembleDebug + 手动UI验收",
          ],
          [
            "重复任务管理（历史）",
            "已完成",
            "任务卡片重复标识、详情弹窗、完成今天/停止重复（from target day）",
            "Today 交互验证 + :task-model:test",
          ],
          [
            "稳定性（持续）",
            "持续中",
            "每轮改动后固定执行 compile + assemble；冲突与IDE假红已建立处理流程",
            "verify-assemble-debug.ps1",
          ],
        ]}
      />

      <H2>文件级改动/约束记录</H2>
      <Table
        headers={["文件", "本轮动作", "约束与现状"]}
        rows={[
          [
            "app/src/main/java/com/example/kairosapplication/TodayScreen.kt",
            "新增 editingTask 链路、卡片点击赋值、CreateTaskBottomSheet(task=...) 调用、onSave 回写",
            "已验证通过；当前是编辑入口主链路",
          ],
          [
            "app/src/main/java/com/example/kairosapplication/DailyReviewScreen.kt",
            "经历多轮 UI/交互改造与回滚；最终按约束补齐/核对编辑入口",
            "用户经常限定“仅改单文件”，后续改动需再次显式确认范围",
          ],
          [
            "app/src/main/java/com/example/kairosapplication/ui/components/CreateTaskBottomSheet.kt",
            "完成组件提取，后续多轮围绕 wrapper 与 Stop 按钮显示逻辑排查",
            "当前已被用户标记为“不要再改”，只做调用侧对接",
          ],
          [
            "app/src/main/java/com/example/kairosapplication/ui/TaskItemCard.kt",
            "曾尝试承载编辑弹窗入口，后按用户范围限制回收/调整",
            "后续尽量仅作为展示与点击转发组件",
          ],
          [
            "app/src/main/java/com/example/kairosapplication/ui/CreateScreen.kt",
            "新增日期模式-重复规则联动；修复 repeatRule 保存；持续调整键盘/图标/面板布局",
            "该文件进入高频迭代期：严禁牵连其它页面，保持功能逻辑不变只做布局收口",
          ],
          [
            "task-model/src/main/java/com/example/taskmodel/util/TaskUtils.kt",
            "stopRepeat 同系列匹配逻辑修正，确保点击 Stop 后删除未来任务",
            "与 TodayScreen 的全局回写联动后，Sunday->Monday 场景已通过用户截图验证",
          ],
          [
            "app/src/main/java/com/example/kairosapplication/ui/TaskItemCard.kt",
            "移除卡片内部私有编辑弹窗，统一转发到 TodayScreen 编辑链路",
            "避免局部状态更新绕过 allTasks，确保 Stop 行为作用于全局任务列表",
          ],
          [
            "Gradle 验证命令",
            "固定执行 clean / :app:compileDebugKotlin / :app:assembleDebug",
            "本轮报告中多次返回 exit code 0",
          ],
        ]}
      />

      <H2>回滚与风险点</H2>
      <Table
        headers={["风险主题", "已发生情况", "后续处理建议"]}
        rows={[
          [
            "范围冲突风险",
            "多次出现“只允许改指定文件”与“跨页联动修复”冲突",
            "每次开始改动前先锁定白名单文件并复述",
          ],
          [
            "视觉回归风险",
            "CreateTaskBottomSheet 提取时曾被判定 UI 变化",
            "后续仅做代码搬移或逻辑闭环，不做视觉调整",
          ],
          [
            "Stop 按钮误判风险",
            "曾误以为是弹窗内部条件问题，实际是上游未传 task 进入编辑态",
            "排查顺序固定：调用链 -> 传参 -> 组件内部显示条件",
          ],
          [
            "误改历史风险",
            "用户曾要求“放弃本次和上次修改”",
            "涉及历史功能时先确认是否以 HEAD 还是会话内状态为准",
          ],
        ]}
      />

      <H2>后续对接（可直接接手）</H2>
      <Text>1) 继续坚持 TodayScreen 唯一编辑入口：TaskItemCard 只转发点击，不再私有弹窗。</Text>
      <Text>2) CreateScreen 目前唯一未收口模块：按“固定布局 + 无整页滚动 + 图标/键盘共存”继续小步微调。</Text>
      <Text>3) 若出现回归，先看 repeatRule 红字与 TaskUtils.stopRepeat，再看调用链是否绕开 allTasks。</Text>
      <Text>4) 每轮改动后固定保留 compile 结果，并在 canvas 同步“改动文件 + 验证结论 + 仍存问题”。</Text>
    </Stack>
  );
}
