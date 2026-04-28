import { H1, Table, Text, Stack, Divider } from "cursor/canvas";

export default function CurrentDevelopmentWorkflow() {
  return (
    <Stack gap={16}>
      <H1>当前开发流程汇总</H1>
      <Text tone="secondary">
        适用于当前 Kotlin + Compose 项目（含 task-model 胚子库）的日常迭代流程。
      </Text>
      <Divider />
      <Table
        headers={["阶段", "执行动作", "产出/目标", "校验命令"]}
        rows={[
          [
            "1. 需求拆解",
            "确认本轮功能范围与影响模块（app/task-model）",
            "形成可执行改动清单，避免无边界开发",
            "N/A",
          ],
          [
            "2. 代码实现",
            "按分层原则修改：task-model 放模型规则，app 放 UI 与文案",
            "完成一轮可编译代码改动",
            "N/A",
          ],
          [
            "3. 模块测试",
            "先跑 task-model 单测，验证核心规则不回归",
            "确保排序/映射等基础能力稳定",
            "./gradlew.bat :task-model:test",
          ],
          [
            "4. 整体编译验证",
            "统一执行 assembleDebug 验证主工程可构建",
            "避免把不可编译状态带入下一轮",
            "powershell -ExecutionPolicy Bypass -File \"D:/KairosApplication/scripts/verify-assemble-debug.ps1\"",
          ],
          [
            "5. 结果确认",
            "检查关键页面行为（Today 任务排序/创建/颜色）",
            "确认功能与视觉无回归",
            "必要时再执行 :app:assembleDebug",
          ],
          [
            "6. 进入下一轮",
            "记录本轮完成项与下一优先级",
            "形成连续迭代闭环（改动 -> 校验 -> 再迭代）",
            "重复步骤 1-5",
          ],
        ]}
      />
    </Stack>
  );
}
