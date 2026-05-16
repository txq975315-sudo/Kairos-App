# Essay 主屏幕顶部 Tab 切换栏重叠修复计划

## 摘要

将 `EssayMainScreen.kt` 的顶部 Tab 切换栏（时间轴/主题/项目）从 `Scaffold.topBar` 内移出到 Scaffold 内容区域顶部，解决其与日期/操作栏的重叠问题，并增加视觉间隔。

---

## 当前状态分析

### 布局结构（问题代码）

在 `app/src/main/java/com/example/kairosapplication/ui/EssayMainScreen.kt` 中，`Scaffold` 的 `topBar` 参数是一个 `Column`，内部依次排列：

```
topBar = {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = AppSpacing.PageHorizontal)
    ) {
        Row { /* 日期卡片 + Inbox + Search + More 按钮 */ }   ← 第 417-716 行
        Spacer(modifier = Modifier.height(10.dp))              ← 第 718 行
        Row { /* 时间轴 | 主题 | 项目  Tab 切换栏 */ }          ← 第 720-751 行
    }
}
```

### 问题根因

1. **Tab 切换栏被塞在 `topBar` 尾部**：与顶部操作栏（日期、Inbox、Search、More）共享同一垂直 Column，仅有 10dp spacer
2. **依赖 Scaffold 自动计算 `contentPadding`**：Scaffold 将整个 topBar（含 Tab 栏）高度计入顶部内边距，但内容区域从此之下开始，导致 Tab 栏紧贴操作栏底部
3. **缺少足够视觉分隔**：10dp 间距不足以让两个独立功能模块（操作栏 vs 导航栏）从视觉上清晰区分

---

## 修改方案

### 方案：将 Tab 切换栏从 `topBar` 移到 Scaffold 内容区域

将 Tab 切换栏从 `topBar` 的 Column 中抽离，放到 Scaffold 的 `content` lambda 顶部，位于 `AnimatedContent` 之前。

**修改后布局结构：**

```
Scaffold(
    topBar = {
        Column {
            Row { /* 日期卡片 + Inbox + Search + More 按钮 */ }  ← 仅操作栏
        }
    },
) { padding ->
    Column(modifier = Modifier.fillMaxSize().padding(padding)) {
        // --- Tab 切换栏移到此处 ---
        Row { /* 时间轴 | 主题 | 项目  Tab 切换栏 */ }           ← 新位置
        Spacer(Modifier.height(4.dp)) + Divider（可选视觉分隔）
        
        // --- 原有的 AnimatedContent ---
        AnimatedContent(...) {
            when(tab) { TIMELINE / TOPIC / PROJECT }
        }
    }
}
```

### 涉及的代码变化

#### 文件：`app/.../ui/EssayMainScreen.kt`

| 操作 | 位置 | 具体改动 |
|------|------|----------|
| 删除 | `topBar` Column 内 | 移除第 718 行 `Spacer(modifier = Modifier.height(10.dp))` |
| 删除 | `topBar` Column 内 | 移除第 720-751 行 Tab 切换 `Row` 及其 `tabs.forEach` 循环 |
| 新增 | Scaffold content 顶部 | 在 `AnimatedContent` 之前插入 Tab 切换 `Row`（复用原有代码逻辑） |
| 新增 | Scaffold content 内 | 在 Tab 栏和 `AnimatedContent` 之间添加 `Spacer` + `HorizontalDivider` 作为视觉分隔 |
| 保留 | `topBar` Column 内 | 日期 + 操作按钮 Row 不动 |

#### 具体实现细节

在 Scaffold content lambda 中，当前结构是：

```kotlin
) { padding ->
    AnimatedContent(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = AppSpacing.PageHorizontal),
        ...
    ) { tab -> ... }
}
```

修改为：

```kotlin
) { padding ->
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(padding)
    ) {
        // Tab 切换栏（从 topBar 移来）
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppSpacing.PageHorizontal),
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            tabs.forEach { (tab, label) ->
                val selected = selectedTab == tab
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedTab = tab }
                        .padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = label,
                        fontSize = 15.sp,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (selected) essayHeaderPrimary else essayHeaderSecondary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(
                                if (selected) essayHeaderPrimary else Color.Transparent
                            )
                    )
                }
            }
        }

        // 视觉分隔线
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = AppSpacing.PageHorizontal),
            thickness = 0.5.dp,
            color = Color.White.copy(alpha = 0.22f)
        )

        // 原有的内容区域
        AnimatedContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppSpacing.PageHorizontal),
            targetState = selectedTab,
            transitionSpec = {
                fadeIn(animationSpec = tween(200)) togetherWith
                    fadeOut(animationSpec = tween(200))
            },
            label = "essayTabContent"
        ) { tab ->
            when (tab) { ... }  // 原有内容不变
        }
    }
}
```

`Column` 替换原有的单独 `AnimatedContent`，确保 Tab 栏固定在顶部，内容区域可滚动（保持各自独立）。

---

## 测试方案（遵循 TDD）

### 测试框架：Compose UI Test

**文件：** `app/src/test/java/com/example/kairosapplication/ui/EssayMainScreenTabBarTest.kt`（新增）

由于 Compose UI 测试需要 `ActivityScenario`，而 `EssayMainScreen` 本身不是 Activity，我们将编写专注于语义节点的测试：

| 测试用例 | 类型 | 描述 |
|----------|------|------|
| `testTabBar_belowDateBar_not_overlapping` | UI 集成测试 | 验证 Tab 切换栏的 Y 坐标 > 日期标签的 Y 坐标 + 最小间隔 |
| `testTabBar_timeline_found` | 语义测试 | 验证"时间轴"/"Timeline"文本存在 |
| `testTabBar_topic_found` | 语义测试 | 验证"主题"/"Topic"文本存在 |
| `testTabBar_project_found` | 语义测试 | 验证"项目"/"Project"文本存在 |

由于需要 Activity 上下文和 Compose 测试规则，测试基类将使用 `createComposeRule()` 来渲染 `EssayMainScreen` 的简化包装。

### 测试依赖确认

项目中已有 Compose UI 测试依赖：
```kotlin
androidTestImplementation(libs.androidx.compose.ui.test.junit4)
debugImplementation(libs.androidx.compose.ui.test.manifest)
```

### TDD 流程

1. **RED**：编写上述测试 → `./gradlew :app:testDebugUnitTest` 测试失败（因没有 UI 测试 + 可能不通过，需确认）
2. **GREEN**：实施上述布局修改 → 测试通过
3. **REFACTOR**：检查代码清晰度，确保无死代码

---

## 验证步骤

1. **编译验证**：`./gradlew :app:compileDebugKotlin :app:assembleDebug` — 应 0 错误
2. **语义测试**：Tab 文本存在于语义树中
3. **位置验证**：Tab 栏 Y 坐标 > 日期标签 Y 坐标 + 最小间隔
4. **视觉检查**：模拟器上检查两个区域清晰分开，无重叠
5. **回归验证**：所有已有功能不受影响（切换 Tab、日期选择、Inbox/Search/More 按钮）

---

## 假设与决策记录

| 决策 | 选项 | 选择 | 理由 |
|------|------|------|------|
| Tab 栏位置 | `topBar` 内 / 内容区顶部 | 内容区顶部 | 彻底解决重叠，逻辑上 Tab 导航属于页面内容而非顶栏 |
| 分隔方式 | Spacer / Divider / 两者 | Spacer + HorizontalDivider | 视觉效果明显，用户可以清晰看到两个区域的边界 |
| 测试类型 | 纯单元测试 / Compose UI 测试 | Compose UI 语义测试 + 位置断言 | 这是布局问题，需要 UI 测试验证渲染结果 |
| 使用 Column 包裹 | 是 / 否 | 是 | Tab 栏和 AnimatedContent 需要垂直排列 |
