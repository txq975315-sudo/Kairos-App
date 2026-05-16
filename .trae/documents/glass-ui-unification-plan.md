# Kairos 发光玻璃质感 UI 统一规范与# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTim# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

|# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*`# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 |# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*`# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) |# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlass# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `Time# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlock# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill`# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层**# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom`# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f |# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** |# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHair# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top |# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └────────────────────────# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlock# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.d# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation =# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.d# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinG# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFF# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` —# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
//# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.Blur# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.C# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `Top# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 +# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
-# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `Glass# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` |# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `App# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill`# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.H# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**Input# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*`# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
-# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight`# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

##### Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `glassTokens_blurRadius_is30dp` | 验证统一 blur 半径为 30.dp# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `glassTokens_blurRadius_is30dp` | 验证统一 blur 半径为 30.dp |
| `glassTokens_rimLight_alphaIs35` | 验证 RimLight alpha=# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `glassTokens_blurRadius_is30dp` | 验证统一 blur 半径为 30.dp |
| `glassTokens_rimLight_alphaIs35` | 验证 RimLight alpha=0.35 |
| `glassTokens_gradient_verticalBrush_hasThreeStops` |# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `glassTokens_blurRadius_is30dp` | 验证统一 blur 半径为 30.dp |
| `glassTokens_rimLight_alphaIs35` | 验证 RimLight alpha=0.35 |
| `glassTokens_gradient_verticalBrush_hasThreeStops` | 验证 `buildGradientBrush()` 返回 3-stop 渐变 |
| `glassTokens_hairlineWidth_is08dp` |# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `glassTokens_blurRadius_is30dp` | 验证统一 blur 半径为 30.dp |
| `glassTokens_rimLight_alphaIs35` | 验证 RimLight alpha=0.35 |
| `glassTokens_gradient_verticalBrush_hasThreeStops` | 验证 `buildGradientBrush()` 返回 3-stop 渐变 |
| `glassTokens_hairlineWidth_is08dp` | 验证描边宽度 0.8.dp |
| `glassTokens_cardRadius_is24dp` | 验证圆角 24.dp# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `glassTokens_blurRadius_is30dp` | 验证统一 blur 半径为 30.dp |
| `glassTokens_rimLight_alphaIs35` | 验证 RimLight alpha=0.35 |
| `glassTokens_gradient_verticalBrush_hasThreeStops` | 验证 `buildGradientBrush()` 返回 3-stop 渐变 |
| `glassTokens_hairlineWidth_is08dp` | 验证描边宽度 0.8.dp |
| `glassTokens_cardRadius_is24dp` | 验证圆角 24.dp |

#### 2. 新建 `app/src/test/java/.../ui/CreateScreenStyle# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `glassTokens_blurRadius_is30dp` | 验证统一 blur 半径为 30.dp |
| `glassTokens_rimLight_alphaIs35` | 验证 RimLight alpha=0.35 |
| `glassTokens_gradient_verticalBrush_hasThreeStops` | 验证 `buildGradientBrush()` 返回 3-stop 渐变 |
| `glassTokens_hairlineWidth_is08dp` | 验证描边宽度 0.8.dp |
| `glassTokens_cardRadius_is24dp` | 验证圆角 24.dp |

#### 2. 新建 `app/src/test/java/.../ui/CreateScreenStyleTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `createScreen_colors_useSharedTokens` | 验证 CreateScreen 不再引用内联十六进制# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `glassTokens_blurRadius_is30dp` | 验证统一 blur 半径为 30.dp |
| `glassTokens_rimLight_alphaIs35` | 验证 RimLight alpha=0.35 |
| `glassTokens_gradient_verticalBrush_hasThreeStops` | 验证 `buildGradientBrush()` 返回 3-stop 渐变 |
| `glassTokens_hairlineWidth_is08dp` | 验证描边宽度 0.8.dp |
| `glassTokens_cardRadius_is24dp` | 验证圆角 24.dp |

#### 2. 新建 `app/src/test/java/.../ui/CreateScreenStyleTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `createScreen_colors_useSharedTokens` | 验证 CreateScreen 不再引用内联十六进制颜色（通过反射或字符串模式检查） |
| `createScreen_calendarSection_hasGlassEffect` | 验证 CalendarSection 使用了# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `glassTokens_blurRadius_is30dp` | 验证统一 blur 半径为 30.dp |
| `glassTokens_rimLight_alphaIs35` | 验证 RimLight alpha=0.35 |
| `glassTokens_gradient_verticalBrush_hasThreeStops` | 验证 `buildGradientBrush()` 返回 3-stop 渐变 |
| `glassTokens_hairlineWidth_is08dp` | 验证描边宽度 0.8.dp |
| `glassTokens_cardRadius_is24dp` | 验证圆角 24.dp |

#### 2. 新建 `app/src/test/java/.../ui/CreateScreenStyleTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `createScreen_colors_useSharedTokens` | 验证 CreateScreen 不再引用内联十六进制颜色（通过反射或字符串模式检查） |
| `createScreen_calendarSection_hasGlassEffect` | 验证 CalendarSection 使用了 GlassTokens |

#### 3. 更新 `NoteCardConstantsTest.kt`（已有）

# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `glassTokens_blurRadius_is30dp` | 验证统一 blur 半径为 30.dp |
| `glassTokens_rimLight_alphaIs35` | 验证 RimLight alpha=0.35 |
| `glassTokens_gradient_verticalBrush_hasThreeStops` | 验证 `buildGradientBrush()` 返回 3-stop 渐变 |
| `glassTokens_hairlineWidth_is08dp` | 验证描边宽度 0.8.dp |
| `glassTokens_cardRadius_is24dp` | 验证圆角 24.dp |

#### 2. 新建 `app/src/test/java/.../ui/CreateScreenStyleTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `createScreen_colors_useSharedTokens` | 验证 CreateScreen 不再引用内联十六进制颜色（通过反射或字符串模式检查） |
| `createScreen_calendarSection_hasGlassEffect` | 验证 CalendarSection 使用了 GlassTokens |

#### 3. 更新 `NoteCardConstantsTest.kt`（已有）

新增测试用例验证 NoteCard 使用的 Token# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `glassTokens_blurRadius_is30dp` | 验证统一 blur 半径为 30.dp |
| `glassTokens_rimLight_alphaIs35` | 验证 RimLight alpha=0.35 |
| `glassTokens_gradient_verticalBrush_hasThreeStops` | 验证 `buildGradientBrush()` 返回 3-stop 渐变 |
| `glassTokens_hairlineWidth_is08dp` | 验证描边宽度 0.8.dp |
| `glassTokens_cardRadius_is24dp` | 验证圆角 24.dp |

#### 2. 新建 `app/src/test/java/.../ui/CreateScreenStyleTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `createScreen_colors_useSharedTokens` | 验证 CreateScreen 不再引用内联十六进制颜色（通过反射或字符串模式检查） |
| `createScreen_calendarSection_hasGlassEffect` | 验证 CalendarSection 使用了 GlassTokens |

#### 3. 更新 `NoteCardConstantsTest.kt`（已有）

新增测试用例验证 NoteCard 使用的 Token 全部来自 `GlassTokens` 而非旧 `TaskCardGlass*`。

### TDD# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `glassTokens_blurRadius_is30dp` | 验证统一 blur 半径为 30.dp |
| `glassTokens_rimLight_alphaIs35` | 验证 RimLight alpha=0.35 |
| `glassTokens_gradient_verticalBrush_hasThreeStops` | 验证 `buildGradientBrush()` 返回 3-stop 渐变 |
| `glassTokens_hairlineWidth_is08dp` | 验证描边宽度 0.8.dp |
| `glassTokens_cardRadius_is24dp` | 验证圆角 24.dp |

#### 2. 新建 `app/src/test/java/.../ui/CreateScreenStyleTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `createScreen_colors_useSharedTokens` | 验证 CreateScreen 不再引用内联十六进制颜色（通过反射或字符串模式检查） |
| `createScreen_calendarSection_hasGlassEffect` | 验证 CalendarSection 使用了 GlassTokens |

#### 3. 更新 `NoteCardConstantsTest.kt`（已有）

新增测试用例验证 NoteCard 使用的 Token 全部来自 `GlassTokens` 而非旧 `TaskCardGlass*`。

### TDD 流程

```
Phase 1: RED
  └─ 编写 GlassTokensTest.kt (# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `glassTokens_blurRadius_is30dp` | 验证统一 blur 半径为 30.dp |
| `glassTokens_rimLight_alphaIs35` | 验证 RimLight alpha=0.35 |
| `glassTokens_gradient_verticalBrush_hasThreeStops` | 验证 `buildGradientBrush()` 返回 3-stop 渐变 |
| `glassTokens_hairlineWidth_is08dp` | 验证描边宽度 0.8.dp |
| `glassTokens_cardRadius_is24dp` | 验证圆角 24.dp |

#### 2. 新建 `app/src/test/java/.../ui/CreateScreenStyleTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `createScreen_colors_useSharedTokens` | 验证 CreateScreen 不再引用内联十六进制颜色（通过反射或字符串模式检查） |
| `createScreen_calendarSection_hasGlassEffect` | 验证 CalendarSection 使用了 GlassTokens |

#### 3. 更新 `NoteCardConstantsTest.kt`（已有）

新增测试用例验证 NoteCard 使用的 Token 全部来自 `GlassTokens` 而非旧 `TaskCardGlass*`。

### TDD 流程

```
Phase 1: RED
  └─ 编写 GlassTokensTest.kt (6 tests) → ./gradlew :app:testDebugUnitTest → FAIL

Phase 2: GREEN
  └─ 实现 GlassTokens# Kairos 发光玻璃质感 UI 统一规范与重构计划

## 摘要

将项目现有的**3 套独立玻璃 Token**（`TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*`）统一为**一套标准玻璃 Token 系统**；将 `NoteCard`、`TaskCard`、`TodayScreen`、`EssayTimelineScreen`、`CreateScreen` 统一使用同一套 4 层发光玻璃结构；消除 `CreateScreen` 中的 15+ 处硬编码颜色，统一使用 AppColors 共享 Token。

---

## 当前状态分析

### 现有玻璃效果分布

| 组件 | 当前玻璃模式 | 使用的 Token 系列 | Blur 半径 |
|------|------------|-------------------|-----------|
| NoteCardTimeline | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardProject | 4 层标准 | `TaskCardGlass*` | 100.dp |
| NoteCardInbox | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TaskItemCard | 4 层标准 | `TaskCardGlass*` | 100.dp |
| TodayScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| TodayScreen TimeBlock | 4 层 | `TimeBlockGlass*` | 100.dp |
| TodayScreen FAB | 3 层 | `TopBarGlass*` | 100.dp |
| EssayMainScreen TopBar | 4 层 | `TopBarGlass*` | 100.dp |
| CreateScreen | **单层无 blur** | `GlassFill` (无渐变) | **无** |
| EssayTabBar (已移到 content) | 无玻璃 | 纯色文字 | 无 |

### 问题诊断

1. **3 套 Token 几乎相同但数值不一致**：
   - `TaskCardGlassTop` alpha=0.08 vs `TimeBlockGlassTop` alpha=0.18 — 视觉不统一
   - `TaskCardGrayMist` alpha=0.05 vs `TimeBlockGrayMist` alpha=0.08
   - `TaskCardGlassHairline` alpha=0.20 vs `TimeBlockGlassHairline` alpha=0.35

2. **CreateScreen 完全脱离玻璃规范**：
   - 无 blur、无渐变、无边缘发光
   - 15+ 处硬编码十六进制颜色（`0xFF1A1A1A`、`0xFFE0E0E0`、`0xFFF5F5F5`...）
   - `GlassFill` 单层半透明方案 vs 标准 4 层玻璃方案

3. **Blur 半径 100.dp 过大**：
   - 用户规范建议「可见空间但不可阅读」，60-70% 透明度
   - 100.dp 在大多数设备上导致背景完全模糊消失，不符合设计意图
   - 推荐 24-36dp（配合 60-70% 透明度）

4. **边框细节不足**：
   - 用户要求「三层边缘：白色内高光 + 极细描边 + 轻阴影」
   - 当前仅有一层 `TaskCardGlassHairline` (1.1dp)
   - 缺少内高光 Box 层

---

## 设计规范（统一玻璃 Token 系统）

### 核心参数

| Token 层级 | 属性 | 值 | 设计意图 |
|-----------|------|----|---------|
| **背景层** | `GlassBaseAlpha` | 0.60–0.70f | 背景可识别但不可阅读 |
| **渐变顶层** | `GlassGradientTop` | White @ 0.12f | 顶部受光感 |
| **渐变中段** | `GlassGradientMid` | White @ 0.08f | 过渡 |
| **渐变底段** | `GlassGradientBottom` | White @ 0.10f | 底部环境光 |
| **朦胧层** | `GlassGrayMist` | `#808080` @ 0.08f | 模拟真实 blur 散色 |
| **内光晕** | `GlassInnerGlow` | White @ 0.10f | 边缘亮度提升 |
| **模糊半径** | `GlassBlurRadius` | **30.dp** | 刚好使背景不可读 |
| **描边** | `GlassHairline` | White @ 0.25f, 0.8dp | 极细边界 |
| **内高光** | `GlassRimLight` | White @ 0.35f, 6dp from top | 受光边缘 |
| **阴影色** | `GlassShadowColor` | `#1A2850` @ 0.10f | 托起卡片 |
| **阴影高度** | `GlassShadowElevation` | 3.dp | 轻悬浮 |
| **圆角** | `GlassCardRadius` | **24.dp** | 大圆角 |
| **大圆角** | `GlassFeatureRadius` | **28.dp** | 大面板 |
| **内容内边距** | `GlassContentPaddingH` | 16.dp | 留白 |
| **内容内边距** | `GlassContentPaddingV` | 12.dp | 留白 |

### 标准 4 层玻璃结构

```
┌──────────────────────────────────────────────┐
│  Shadow(elevation=3.dp, shadowColor)         │ ← 最外层阴影
│  ┌─ Border(0.8dp, hairline) ──────────────┐  │
│  │  RoundedCornerShape(24.dp)              │  │
│  │  ┌─ Layer 1: GlassGradient ──────────┐  │  │
│  │  │  Brush.verticalGradient(           │  │  │
│  │  │    Top→Mid→Bottom)                 │  │  │
│  │  ├─ Layer 2: GrayMist + Blur(30dp) ──┤  │  │
│  │  │  blur(30.dp) + GrayMist           │  │  │
│  │  ├─ Layer 3: InnerGlow + Blur(30dp) ─┤  │  │
│  │  │  blur(30.dp) + InnerGlow          │  │  │
│  │  ├─ Layer 4: TopVeil ────────────────┤  │  │
│  │  │  GlassFill.copy(alpha=0.06f)      │  │  │
│  │  │  ┌─ Content ───────────────────┐  │  │  │
│  │  │  │  padding(16.dp, 12.dp)      │  │  │  │
│  │  │  └─────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
└──────────────────────────────────────────────────┘
```

### 新增三层边缘细节

在 Layer 1 上方新增：

```
┌─ RimLight (Box, matchParentSize, 6dp from top) ─┐
│  White @ 0.35f                                    │
│  这部分只用 verticalGradient 从 0.35 → 0.0 过渡   │
└────────────────────────────────────────────────────┘
```

---

## 改动方案

### 文件清单与改动内容

#### 1. `StyleConstants.kt` — 统一 Token 系统

**核心改动：**
- 删除 `TaskCardGlass*`、`TopBarGlass*`、`TimeBlockGlass*` 三套独立 Token
- 新增统一的 `Glass*` 单套 Token（如 `GlassGradientTop`、`GlassBlurRadius` 等）
- 新增 `GlassEdgeLayers` 对象包含三层边缘辅助函数
- 保留 `TimeBlockGlass*` 仅作为 `Glass*` 的别名（向后兼容，但内部引用全部改用 `Glass*`）

```kotlin
object GlassTokens {
    // === 标准玻璃渐变（4层核心） ===
    val GradientTop = Color(0xFFFFFFFF).copy(alpha = 0.12f)
    val GradientMid = Color(0xFFFAFBFD).copy(alpha = 0.08f)
    val GradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.10f)
    val GrayMist = Color(0xFF808080).copy(alpha = 0.08f)
    val InnerGlow = Color(0xFFFFFFFF).copy(alpha = 0.10f)
    val BlurRadius = 30.dp
    
    // === 三层边缘 ===
    val RimLight = Color.White.copy(alpha = 0.35f)
    val Hairline = Color.White.copy(alpha = 0.25f)
    val HairlineWidth = 0.8.dp
    
    // === 阴影 ===
    val ShadowColor = Color(0xFF1A2850).copy(alpha = 0.10f)
    val ShadowElevation = 3.dp
    
    // === 圆角 ===
    val CardRadius = 24.dp
    val FeatureRadius = 28.dp
    
    // === 对空间缩放（薄面板用） ===
    val ThinGradientTop = Color(0xFFFFFFFF).copy(alpha = 0.08f)
    val ThinGradientMid = Color(0xFFFAFBFD).copy(alpha = 0.05f)
    val ThinGradientBottom = Color(0xFFF0F4FA).copy(alpha = 0.08f)
}

// 同时保留旧引用作为 alias（标记 @Deprecated 或直接替换）
// AppColors 中的旧字段全部标记 @Deprecated("Use GlassTokens.*")
```

#### 2. `NoteCardTimeline.kt` / `NoteCardProject.kt` / `NoteCardInbox.kt` — 更新 Token 引用

**改动：** 将所有 `AppColors.TaskCardGlass*` → `GlassTokens.*`。同时增加 RimLight 层。

**具体：**
```
// 改前: glassBrush = Brush.verticalGradient(TaskCardGlassTop→Mid→Bottom)
// 改后: glassBrush = Brush.verticalGradient(GlassTokens.GradientTop→Mid→Bottom)
// 
// 改前: .blur(AppColors.TaskCardBlurRadius)
// 改后: .blur(GlassTokens.BlurRadius)
//
// 新增: RimLight Box 层在 Layer 1 上方
```

#### 3. `TaskItemCard.kt` — 更新 Token 引用 + 调整圆角

**改动：** 
- `AppColors.TaskCardGlass*` → `GlassTokens.*`
- `AppShapes.CardRadius` (16dp) → `GlassTokens.CardRadius` (24dp)
- 增加 RimLight 层
- 调整 blur 半径

#### 4. `TodayScreen.kt` — 统一使用 GlassTokens

**改动：**
- TopBar `TopBarGlass*` → `GlassTokens.*`
- TimeBlock `TimeBlockGlass*` → `GlassTokens.*`
- FAB 按钮使用 `GlassTokens.ThinGradient*` 薄面板 Token
- 统一 blur 半径

#### 5. `CreateScreen.kt` — 全面玻璃化 + 消除硬编码

**最大改动文件。** 需要：
- 将所有硬编码十六进制颜色替换为 `AppColors.*` 或 `GlassTokens.*`
- 给 CalendarSection、InputField、工具栏面板应用标准 4 层玻璃结构
- 给选项按钮应用 `GlassTokens.*` 背景
- 对齐圆角到 `GlassTokens.CardRadius`

**硬编码替换映射表：**

| 当前硬编码 | 替换为 |
|-----------|--------|
| `Color(0xFF1A1A1A)` | `AppColors.PrimaryText` / `GlassTokens.Hairline` |
| `Color(0xFFE0E0E0)` | `AppColors.Divider` |
| `Color(0xFF757575)` | `AppColors.SecondaryText` |
| `Color(0xFF9E9E9E)` | `AppColors.HintText` |
| `Color(0xFFF5F5F5)` | `AppColors.CardBackground` |
| `Color(0xFFEEEEEE)` | `AppColors.SoftCircleFill` |
| `Color(0xFF4A4A4A)` | `AppColors.PrimaryText.copy(alpha=0.7f)` |
| `Color(0xFFB0B0B0)` | `AppColors.HintText` |
| `Color(0xFFEFEFEF)` | `AppColors.CardBackground` |
| `Color(0xFFFF4444)` | `AppColors.Urgent` |
| `Color(0xFF1976D2)` | `AppColors.PrimaryText` (使用主题色) |
| `Color(0xFF616161)` | `AppColors.SecondaryText` |
| `Color(0xFFF0F0F0)` | `AppColors.CardBackground` |

**CalendarSection 玻璃化方案：** 对 CalendarSection 的外层容器应用简化版 3 层玻璃（Gradient + GrayMist + blur + TopVeil）。

**InputField 玻璃化方案：** 使用 `GlassTokens.ThinGradient*` 薄版 + `blur(20.dp)`。

#### 6. `EssayMainScreen.kt` — TabBar 玻璃化

**改动：** 
- 第 1030-1070 行的 Tab 切换栏背景增加玻璃效果（薄版）
- 当前使用纯文字 + 下划线指示器
- 改为：底部描边区域使用 `GlassTokens.ThinGradient*` 做浅色玻璃底
- 选中指示器使用 `GlassTokens.RimLight` 发光效果

---

## 测试方案（TDD）

### 测试文件

#### 1. 新建 `app/src/test/java/.../core/ui/GlassTokensTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `glassTokens_standardValues_matchSpec` | 验证核心 Token 值符合设计规范（alpha 范围 0.08-0.12） |
| `glassTokens_blurRadius_is30dp` | 验证统一 blur 半径为 30.dp |
| `glassTokens_rimLight_alphaIs35` | 验证 RimLight alpha=0.35 |
| `glassTokens_gradient_verticalBrush_hasThreeStops` | 验证 `buildGradientBrush()` 返回 3-stop 渐变 |
| `glassTokens_hairlineWidth_is08dp` | 验证描边宽度 0.8.dp |
| `glassTokens_cardRadius_is24dp` | 验证圆角 24.dp |

#### 2. 新建 `app/src/test/java/.../ui/CreateScreenStyleTest.kt`

| 测试用例 | 描述 |
|----------|------|
| `createScreen_colors_useSharedTokens` | 验证 CreateScreen 不再引用内联十六进制颜色（通过反射或字符串模式检查） |
| `createScreen_calendarSection_hasGlassEffect` | 验证 CalendarSection 使用了 GlassTokens |

#### 3. 更新 `NoteCardConstantsTest.kt`（已有）

新增测试用例验证 NoteCard 使用的 Token 全部来自 `GlassTokens` 而非旧 `TaskCardGlass*`。

### TDD 流程

```
Phase 1: RED
  └─ 编写 GlassTokensTest.kt (6 tests) → ./gradlew :app:testDebugUnitTest → FAIL

Phase 2: GREEN
  └─ 实现 GlassTokens 到 StyleConstants.kt
  └─ 更新 NoteCardTimeline/Project/Inbox 引用
  └─ 运行测试