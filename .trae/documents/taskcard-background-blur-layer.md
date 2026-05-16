# TaskCard 背景模糊层 — 精确实现计划

## 需求

给 TaskItemCard 的**玻璃渐变背景层**（最底层的 Box）添加 `blur(30.dp)` 效果，让卡片背景从「纯渐变」变为「柔光模糊玻璃」，增强发光玻璃质感。

---

## 当前状态

```
Card (Transparent) →
  Box (.background(glassBrush))              ← 纯渐变，无模糊
    Box (.blur(100dp) + grayMist)            ← 现有灰色朦胧层
    Box (.blur(100dp) + innerGlow)           ← 现有内发光层
    Row (内容层)
```

现有 `TaskCardBlurRadius = 100.dp` 仅作用于灰色朦胧层和内发光层。最底层的玻璃渐变**没有任何模糊**，导致卡片背景看起来是硬质渐变而非柔光玻璃。

---

## 修改方案

### 1. 新增常量 `TaskCardBackgroundBlurRadius = 30.dp`

**文件：** `app/.../core/ui/StyleConstants.kt`

在 `AppColors` 中 `TaskCardBlurRadius` 旁新增：

```kotlin
/** Blur radius for the base glass gradient layer (softer, background-only blur) */
val TaskCardBackgroundBlurRadius = 30.dp
```

**为什么选 30.dp？**
- 100.dp 是极重模糊（用于掩盖底层细节）
- 30.dp 是中等柔化——让渐变有「融感」但依然保留色调方向感
- 用户明确指定数值 30

### 2. 给玻璃渐变 Box 添加 `.blur(30.dp)`

**文件：** `app/.../ui/TaskItemCard.kt` — TaskCardInner()

修改第 222-227 行，在 `glassBrush` 背景 Box 上添加 blur：

```kotlin
// 修改前
Box(
    modifier = Modifier
        .fillMaxWidth()
        .clip(cardShape)
        .background(glassBrush, cardShape)
) {

// 修改后
Box(
    modifier = Modifier
        .fillMaxWidth()
        .clip(cardShape)
        .blur(AppColors.TaskCardBackgroundBlurRadius)  // ← 新增
        .background(glassBrush, cardShape)
) {
```

### 3. RED 阶段：编写测试

**文件：** `app/src/test/java/com/example/kairosapplication/ui/TaskCardStyleTest.kt`（新增）

```kotlin
@Test
fun taskCardBackgroundBlurRadius_is30dp() {
    assertEquals(30.dp, AppColors.TaskCardBackgroundBlurRadius)
}
```

### 4. 结果结构

```
Card (Transparent) →
  Box (.blur(30dp) + .background(glassBrush))  ← 新增 30dp 模糊！
    Box (.blur(100dp) + grayMist)
    Box (.blur(100dp) + innerGlow)
    Row (内容层 — 完全不受影响)
```

---

## 验证步骤

1. `./gradlew :app:testDebugUnitTest` — 新增的测试通过
2. `./gradlew :app:compileDebugKotlin` — 编译通过
3. `./gradlew :app:assembleDebug` — APK 构建成功
4. 视觉上：卡片最底层渐变从「硬边缘」变为「柔光扩散」，玻璃感更强

---

## 不涉及的文件

- NoteCard 系列（NoteCardTimeline/Project/Inbox）— 本次仅 TaskCard，
  后续统一规范时再一并更新
- TodayScreen / CreateScreen / EssayMainScreen — 不受影响
