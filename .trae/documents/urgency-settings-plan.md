# 任务紧急程度全部设定 — 实现计划

## 摘要

将紧急程度的 4 个级别（紧急/较紧急/一般/不紧急）的文本标签和颜色从硬编码改为用户可配置。新增紧急程度设置页面，通过 DataStore 持久化，全局生效（Compose UI + Widget）。遵循 TDD 流程。

核心要点：每个级别 = 一个颜色 + 一个文本，不做色彩序列/渐变逻辑。底部渐变条仅为设置页面的纯展示元素。

## 当前状态分析

### 现有 4 个级别

| level (Int) | 当前标签 | 当前颜色 | 设计图目标颜色 |
|-------------|---------|---------|--------------|
| 0 | 紧急 | #FF4444 | #FF0000 |
| 1 | 高 | #FF9800 | #FF7A00 |
| 2 | 普通 | #FFFC3A | #FFD700 |
| 3 | 低优先级 | #9E9E9E | #007BFF |

### 硬编码分布

- `TaskConstants.kt`: URGENCY_URGENT=0..3 常量 + URGENCY_LEVELS map
- `TaskUtils.kt`: urgencyColorMap + getUrgencyColor()
- `StyleConstants.kt`: AppColors.Urgent/High/Normal/Low
- `WidgetTaskStyle.kt`: urgencyArgb IntArray
- `strings.xml`: i18n_task_urgency_0~3 标签
- 20+ UI 文件通过上述 API 间接引用

## 实施步骤

### 阶段 A：数据模型与测试（task-model 模块，TDD）

#### A1. 创建 UrgencyConfig 数据模型
- 新建 `task-model/.../model/UrgencyLevelConfig.kt`
- UrgencyLevelConfig(level, label, description, colorHex)
- UrgencyConfig(levels) 含 defaultLevels()、colorForLevel()、labelForLevel()、toJson()、fromJson()
- 默认值采用设计图颜色

#### A2. 创建 ColorUtils 工具
- 新建 `task-model/.../util/ColorUtils.kt`
- 纯 Kotlin HEX 转 ARGB 解析，不依赖 android.graphics.Color

#### A3. TDD 编写测试
- 新建 UrgencyConfigTest.kt（8 个测试用例：默认值、颜色查找、标签查找、序列化往返、null/无效 JSON 容错）
- 新建 ColorUtilsTest.kt（2 个测试用例：合法/非法 HEX）
- 验证 RED → 实现 → 验证 GREEN

#### A4. 更新现有 TaskUtilsTest
- 修改颜色期望值匹配新默认颜色
- 新增自定义配置测试

### 阶段 B：数据持久化（app 模块）

#### B1. 扩展 DataStoreManager
- 新增 urgencyConfigFlow、saveUrgencyConfig()、resetUrgencyConfig()、getUrgencyConfigSync()

#### B2. 扩展 SettingsViewModel
- 新增 urgencyConfig StateFlow、updateUrgencyLevel()、resetUrgencyConfig()

### 阶段 C：集成层改造

#### C1. 改造 TaskUtils
- 新增 setUrgencyConfig() 注入点
- getUrgencyColor() 优先从注入配置读取
- 新增 getUrgencyLabel() 方法

#### C2. 改造 StyleConstants
- 新增 LocalUrgencyConfig CompositionLocal

#### C3. 改造 WidgetTaskStyle
- 新增 dynamicUrgencyArgb 和 updateFromConfig()
- fallback 到硬编码

#### C4. 改造 WidgetManager
- refreshWidgets() 中读取配置并注入 WidgetTaskStyle

### 阶段 D：UI 消费方更新

#### D1. 更新 MainScreen
- CompositionLocalProvider 提供配置
- 启动时注入 TaskUtils
- 新增 showUrgencySettings 路由

#### D2-D5. 更新颜色消费方
- UrgencyCircle.kt、TodayAgendaTimeline.kt、CreateTaskBottomSheet.kt、CreateScreen.kt

#### D6. 检查其他消费方
- MonthCalendar、ViewScreen、WidgetMainScreen 通过 TaskUtils 间接使用自动生效
- TrashScreen 的 AppColors.Urgent 用作危险操作警告色不改

### 阶段 E：设置页面

#### E1. 创建 UrgencySettingsScreen
- 4 个级别编辑行（颜色圆点 + 标签 + 描述 + 颜色值）
- 底部渐变预览条（纯展示）
- 恢复默认按钮
- 编辑弹窗（标签 + 描述 + 颜色选择器）

#### E2. 更新 SettingsScreen
- 新增 onNavigateToUrgency 参数 + 入口行

#### E3. 新增 i18n 字符串
- urgency_settings、urgency_level_label、urgency_level_description、urgency_level_color、urgency_reset_default、urgency_reset_confirm、urgency_preview、urgency_settings_desc

### 阶段 F：验证

- 全部单元测试通过
- assembleDebug 编译通过
- 手动验证：今日页颜色、创建任务弹窗、设置页编辑、恢复默认、Widget 颜色、重启持久化

## 假设与决策

1. 默认颜色变更为设计图颜色
2. 级别数量固定 4 级，不允许增删
3. TaskConstants 整数常量保留，URGENCY_LEVELS 标记 Deprecated
4. AppColors 保留作为 fallback
5. TrashScreen 不改（语义不同）
6. 渐变条纯展示
