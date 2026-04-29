import { H1, H2, Table, Text, Stack, Divider, Callout } from "cursor/canvas";

export default function CurrentDevelopmentWorkflow() {
  return (
    <Stack gap={16}>
      <H1>开发记录与对接看板</H1>
      <Text tone="secondary">
        项目：Kotlin + Jetpack Compose（含 task-model 模块）｜用途：进度查看、跨人对接、后续排期。
      </Text>
      <Callout tone="info">
        当前分支已新增 ArrowButton 统一按钮库、CreateScreen 路由独立化与返回刷新链路，并完成 DailyReviewScreen 从原型到可编译运行版的多轮重构修复。
      </Callout>
      <Divider />

      <H2>阶段进度</H2>
      <Table
        headers={["阶段", "状态", "核心结果", "验收方式"]}
        rows={[
          [
            "基础架构",
            "已完成",
            "新增 task-model 模块；Task/CreateTaskParam/常量/工具函数沉淀",
            ":task-model:test + :app:assembleDebug",
          ],
          [
            "文案分层",
            "已完成",
            "TaskText 从库回迁至 app（strings + provider），避免模型层耦合文案",
            ":app:compileDebugKotlin",
          ],
          [
            "CreateScreen",
            "已完成",
            "日历、多选/范围/重复规则、键盘上方发送按钮、创建成功反馈",
            ":app:assembleDebug + 手动UI验收",
          ],
          [
            "重复任务管理",
            "已完成",
            "任务卡片重复标识、详情弹窗、完成今天/停止重复（from target day）",
            "Today 交互验证 + :task-model:test",
          ],
          [
            "稳定性",
            "持续中",
            "每轮改动后固定执行 compile + assemble；冲突与IDE假红已建立处理流程",
            "verify-assemble-debug.ps1",
          ],
          [
            "导航重构",
            "已完成",
            "CreateScreen 从 overlay 升级为独立路由；Today 点击创建可跳转并返回原页",
            ":app:compileDebugKotlin + 手动导航验收",
          ],
          [
            "按钮组件统一",
            "已完成",
            "新增 ArrowButton 并替换 Today/Create 页箭头，删除不稳定 BackButton 实现",
            ":app:assembleDebug",
          ],
          [
            "Daily Review 页面",
            "已完成（首版）",
            "完成昨日任务复盘页结构、分区展示与底部操作按钮，清理重复代码导致的编译雪崩问题",
            "clean + :app:compileDebugKotlin + :app:assembleDebug",
          ],
        ]}
      />

      <H2>关键改动清单</H2>
      <Table
        headers={["模块/文件", "变更类型", "摘要"]}
        rows={[
          [
            "task-model/*",
            "新增与重构",
            "Task/参数模型、排序/颜色工具、重复任务批量处理（complete/stop）",
          ],
          [
            "app/ui/CreateScreen.kt",
            "功能迭代",
            "日期选择增强、互斥选择逻辑、发送创建链路、键盘工具栏发送按钮",
          ],
          [
            "app/TodayScreen.kt",
            "数据流重构",
            "接入 TaskCreationBus、按 taskDate 分桶显示、详情弹窗动作接入",
          ],
          [
            "app/ui/TaskItemCard.kt",
            "新增",
            "重复任务标识展示、卡片详情入口、编译兼容修复（padding/smart cast）",
          ],
          [
            "app/ui/TaskDetailBottomSheet.kt",
            "新增+迭代",
            "按钮交互修复、英文文案、按钮简化为 Complete Today/Stop Repeat",
          ],
          [
            "app/ui/components/ButtonLibrary.kt",
            "新增",
            "提供 ArrowButton（LEFT/RIGHT、size/tint 可配置），统一页面箭头入口",
          ],
          [
            "app/MainScreen.kt + app/TodayScreen.kt",
            "导航与刷新链路",
            "Create 页路由化；Today 在回前台时消费 TaskCreationBus，确保创建后列表刷新",
          ],
          [
            "app/DailyReviewScreen.kt",
            "重构修复",
            "多版本残留代码已清理为单一实现；页面结构、分区和按钮完成，当前可稳定编译运行",
          ],
        ]}
      />

      <H2>对接说明（给协作者）</H2>
      <Table
        headers={["主题", "当前约定", "协作者需要知道"]}
        rows={[
          [
            "任务创建入口",
            "CreateScreen 通过 TaskCreationBus 推送新任务",
            "TodayScreen 会消费总线并刷新，不需要手动注入任务",
          ],
          [
            "重复停止语义",
            "以被点击任务日期为 cutoff，保留当天，删除未来",
            "不要再用 LocalDate.now() 作为全局 cutoff",
          ],
          [
            "文案归属",
            "业务文案在 app 层；task-model 只放模型与规则",
            "新增文案优先 strings/provider，不进模型库",
          ],
          [
            "验证基线",
            "每次改动至少过 compile + assemble",
            "命令固定：clean / :app:compileDebugKotlin / :app:assembleDebug",
          ],
          [
            "Daily Review 当前约束",
            "先保证单文件可编译，再叠加交互",
            "若出现大量报错优先检查是否有重复 package/重复整段代码拼接",
          ],
        ]}
      />

      <H2>下一步建议</H2>
      <Text>1) 为重复任务增加 seriesId，替代 title+timeBlock 匹配。</Text>
      <Text>2) 将 TaskCreationBus 升级为持久化存储（Room）避免进程丢失。</Text>
      <Text>3) 补齐重复任务 UI 自动化回归用例（停止重复后跨日期验证）。</Text>
    </Stack>
  );
}
