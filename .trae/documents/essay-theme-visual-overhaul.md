# Essay 模块六大主题视觉规范全面修改计划

## 摘要

将 Essay 模块的 6 个主分类（觉察自我、社交观察、亲密关系、身心状态、意义探索、自由随想）按照新设计规范全面更新：中英文名称、主题色、辅助色、背景色、Emoji、副标题。同时消除 UI 层的硬编码颜色，让所有界面跟随分类主题色动态变化。

## 当前状态分析

### 现有颜色/名称 vs 新设计规范

| 分类 | 旧中文名 | 新中文名 | 旧英文名 | 新英文名 | 旧主题色 | 新主题色 | 新辅助色 | 新背景色 | 旧Emoji | 新Emoji |
|------|---------|---------|---------|---------|---------|---------|---------|---------|---------|---------|
| self_awareness | 自我认知 | 觉察自我 | Self Schema | Self Awareness | #2196F3 | #6A5AF9 | #CBB9FF | #F2ECFF | 🧠 | 💡 |
| interpersonal | 社会人际 | 社交观察 | Social Bond | Social Observation | #FF9800 | #00BFA5 | #B2F2E5 | #E6FBF6 | 👥 | 👥 |
| intimacy_family | 亲密关系 | 亲密关系 | Intimate Tie | Intimate Connections | #E91E63 | #FF4D6D | #FFB3C1 | #FFF0F3 | ❤️ | ❤️ |
| somatic_energy | 身心能量 | 身心状态 | Somatic Energy | Mind-Body State | #4CAF50 | #FF9A00 | #FFD580 | #FFF7E6 | 💪 | 🧘 |
| meaning | 存在探索 | 意义探索 | Existential Quest | Meaning Exploration | #9C27B0 | #2563EB | #A5C8FF | #EAF2FF | 🔮 | 🧭 |
| freestyle | 自由随想 | 自由随想 | Free Recall | Free Notes / Misc | #9E9E9E | #7AC943 | #CFF7B3 | #F3FFE6 | ✨ | 🍃 |

### 关键约束
- **分类存储 ID 不变**（self_awareness, interpersonal 等），保证已有数据兼容
- **二级分类 ID 不变**
- 当前只有单一主题色，**没有辅助色和背景色概念**，需新增
- UI 层存在硬编码颜色（EssayMainScreen、NoteEditorScreen、SearchScreen）
- 约 15+ 个 UI 文件通过 `NoteCardConstants.categoryColor()` 动态读取颜色，更新映射后自动生效

## 实施步骤

### 步骤 1：TDD — 编写 NoteCardConstants 新映射的失败测试

**文件**：`app/src/test/java/com/example/kairosapplication/ui/components/NoteCardConstantsTest.kt`（新建）

**内容**：
- 6 个主题色断言（如 `primaryColor_selfAwareness_is6A5AF9`）
- 6 个辅助色断言
- 6 个背景色断言
- 6 个 Emoji 断言
- `categorySecondaryColor()` 和 `categoryBackgroundColor()` 辅助函数断言
- fallback 行为断言

**验证**：`./gradlew :app:testDebugUnitTest` → 全部失败（RED）

### 步骤 2：TDD — 编写字符串资源 key 验证测试

**文件**：`app/src/test/java/com/example/kairosapplication/i18n/TopicStringKeysTest.kt`（新建）

**内容**：
- 验证 6 个主分类 key 映射不变
- 验证新增副标题 key 格式 `i18n_essay_primary_subtitle_*`

**验证**：测试失败（RED）

### 步骤 3：修改 NoteCardConstants.kt — 更新颜色和 Emoji

**文件**：`app/src/main/java/com/example/kairosapplication/ui/components/NoteCardConstants.kt`

**修改**：
1. 更新 `PRIMARY_CATEGORY_COLOR` 6 个颜色值
2. 更新 `PRIMARY_CATEGORY_EMOJI` 6 个 Emoji（💡/👥/❤️/🧘/🧭/🍃）
3. 新增 `PRIMARY_CATEGORY_SECONDARY_COLOR` 映射
4. 新增 `PRIMARY_CATEGORY_BACKGROUND_COLOR` 映射
5. 新增 `categorySecondaryColor(key)` 和 `categoryBackgroundColor(key)` 辅助函数
6. 更新 fallback 颜色

**验证**：步骤 1 测试全部通过（GREEN）

### 步骤 4：更新字符串资源

**文件**：
- `app/src/main/res/values/strings.xml`
- `app/src/main/res/values-en/strings.xml`

**修改**：
1. 更新 6 个 `i18n_essay_primary_*` 中文名（觉察自我/社交观察/亲密关系/身心状态/意义探索/自由随想）
2. 更新 6 个 `i18n_essay_primary_*` 英文名（Self Awareness/Social Observation/Intimate Connections/Mind-Body State/Meaning Exploration/Free Notes / Misc）
3. 新增 6 个 `i18n_essay_primary_subtitle_*` 中文副标题
4. 新增 6 个 `i18n_essay_primary_subtitle_*` 英文副标题

**验证**：步骤 2 测试通过 + `./gradlew :app:compileDebugKotlin` 通过

### 步骤 5：TopicDisplayStrings 新增副标题方法

**文件**：`app/src/main/java/com/example/kairosapplication/ui/topic/TopicDisplayStrings.kt`

**修改**：
1. 新增 `primarySubtitle(categoryKey, lang, context)` 方法
2. 新增 `rememberTopicPrimarySubtitle(categoryKey)` Composable 记忆函数

### 步骤 6：消除 UI 硬编码颜色

#### 6.1 EssayMainScreen.kt
**文件**：`app/src/main/java/com/example/kairosapplication/ui/EssayMainScreen.kt`

| 硬编码 | 用途 | 改为 |
|--------|------|------|
| `Color(0xFFEEF2FF)` | TopicNavPrimaryRow 选中背景 | `NoteCardConstants.categoryBackgroundColor(selectedPrimary)` |
| `Color(0xFF8A7CF8).copy(alpha = 0.45f)` | 选中边框 | `NoteCardConstants.categoryColor(selectedPrimary).copy(alpha = 0.45f)` |
| `Color(0xFFEEF2FF).copy(alpha = 0.65f)` | TopicNavSecondaryRow 选中背景 | `NoteCardConstants.categoryBackgroundColor(selectedPrimary).copy(alpha = 0.65f)` |

需要给 `TopicNavPrimaryRow` 和 `TopicNavSecondaryRow` 新增颜色参数。

#### 6.2 NoteEditorScreen.kt
**文件**：`app/src/main/java/com/example/kairosapplication/ui/editor/NoteEditorScreen.kt`

| 硬编码 | 用途 | 改为 |
|--------|------|------|
| `Color(0xFF8A7CF8)` | 添加按钮文字颜色 | `NoteCardConstants.categoryColor(primaryCategory)` |

#### 6.3 SearchScreen.kt（可选）
**文件**：`app/src/main/java/com/example/kairosapplication/ui/search/SearchScreen.kt`

| 硬编码 | 用途 | 改为 |
|--------|------|------|
| `Color(0xFFD4C8E8)` | SearchFieldPurple | `NoteCardConstants.categorySecondaryColor(NotePrimaryCategory.SELF_AWARENESS)` |

#### 6.4 自动生效的文件（无需改代码）
以下文件已通过 `NoteCardConstants.categoryColor()` 动态读取，更新映射后自动生效：
- `NoteCardView.kt`、`NoteCardTimeline.kt`、`NoteTimelineIntegrated.kt`、`NoteCardProject.kt`
- `WeekTab.kt`、`WeekNoteRow.kt`、`MonthCalendar.kt`、`TodayAgendaTimeline.kt`
- `TrashScreen.kt`、`InboxListScreen.kt`

### 步骤 7：全量验证

1. `./gradlew :app:compileDebugKotlin` — 编译通过
2. `./gradlew :app:testDebugUnitTest` — 所有测试通过
3. `./gradlew :task-model:testDebugUnitTest` — 数据层测试通过
4. 手动验证：中英文名称、Emoji、颜色条、选中状态、编辑器品牌色

## 决策与假设

1. **副标题通过 i18n 系统读取**，不新增数据模型字段（与现有主分类名称方案一致）
2. **辅助色和背景色新增到 NoteCardConstants**，与主题色并列管理
3. **FAB 颜色 `0xFF8A7CF8`** 保持不变（属于模块品牌色，不属于分类色）
4. **TopicManageHubScreen 暂不修改**（课题管理卡片增强为后续迭代）
5. **Emoji 选择**：💡(灯泡)、👥(三人对话)、❤️(心形)、🧘(冥想)、🧭(指南针)、🍃(叶子)

## 文件修改清单

| 文件 | 操作 |
|------|------|
| `app/src/test/.../NoteCardConstantsTest.kt` | 新建 |
| `app/src/test/.../TopicStringKeysTest.kt` | 新建 |
| `app/src/main/.../NoteCardConstants.kt` | 修改 |
| `app/src/main/res/values/strings.xml` | 修改 |
| `app/src/main/res/values-en/strings.xml` | 修改 |
| `app/src/main/.../TopicDisplayStrings.kt` | 修改 |
| `app/src/main/.../EssayMainScreen.kt` | 修改 |
| `app/src/main/.../NoteEditorScreen.kt` | 修改 |
| `app/src/main/.../SearchScreen.kt` | 修改（可选） |
